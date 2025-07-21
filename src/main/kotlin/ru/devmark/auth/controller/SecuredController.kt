package ru.devmark.auth.controller

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ru.devmark.auth.annotation.CurrentUser
import ru.devmark.auth.service.BusinessLogicService

@RestController
@RequestMapping("/secured")
class SecuredController(
    private val businessLogicService: BusinessLogicService,
) {

    @GetMapping("/info")
    fun info(
        @CurrentUser user: UserDetails, // todo сделать так, чтобы тут всегда грузилась информация о пользователе, если токен валидный
    ): String = businessLogicService.getInfo()
}
