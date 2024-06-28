package edu.festu.ivankuznetsov.springsamplebo94xpri.service

import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.BaseEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.BobaEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.TeaEntity

interface BaseService {
    fun getAll():MutableList<BaseEntity>
    fun save(base: BaseEntity)
    fun getById(id_base: Int): BaseEntity
    fun delete(base: BaseEntity)
}
