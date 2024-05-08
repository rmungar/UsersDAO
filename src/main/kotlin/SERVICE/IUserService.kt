package org.example.SERVICE


import org.example.ENTITY.UserEntity
import java.util.*

interface IUserService {
    fun create(user: UserEntity): UserEntity
    fun getById(id: UUID): UserEntity?
    fun update(user: UserEntity): UserEntity
    fun delete(id: UUID)
    fun getAll(): List<UserEntity>
}
