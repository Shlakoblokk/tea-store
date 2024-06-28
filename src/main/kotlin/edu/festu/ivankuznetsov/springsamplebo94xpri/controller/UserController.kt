package edu.festu.ivankuznetsov.springsamplebo94xpri.controller

import edu.festu.ivankuznetsov.springsamplebo94xpri.config.JwtService
import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.AuthRequest
import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.TeaEntity
import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.UserInfo
import edu.festu.ivankuznetsov.springsamplebo94xpri.service.TeaService
import edu.festu.ivankuznetsov.springsamplebo94xpri.service.UserInfoDetails
import edu.festu.ivankuznetsov.springsamplebo94xpri.service.UserInfoService
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView
import java.nio.file.attribute.UserPrincipal


@RestController
@RequestMapping
class UserController(private val jwtService: JwtService,
                     private val authenticationManager: AuthenticationManager,
                     private val userInfoService: UserInfoService,
                     private val teaService: TeaService,)
{


    /*@PostMapping("/addNewUser")
    fun addNewUser(
        @RequestParam("email") email: String?,
        @RequestParam("name") name: String?,
        @RequestParam("password") password: String?

    ): String? {
        val userInfo = UserInfo(email = email, name = name, password = password)
        userInfoService.addUser(userInfo)
        return "tea"
    }*/

    @PostMapping("/changeUserRole")
    fun changeUserRole(
        @RequestParam("userId") userId: Long,
        @RequestParam("newRole") newRole: String
    ): String {
        // Получите текущего аутентифицированного пользователя
        val authentication = SecurityContextHolder.getContext().authentication
        val userDetails = authentication.principal as UserInfoDetails

        // Проверка наличия у текущего пользователя роли ADMIN
        if (userDetails.authorities.any { it.authority == "ROLE_ADMIN" }) {
            // Изменение роли у выбранного пользователя
            userInfoService.changeUserRole(userId, newRole)
            return "User Role Changed Successfully"
        } else {
            return "Permission Denied"
        }
    }

    @RequestMapping("/changeUserRolePage")
    fun changeUserRolePage(): ModelAndView {
        return ModelAndView("changeUserRolePage")
    }

    @RequestMapping("/login")
    fun login(): ModelAndView {
        return ModelAndView("login")
    }


    @RequestMapping("/registration")
    fun registration(): ModelAndView {
        return ModelAndView("registration")
    }

    @PostMapping("/generateToken")
    fun authenticateAndGetToken(@RequestBody authRequest: AuthRequest): String {
        val authentication =
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken(authRequest.username, authRequest.password))
        if (authentication.isAuthenticated) {
            return jwtService.generateToken(authRequest.username)
        } else {
            throw UsernameNotFoundException("Invalid user request!")
        }
    }

    private fun getCurrentUser(): UserInfo {
        val authentication = SecurityContextHolder.getContext().authentication
        val userInfoDetails = authentication.principal as UserInfoDetails
        return userInfoDetails.userInfo
    }

}