package ru.devmark.auth.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/jwt")
class JwtController {
    // todo этот контроллер позволяет выпускать пару jwt-токенов (refresh + access) и перевыпускать их по refresh токену
}
