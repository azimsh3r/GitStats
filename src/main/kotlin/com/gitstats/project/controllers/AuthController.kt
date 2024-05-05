package com.gitstats.project.controllers

import com.gitstats.project.security.AuthPrincipal
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/auth")
class AuthController {
    @GetMapping("/login")
    fun login(model: Model) : String {
        model.addAttribute("authPrincipal", AuthPrincipal())
        return "/auth/authentication"
    }
}
