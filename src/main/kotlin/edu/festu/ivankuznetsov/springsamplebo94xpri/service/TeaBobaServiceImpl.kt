package edu.festu.ivankuznetsov.springsamplebo94xpri.service

import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.*
import edu.festu.ivankuznetsov.springsamplebo94xpri.repository.TeaBobaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException


@Service
class TeaBobaServiceImpl (private val teaBobaRepository: TeaBobaRepository): TeaBobaService {

    override fun save(teaBobaEntity: TeaBobaEntity): TeaBobaEntity {
        return teaBobaRepository.save(teaBobaEntity)
    }

    override fun getAll():MutableList<TeaBobaEntity>{
        return teaBobaRepository.findAll()
    }

    override fun findByTeaAndBoba(tea: TeaEntity, boba: BobaEntity): TeaBobaEntity? {
        return teaBobaRepository.findByTeaAndBoba(tea, boba)
    }

    override fun getById(teaBobaId: Long): TeaBobaEntity? {
        return teaBobaRepository.findById(teaBobaId).orElse(null)
    }

    override fun getAllTeaBobas(): List<TeaBobaEntity> {
        return teaBobaRepository.findAll()
    }


    override fun getTeaBobasByIds(teaBobaIds: List<Long>): List<TeaBobaEntity> {
        return teaBobaRepository.findAllById(teaBobaIds)
    }


    override fun getTeaBobaById(teaBobaId: Long): TeaBobaEntity {
        return teaBobaRepository.findById(teaBobaId).orElseThrow { NoSuchElementException("TeaBoba not found") }
    }


    override fun deleteTeaBoba(tea_boba: TeaBobaEntity) {
        teaBobaRepository.delete(tea_boba)
    }


    override fun getTeaBobaForUser(): List<TeaBobaEntity> {
        val authentication = SecurityContextHolder.getContext().authentication
        val currentUser = (authentication.principal as UserDetails).username

        // Здесь вызываем метод findByUserName для получения заказов текущего пользователя
        return teaBobaRepository.findByUserName(currentUser)
    }

    override fun updateTeaBobaPrice(teaBobaId: Long, newPrice: Double) {
        val teaBoba = teaBobaRepository.findById(teaBobaId).orElse(null)
        teaBoba?.let {
            // Обновите price в соответствии с новым значением
            it.price = newPrice
            teaBobaRepository.save(it)
        }
    }



}

