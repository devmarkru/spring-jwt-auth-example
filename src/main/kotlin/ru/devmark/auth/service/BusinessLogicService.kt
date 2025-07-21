package ru.devmark.auth.service

import org.springframework.stereotype.Service
import ru.devmark.auth.dto.MessageResponse

@Service
class BusinessLogicService {
    fun getInfo(): MessageResponse = MessageResponse("Very secret information")

    fun getAdminInfo(): MessageResponse = MessageResponse("Admin level information")
}
