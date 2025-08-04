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

    @GetMapping("/listar")
    public List<NinjaModel> listarNinjas() {
        return ninjaService.listarNinjas();
    }

    @GetMapping("/listar/{id}")
    public NinjaModel listarNinjasPorId(@PathVariable Long id) {
        return ninjaService.findById(id);
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
