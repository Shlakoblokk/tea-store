package edu.festu.ivankuznetsov.springsamplebo94xpri.repository

import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Repository


@Repository
interface TeaBobaRepository : JpaRepository<TeaBobaEntity, Long> {
    fun findByTeaAndBoba(tea: TeaEntity, boba: BobaEntity): TeaBobaEntity?
    //fun findAllByOrder(order: OrdersEntity): List<TeaBobaEntity>

    fun findByUserName(username: String): List<TeaBobaEntity>

    fun findByIdAndUser(id: Long, user: UserInfo): TeaBobaEntity?

    fun findByTea(tea: TeaEntity): TeaBobaEntity?

    fun findAllByTea(tea: TeaEntity): List<TeaBobaEntity>


}