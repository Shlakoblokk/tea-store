package edu.festu.ivankuznetsov.springsamplebo94xpri.service

import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.BobaEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.TeaEntity

interface BobaService {
    fun getAll():MutableList<BobaEntity>
    fun save(boba: BobaEntity)
    fun getById(id_boba: Int): BobaEntity
    fun delete(boba: BobaEntity)


    fun getAllBobaOptions(): List<BobaEntity>
}
