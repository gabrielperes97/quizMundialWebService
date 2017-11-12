package getbrains.repository

import getbrains.models.Jogador
import org.springframework.data.repository.CrudRepository

interface JogadorRepository : CrudRepository<Jogador, Int>
{
    fun findAllByOrderByPontuacaoDesc():List<Jogador>

    fun findByNick(nick:String):Jogador
}
