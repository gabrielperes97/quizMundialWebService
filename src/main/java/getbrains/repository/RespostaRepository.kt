package getbrains.repository

import getbrains.models.Resposta
import org.springframework.data.repository.CrudRepository

interface RespostaRepository : CrudRepository<Resposta, Int>{
}