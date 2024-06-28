package edu.festu.ivankuznetsov.springsamplebo94xpri.service

import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.UserInfo
import edu.festu.ivankuznetsov.springsamplebo94xpri.repository.UserInfoRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException


@Service
class UserInfoService : UserDetailsService

{

    @Autowired
    private val userInfoRepository: UserInfoRepository? = null

    fun getUserById(userId: Long): UserInfo? {
        return userInfoRepository?.findById(userId.toInt())?.orElse(null)
    }

    @Autowired
    private val encoder: PasswordEncoder? = null


    override fun loadUserByUsername(username: String?): UserDetails {
        return username?.let {
            val userDetail = userInfoRepository?.findByName(username)
            return userDetail?.let { a -> UserInfoDetails(a) } ?: throw UsernameNotFoundException("User not found")
        } ?: throw UsernameNotFoundException("Username not provided")
    }

    fun addUser(userInfo: UserInfo): String {
        userInfo.password = encoder?.encode(userInfo.password)
        userInfoRepository?.save(userInfo)
        return "User Added Successfully"
    }

    fun changeUserRole(userId: Long, newRole: String) {
        val user = userInfoRepository?.findById(userId.toInt())?.orElse(null)

        // Проверка наличия пользователя
        if (user != null) {
            user.roles = newRole
            userInfoRepository?.save(user)
        } else {
            // Обработка ошибки, если пользователь не найден
        }
    }

    fun getAllUsers(): MutableList<UserInfo?>? {
        return userInfoRepository?.findAll()
    }

    fun updateUserInfo(user: UserInfo) {
        try {
            val existingUser = userInfoRepository?.findById(user.id_user ?: throw IllegalArgumentException("User ID cannot be null"))
                ?.orElseGet { throw EntityNotFoundException("User not found with id: ${user.id_user}") }

            // Проверка, что объект пользователя найден
            existingUser ?: throw EntityNotFoundException("User not found with id: ${user.id_user}")

            // Обновление информации о пользователе
            existingUser.name = user.name
            existingUser.email = user.email
            // Добавьте обновление других полей, если необходимо

            userInfoRepository?.save(existingUser)
        } catch (e: Exception) {
            // Обработка ошибок
            e.printStackTrace()
            throw e
        }
    }

    fun deleteUser(userId: Long) {
        println("Attempting to delete user with ID: $userId")
        val user = userInfoRepository?.findById(userId.toInt())
            ?.orElseThrow { throw EntityNotFoundException("User not found with ID: $userId") }

        userInfoRepository?.delete(user!!)
        println("User deleted successfully")
    }

}

