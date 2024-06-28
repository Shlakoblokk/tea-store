package edu.festu.ivankuznetsov.springsamplebo94xpri.controller

import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.BobaEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.TeaBobaEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.TeaEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.UserInfo
import edu.festu.ivankuznetsov.springsamplebo94xpri.repository.BobaRepository
import edu.festu.ivankuznetsov.springsamplebo94xpri.repository.TeaBobaRepository
import edu.festu.ivankuznetsov.springsamplebo94xpri.service.*
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*


@Controller
class TeaController(
    private val teaService: TeaService,
    private val bobaService: BobaService,
    private val baseService: BaseService,
    private val teaBobaService: TeaBobaService,
    private val teaBobaRepository: TeaBobaRepository,
    private val bobaRepository: BobaRepository

    ) {


    @GetMapping("/home")
    fun home(model: Model):String {
        return "homepage"
    }

    @GetMapping("/about")
    fun about(model: Model):String {
        return "about"
    }


    //чай
    @GetMapping("/tea")
    fun getTea(model: Model):String {
        model["tea"]=teaService.getAll()
        model.addAttribute("boba",bobaService.getAll())
        model.addAttribute("base",baseService.getAll())
        return "tea"
    }

    //просмотр информации о чае когда нажимаешь на него
    /*@GetMapping("/tea/{teaId}")
    fun getTeaId(@PathVariable teaId: String, model: Model): String {
        model["tea"] = teaService.getById(teaId.toLong())
        println(teaService.getById(teaId.toLong()))
        return "tea_page"
    }*/

    /*//редактирование чая
    @GetMapping("/tea/edit/{teaId}")
    fun editTea(@PathVariable teaId: Long, model: Model): String{
        model["tea"] = teaService.getById(teaId)
        model["base"] = baseService.getAll()
        return "tea_edit"
    }

    @PostMapping("/tea/edit")
    fun editTeaPut(tea: TeaEntity): String {
        teaService.save(tea)
        return "redirect:/tea"
    }*/

    @GetMapping("/tea/edit/{teaId}")
    fun editTea(@PathVariable teaId: Long, model: Model): String{
        model["tea"] = teaService.getById(teaId)
        model["base"] = baseService.getAll()
        return "tea_edit"
    }

    @PostMapping("/tea/edit")
    fun editTeaPost(tea: TeaEntity): String {
        teaService.updateTeaAndTeaBoba(tea)
        return "redirect:/tea"
    }



    //создание нового чая
    @GetMapping("/tea/createTea")
    fun createTea(model: Model): String{
        model["tea"] = TeaEntity()
        model["base"] = baseService.getAll()
        return "tea_create"
    }

    @PostMapping("/tea/create")
    fun createTeaPost(tea: TeaEntity): String{
        println("CREATING: $tea")
        teaService.save(tea)
        return "redirect:/tea"
    }


    //удаление чая
    @GetMapping("/tea/delete/{teaId}")
    fun deleteTea(@PathVariable teaId: Long): String {
        val tea = teaService.getById(teaId)
        teaService.delete(tea)
        return "redirect:/tea"
    }











    //просмотр информации о чае когда нажимаешь на него
    /*@GetMapping("/tea_boba/{Id}")
    fun getTeaBobaId(@PathVariable teaId: String, model: Model): String {
        model["tea"] = teaService.getById(teaId.toLong())
        model["boba"]=baseService.getAll()
        return "tea_boba_page"
    }*/


   /* @GetMapping("/tea_boba/edit/{id}")
    fun showEditForm(@PathVariable id: Long, model: Model): String {

        *//*model["tea"] = teaService.getAll()
        model["base"] = baseService.getAll()
        model["boba"] = bobaService.getAll()

        val teaBoba = teaBobaService.getTeaBobaById(id)
        model.addAttribute("tea_boba", teaBoba)
        return "tea_boba_edit"*//*
        val currentUser = getCurrentUser()
        val teaBoba = teaBobaService.getTeaBobaByIdAndUser(id, currentUser)
        val allBobaOptions = bobaService.getAllBobaOptions()

        model.addAttribute("teaBoba", teaBoba)
        model.addAttribute("allBobaOptions", allBobaOptions)

        return "tea_boba_edit"




        *//*val teaBoba = teaBobaRepository.findById(id).orElse(null)

        // Получите все доступные шарики из репозитория
        val allBobaOptions = bobaRepository.findAll()

        // Добавьте атрибуты к модели
        model.addAttribute("teaBoba", teaBoba)
        model.addAttribute("allBobaOptions", allBobaOptions)

        return "tea_boba_edit"*//*
    }*/

    /*@PostMapping("/tea_boba/edit")
    fun updateTeaBoba(@ModelAttribute teaBoba: TeaBobaEntity): String {
        teaBobaService.updateTeaBoba(teaBoba)
        return "redirect:/path/to/success/page"
    }*/





    @GetMapping("/tea_boba")
    fun getAllTeaWithBoba(model:Model):String {

        val tea_boba = teaBobaService.getTeaBobaForUser()

        val totalTeaBobaPrice = tea_boba.sumByDouble { it.price ?: 0.0 }


        model.addAttribute("tea_boba", tea_boba)
        model.addAttribute("totalTeaBobaPrice", totalTeaBobaPrice)

        model.addAttribute("resetTeaBobaPrice", false)

        return "tea_boba" // Название представления, куда передаются заказы для отображения
    }

    //создание чая с шариками
    @GetMapping("/tea_boba/create/{teaId}")
    fun editTeaBoba(@PathVariable teaId: Long, model: Model): String{
        model["tea"] = teaService.getById(teaId)
        model["base"] = baseService.getAll()
        model["boba"] = bobaService.getAll()
        return "tea_boba_create"
    }


    @PostMapping("/tea_boba/create")
    fun createTeaBobaPost(
        @ModelAttribute tea: TeaEntity,
        @RequestParam bobaIds: List<Long>,
        @RequestParam quantity: Int
    ): String {
        val currentUser = getCurrentUser()

        // Получение списка объектов BobaEntity по их id
        val retrievedTea = teaService.getById(tea.teaId!!)
        val bobaEntities = bobaIds.map { bobaService.getById(it.toInt()) }


        // Рассчет цены чая с шариками на основе количества
        val totalTeaBobaPrice = calculateTotalTeaBobaPrice(retrievedTea, bobaEntities, quantity)


        // Создание новых связей в таблице tea_boba
        bobaEntities.forEach { bobaEntity ->
            val teaBobaEntity = TeaBobaEntity(tea = tea, boba = bobaEntity, quantity = quantity, user = currentUser, price = totalTeaBobaPrice)
            teaBobaService.save(teaBobaEntity)
        }

        println("Tea with Boba saved: ${tea.name} with Boba ${bobaEntities.map { it.boba_description }}")
        return "redirect:/tea_boba"
    }

    private fun getCurrentUser(): UserInfo {
        val authentication = SecurityContextHolder.getContext().authentication
        val userInfoDetails = authentication.principal as UserInfoDetails
        return userInfoDetails.userInfo
    }

    private fun calculateTotalTeaBobaPrice(tea: TeaEntity, bobaEntities: List<BobaEntity>, quantity: Int): Double {
        val totalTeaPrice = tea.price ?: 0.0
        val totalBobaPrice = bobaEntities.sumByDouble { it.price ?: 0.0 }
        return (totalTeaPrice + totalBobaPrice) * quantity
    }


    @GetMapping("/tea_boba/delete/{id}")
    fun deleteTeaBoba(@PathVariable id: Long): String {
        // Логика удаления tea_boba по идентификатору teaBobaId
        val tea_boba =teaBobaService.getById(id)
        if (tea_boba != null) {
            teaBobaService.deleteTeaBoba(tea_boba)
        }
        return "redirect:/tea_boba" // Перенаправление на страницу после удаления
    }

}