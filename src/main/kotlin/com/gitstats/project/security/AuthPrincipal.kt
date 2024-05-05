package com.gitstats.project.security

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

class AuthPrincipal {
    @Size(min=1, max=39, message = "Username should be between 1 and 39 characters")
    @NotEmpty(message = "Username cannot be blank")
    var username: String? = null

    @Size(min=40, message = "Access Token should consist of 100 characters")
    @NotEmpty(message="PAT token cannot be empty")
    var password: String? = null
}
