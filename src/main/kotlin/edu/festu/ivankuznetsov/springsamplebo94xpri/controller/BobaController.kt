package edu.festu.ivankuznetsov.springsamplebo94xpri.controller

import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.BobaEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.TeaEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.service.BaseService
import edu.festu.ivankuznetsov.springsamplebo94xpri.service.BobaService
import edu.festu.ivankuznetsov.springsamplebo94xpri.service.TeaService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
class BobaController(private val teaService: TeaService,
                     private val bobaService: BobaService,
                     private val baseService: BaseService
) {

    //шарики
    /*@GetMapping("/boba")
    fun getAllBoba(model: Model):String {
        model.addAttribute("boba",bobaService.getAll())
        return "boba"
    }*/

    //создание новых шариков
    @GetMapping("/boba/create")
    fun createBoba(model: Model): String{
        model["boba"] = BobaEntity()
        return "boba_create"
    }

    @PostMapping("/boba/create")
    fun createBobaPost(boba: BobaEntity): String{
        bobaService.save(boba)
        return "redirect:/tea"
    }

    //удаление шариков
    @GetMapping("/boba/delete/{id_boba}")
    fun deleteBoba(@PathVariable id_boba: Int): String {
        val boba = bobaService.getById(id_boba)
        bobaService.delete(boba)
        return "redirect:/tea"
    }



    @GetMapping("/boba/edit/{id_boba}")
    fun editBoba(@PathVariable id_boba: Long, model: Model): String{
        model["boba"] = bobaService.getById(id_boba.toInt())
        return "boba_edit"
    }

    @PostMapping("/boba/edit")
    fun editBoba(boba: BobaEntity): String {
        bobaService.save(boba)
        return "redirect:/tea"
    }
}