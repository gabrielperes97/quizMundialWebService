package get.brains.quizmundial.webservice.repository

import get.brains.quizmundial.webservice.model.Player
import org.springframework.data.repository.CrudRepository

interface PlayerRepository : CrudRepository<Player, Int> {
    fun findAllByOrderByScoreDesc():List<Player>

    fun findByNick(nick: String): Player

    fun findByNickAndPassword(nick: String, password: String): Player
}
