package com.gitstats.project.exceptionHandling

class BadCredentialsException(m: String = "Provided credentials are incorrect! Please try again!") : RuntimeException(m)