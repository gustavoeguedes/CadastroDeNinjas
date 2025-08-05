package dev.java10X.CadastroDeNinjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;
    public NinjaController(NinjaService ninjaService) {
        this.ninjaService =ninjaService;
    }



    @GetMapping("/boas-vindas")
    @Operation(summary = "Endpoint de boas-vindas", description = "Retorna uma mensagem de boas-vindas")
    public String boasVindas() {
        return "Boas Vindas";
    }

    @PostMapping("/criar")
    @Operation(summary = "Cria um novo ninja", description = "Endpoint para cadastrar um novo ninja e insere no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<String> criarNinja(
            @Parameter(description = "Dados do ninja a ser cadastrado", required = true)
            @RequestBody NinjaDTO ninja) {
        NinjaDTO ninjaCadastrado = ninjaService.criarNinja(ninja);

        return ResponseEntity.status(HttpStatus.CREATED).body("Ninja cadastrado com sucesso: " + ninjaCadastrado.getNome());
    }

    @GetMapping("/listar")
    @Operation(summary = "Lista todos os ninjas", description = "Endpoint para listar todos os ninjas cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
    public ResponseEntity<List<NinjaDTO>> listarNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista o ninja por Id", description = "Endpoint para listar um ninja específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
    public ResponseEntity<NinjaDTO> listarNinjasPorId(
            @Parameter(description = "ID do ninja a ser buscado", required = true)
            @PathVariable Long id) {
        NinjaDTO ninja = ninjaService.findById(id);
        if (ninja == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(ninja);
    }

    @PutMapping("alterar/{id}")
    @Operation(summary = "Altera um ninja buscando pelo ID", description = "Endpoint para alterar um ninja específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
    public Optional<NinjaDTO> alterarId(
            @Parameter(description = "ID do ninja a ser alterado", required = true)
            @PathVariable Long id,
            @Parameter(description = "Dados do ninja a ser atualizado", required = true)
            @RequestBody NinjaDTO ninjaAtualizado) {
        return ninjaService.atualizarNinja(id, ninjaAtualizado);
    }

    @DeleteMapping("deletar/{id}")
    @Operation(summary = "Deleta um ninja buscando pelo ID", description = "Endpoint para deletar um ninja específico pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado")
    })
    public ResponseEntity<String> deletarId(
            @Parameter(description = "ID do ninja a ser deletado", required = true)
            @PathVariable Long id) {

    if (ninjaService.findById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ninja não encontrado com o ID: " + id);
    }

        ninjaService.deletarNinja(id);
        return ResponseEntity.ok().body("Ninja deletado com sucesso: " + id);
    }
}
