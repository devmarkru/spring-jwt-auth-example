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
                select p.id, p.login, p.first_name, p.last_name, p.password_hash, p.created,
                       p.last_login, r.role_name
                from person p
                join role r on r.id = p.role_id
                where p.login = :login
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
                role = rs.getString("role_name"),
                passwordHash = rs.getString("password_hash"),
                created = rs.getTimestamp("created").toLocalDateTime(),
                lastLogin = rs.getTimestamp("last_login").toLocalDateTime(),
            )
        }
    }
}
