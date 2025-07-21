package ru.devmark.auth.service

import org.springframework.stereotype.Service

@Service
class BusinessLogicService {
    fun getInfo(): String = "Very secret information"

    fun getAdminInfo(): String = "Admin level information"
}
