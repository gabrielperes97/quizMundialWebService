package com.getbrains.qmrestservice.repository

import com.getbrains.qmrestservice.model.Player
import org.springframework.data.repository.CrudRepository

interface PlayerRepository : CrudRepository<Player, Int> {
    fun findAllByOrderByScoreDesc():List<Player>

    fun findByNick(nick: String):Player
}
