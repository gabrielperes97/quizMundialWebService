package com.getbrains.quizmundial.webservice.controller

import com.getbrains.quizmundial.webservice.model.Player
import com.getbrains.quizmundial.webservice.repository.PlayerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("player")
class PlayerController {

    @Autowired
    lateinit var playerRepository : PlayerRepository

    @GetMapping
    fun getById(@RequestParam(value = "id", required = false) id:Int?,
                @RequestParam(value = "nick", required = false) nick:String?): Player?{
        if (id != null){
            return playerRepository.findOne(id)
        }
        if (nick != null) {
            return playerRepository.findByNick(nick)
        }
        return null
    }

    @PostMapping()
    fun save(@RequestBody player: Player): Player = playerRepository.save(player)

    @RequestMapping("ranking")
    fun ranking(): List<Player> = playerRepository.findAllByOrderByScoreDesc()

    @RequestMapping("list")
    fun list(): List<Player> = playerRepository.findAll().toList()
}