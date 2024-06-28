package edu.festu.ivankuznetsov.springsamplebo94xpri.service

import edu.festu.ivankuznetsov.springsamplebo94xpri.entity.UserInfo
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*
import java.util.stream.Collectors


class UserInfoDetails(
    val userInfo: UserInfo
) : UserDetails {

   /* override fun getAuthorities(): Collection<GrantedAuthority>? {
        return userInfo.roles?.split(",")?.map { SimpleGrantedAuthority(it) }
    }*/

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return userInfo.roles?.split(",")?.map { SimpleGrantedAuthority(it) } ?: emptyList()
    }

    override fun getPassword(): String? {
        return userInfo.password
    }

    override fun getUsername(): String? {
        return userInfo.name
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}



