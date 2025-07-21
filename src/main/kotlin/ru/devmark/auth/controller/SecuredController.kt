package ru.devmark.auth.controller

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.access.prepost.PreAuthorize
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
        @CurrentUser user: UserDetails,
    ): String = businessLogicService.getInfo()

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    fun adminInfo(@CurrentUser user: UserDetails): String = businessLogicService.getAdminInfo()
}
