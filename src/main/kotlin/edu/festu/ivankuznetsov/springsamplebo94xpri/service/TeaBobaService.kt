package edu.festu.ivankuznetsov.springsamplebo94xpri.service

import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.*
import org.springframework.data.jpa.repository.Modifying
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.transaction.annotation.Transactional

interface TeaBobaService {
    fun save(teaBobaEntity: TeaBobaEntity): TeaBobaEntity
    fun getAll():MutableList<TeaBobaEntity>

    fun getById(teaBobaId: Long): TeaBobaEntity?
    fun findByTeaAndBoba(tea: TeaEntity, boba: BobaEntity): TeaBobaEntity?


    fun getAllTeaBobas():List<TeaBobaEntity>


    fun getTeaBobasByIds (teaBobaIds: List<Long>): List<TeaBobaEntity>


    fun getTeaBobaById(teaBobaId: Long): TeaBobaEntity

    fun deleteTeaBoba(tea_boba: TeaBobaEntity)


    fun getTeaBobaForUser(): List<TeaBobaEntity>

    fun updateTeaBobaPrice(teaBobaId: Long, newPrice: Double)



}