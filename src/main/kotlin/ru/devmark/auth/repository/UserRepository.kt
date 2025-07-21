package ru.devmark.auth.repository

import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Repository
import ru.devmark.auth.entity.PersonEntity

@Repository
class UserRepository(
    private val jdbcClient: JdbcClient,
) {
    fun findByLogin(login: String): PersonEntity? =
        jdbcClient.sql(
            """
                select id, login, first_name, last_name, password_hash, created, last_login
                from person
                where login = :login
            """
        )
            .param("login", login)
            .query(mapper)
            .optional()
            .orElse(null)

    fun updateLastLogin(id: Int) {
        jdbcClient.sql("update person set last_login = current_timestamp where id = :id")
            .param("id", id)
            .update()
    }

    companion object {
        private val mapper = RowMapper { rs, _ ->
            PersonEntity(
                id = rs.getInt("id"),
                login = rs.getString("login"),
                firstName = rs.getString("first_name"),
                lastName = rs.getString("last_name"),
                passwordHash = rs.getString("password_hash"),
                created = rs.getTimestamp("created").toLocalDateTime(),
                lastLogin = rs.getTimestamp("last_login").toLocalDateTime(),
            )
        }
    }
}
