package getbrains.controller

import getbrains.models.Jogador
import getbrains.repository.JogadorRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("jogador")
//Link para uma ajudinha https://medium.com/collabcode/implementando-uma-crud-api-no-spring-boot-com-kotlin-parte-1-c6e281d0f8f8
class JogadorController {

    @Autowired
    lateinit var jogadorRepository : JogadorRepository


    //Retorna um usuário em especifico requisitado a partir de uma chamada GET passando como parametro um id com o id do jogador
    // O link é apenas /jogador
    @GetMapping
    fun getById(@RequestParam(value = "id", required = false) id:Int?,
                @RequestParam(value = "nick", required = false) nick:String?):Jogador? {
        if (id != null)
        {
            return jogadorRepository.findOne(id)
        }
        if (nick != null)
        {
            return jogadorRepository.findByNick(nick)
        }
        return null
    }

    //Salva um objeto do tipo jogador enviado no corpo de uma chamada POST
    //O link é apernas /jogador/
    @PostMapping
    fun save(@RequestBody jogador: Jogador): Jogador {
        return jogadorRepository.save(jogador)
    }

    //Pega o ranking de jogadores ordenado decrescentemente por pontuacao
    //Acessa em /jogador/ranking sem parametros
    @RequestMapping("ranking")
    fun ranking(): List<Jogador>
    {
        return jogadorRepository.findAllByOrderByPontuacaoDesc()
    }

    //Retorna a lista de jogadores através de uma chamada qualquer
    //o link é /jogadores/list
    @RequestMapping("list")
    fun list(): List<Jogador>
    {
        return jogadorRepository.findAll().toList()
    }






    /*
    @Autowired
    private JogadorDAO jogadorDAO;

    @RequestMapping("/get-by-id")
    @ResponseBody
    public Jogador getById(String id) {
        Jogador jogador = null;
        try {
            jogador = jogadorDAO.findById(Integer.parseInt(id));
        }
        catch (Exception ex) {
        }
        return jogador;
    }*/
}