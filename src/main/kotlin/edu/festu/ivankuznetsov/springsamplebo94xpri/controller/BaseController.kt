package edu.festu.ivankuznetsov.springsamplebo94xpri.controller

import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.BaseEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.BobaEntity
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
class BaseController (private val teaService: TeaService,
                      private val bobaService: BobaService,
                      private val baseService: BaseService
) {

    @GetMapping("/base")
    fun getAllBase(model:Model):String {
        model.addAttribute("base",baseService.getAll())
        return "base"
    }

    //создание новых шариков
    @GetMapping("/base/create")
    fun createBase(model: Model): String{
        model["base"] = BaseEntity()
        return "base_create"
    }

    @PostMapping("/base/create")
    fun createBasePost(base: BaseEntity): String{
        baseService.save(base)
        return "redirect:/base"
    }

    //удаление шариков
    @GetMapping("/base/delete/{id_base}")
    fun deleteBase(@PathVariable id_base: Int): String {
        val base = baseService.getById(id_base)
        baseService.delete(base)
        return "redirect:/base"
    }


    @GetMapping("/base/edit/{id_base}")
    fun editBase(@PathVariable id_base: Long, model: Model): String{
        model["base"] = baseService.getById(id_base.toInt())
        return "base_edit"
    }

    @PostMapping("/base/edit")
    fun editBase(base: BaseEntity): String {
        baseService.save(base)
        return "redirect:/base"
    }

}