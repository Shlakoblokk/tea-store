package edu.festu.ivankuznetsov.springsamplebo94xpri.service

import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.BobaEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.TeaEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.repository.TeaRepository
import javax.transaction.Transactional

interface TeaService{

    fun getAll():MutableList<TeaEntity>
    fun save(tea: TeaEntity)
    fun getById(teaId: Long): TeaEntity
    fun delete(tea: TeaEntity)

    fun updateTeaAndTeaBoba(tea: TeaEntity)

}