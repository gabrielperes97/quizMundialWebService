package com.getbrains.quizmundial.webservice.repository

import com.getbrains.quizmundial.webservice.model.Player
import org.springframework.data.repository.CrudRepository

interface PlayerRepository : CrudRepository<Player, Int> {
    fun findAllByOrderByScoreDesc():List<Player>

    fun findByNick(nick: String): Player
}
