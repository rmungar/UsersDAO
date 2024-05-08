package org.example.DAO

import org.example.ENTITY.UserEntity
import org.example.OUTPUT.Console
import java.sql.SQLException
import java.util.*
import javax.sql.DataSource

class UserDAOH2(private val dataSource: DataSource) : IUserDAO {

    private val console = Console()

    override fun create(user: UserEntity): UserEntity? {
        val sql = "INSERT INTO tuser (id, name, email) VALUES (?, ?, ?)"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, user.id.toString())
                    stmt.setString(2, user.name)
                    stmt.setString(3, user.email)
                    val rs = stmt.executeUpdate()
                    if (rs ==1){
                        user
                    }
                    else{
                        console.showMessage("-- ERROR AL CREAR EL USUARIO --", true)
                        null
                    }
                }
            }
        }catch (e:SQLException){
            console.showMessage("-- ERROR AL CREAR EL USUARIO --", true)
            return null
        }
    }

    override fun getById(id: UUID): UserEntity? {
        val sql = "SELECT * FROM tuser WHERE id = (?)"
        return dataSource.connection.use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                stmt.setString(1, id.toString())
                val rs = stmt.executeQuery()
                if (rs.next()) {
                    UserEntity(
                        id = UUID.fromString(rs.getString("id")),
                        name = rs.getString("name"),
                        email = rs.getString("email")
                    )
                } else {
                    console.showMessage("-- ERROR AL OBTENER EL USUARIO --", true)
                    null
                }
            }
        }
    }

    override fun getAll(): List<UserEntity> {
        val sql = "SELECT * FROM tuser"
        return dataSource.connection.use { conn ->
            conn.prepareStatement(sql).use { stmt ->
                val rs = stmt.executeQuery()
                val users = mutableListOf<UserEntity>()
                while (rs.next()) {
                    users.add(
                        UserEntity(
                            id = UUID.fromString(rs.getString("id")),
                            name = rs.getString("name"),
                            email = rs.getString("email")
                        )
                    )
                }
                users
            }
        }
    }

    override fun update(user: UserEntity):UserEntity? {
        val sql = "UPDATE tuser SET name = ?, email = ? WHERE id = ?"
        return try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, user.name)
                    stmt.setString(2, user.email)
                    stmt.setString(3, user.id.toString())
                    stmt.executeUpdate()
                    user
                }
            }
        }catch (e:SQLException){
            console.showMessage("-- ERROR AL ACTUALIZAR LA BASE DE DATOS", true)
            null
        }
    }

    override fun delete(id: UUID): Boolean {
        val sql = "DELETE FROM tuser WHERE id = ?"
        try {
            dataSource.connection.use { conn ->
                conn.prepareStatement(sql).use { stmt ->
                    stmt.setString(1, id.toString())
                    stmt.executeUpdate()
                    true
                }
            }
        } catch (e: SQLException) {
            console.showMessage("-- ERROR AL BORRAR EL USUARIO --", true)
            return false
        }
        return false
    }
}

