package getbrains.repository

import getbrains.models.Questao
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional

@Transactional
interface QuestaoRepository : CrudRepository<Questao, Int>
