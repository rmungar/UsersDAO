package org.example

import org.example.DAO.UserDAOH2
import org.example.DBSF.DataSourceFactory
import org.example.ENTITY.UserEntity
import org.example.OUTPUT.Console
import org.example.SERVICE.UserServiceImpl

fun main() {


    // Creamos la instancia de la base de datos
    val dataSource = DataSourceFactory.getDS(DataSourceFactory.DataSourceType.HIKARI)

    // Creamos la instancia de UserDAO
    val userDao = UserDAOH2(dataSource)

    // Creamos la instancia de UserService
    val userService = UserServiceImpl(userDao)

    val console = Console()


    // Creamos un nuevo usuario
    val newUser = UserEntity(name = "John Doe", email = "johndoe@example.com")
    var createdUser = userService.create(newUser)
    console.showMessage("Created user: $createdUser", true)

    // Obtenemos un usuario por su ID
    val foundUser = userService.getById(createdUser.id)
    console.showMessage("Found user: $foundUser",true)

    // Actualizamos el usuario
    val updatedUser = foundUser!!.copy(name = "Jane Doe")
    val savedUser = userService.update(updatedUser)
    console.showMessage("Updated user: $savedUser",true)

    val otherUser = UserEntity(name = "Eduardo Fernandez", email = "eferoli@gmail.com")
    createdUser = userService.create( otherUser)
    console.showMessage("Created user: $createdUser",true)


    // Obtenemos todos los usuarios

    console.showUsers(userService.getAll())

    // Eliminamos el usuario
    userService.delete(savedUser.id)
    console.showMessage("User deleted",true)

    // Obtenemos todos los usuarios
    console.showUsers(userService.getAll())

    // Eliminamos el usuario
    userService.delete(otherUser.id)
    console.showMessage("User deleted",true)

    console.showUsers(userService.getAll())
}