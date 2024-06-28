package edu.festu.ivankuznetsov.springsamplebo94xpri.repository

import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.UserInfo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository


@Repository
interface UserInfoRepository : JpaRepository<UserInfo?, Int?> {
    fun findByName(username: String?): UserInfo?


   /* @Transactional
    @Query("SELECT t from userinfo t left join fetch t.roles where t.username = ?1")
    fun findByName(username: String): UserInfo?*/
}