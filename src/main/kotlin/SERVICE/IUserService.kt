package org.example.SERVICE

import org.example.DAO.IUserDAO
import org.example.DAO.UserService
import org.example.ENTITY.UserEntity
import java.util.*

class IUserServiceImpl(private val userDao: IUserDAO) : UserService {
    override fun create(user: UserEntity): UserEntity {
        return userDao.create(user)
    }

    override fun getById(id: UUID): UserEntity? {
        return userDao.getById(id)
    }

    override fun update(user: UserEntity): UserEntity {
        return userDao.update(user)
    }

    override fun delete(id: UUID) {
        userDao.delete(id)
    }

    override fun getAll(): List<UserEntity> {
        return userDao.getAll()
    }
}
