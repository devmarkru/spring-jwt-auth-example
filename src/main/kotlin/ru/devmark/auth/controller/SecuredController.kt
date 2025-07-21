package ru.devmark.auth.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/secured")
class SecuredController {
    // todo все запросы, которые в этом контроллере, доступны только с использованием jwt
}