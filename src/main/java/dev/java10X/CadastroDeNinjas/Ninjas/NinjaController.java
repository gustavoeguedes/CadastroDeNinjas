package dev.java10X.CadastroDeNinjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class NinjaController {

    private final NinjaService ninjaService;
    public NinjaController(NinjaService ninjaService) {
        this.ninjaService =ninjaService;
    }


    @GetMapping("/boas-vindas")
    public String boasVindas() {
        return "Boas Vindas";
    }

    @PostMapping("/criar")
    public String criarNinja() {
        return "Ninja Criada";
    }

    @GetMapping("todosNinjas")
    public List<NinjaModel> listarNinjas() {
        return ninjaService.listarNinjas();
    }

    @GetMapping("por-id")
    public String porId() {
        return "Por Id";
    }

    @PutMapping("alterar-id")
    public String alterarId() {
        return "Alterar Id";
    }

    @DeleteMapping("deletar-id")
    public String deletarId() {
        return "Deletar Id";
    }
}
