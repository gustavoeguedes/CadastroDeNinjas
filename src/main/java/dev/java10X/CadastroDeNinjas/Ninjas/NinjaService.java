package dev.java10X.CadastroDeNinjas.Ninjas;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NinjaService {


    private NinjaRepository ninjaRepository;
    private NinjaMapper ninjaMapper;

    public NinjaService(NinjaRepository ninjaRepository, NinjaMapper ninjaMapper) {
        this.ninjaRepository = ninjaRepository;
        this.ninjaMapper = ninjaMapper;
    }

    public List<NinjaDTO> listarNinjas() {
        List<NinjaModel> ninjas = ninjaRepository.findAll();

        return ninjas.stream()
                .map(ninjaMapper::map)
                .collect(Collectors.toList());
    }

    public NinjaDTO findById(Long id) {
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        return ninjaPorId.map(ninjaMapper::map).orElse(null);
    }

    public NinjaDTO criarNinja(NinjaDTO ninjaDTO) {
        NinjaModel model = ninjaMapper.map(ninjaDTO);
        NinjaModel saved = ninjaRepository.save(model);
        return ninjaMapper.map(saved);
    }

    public void deletarNinja(Long id) {
         ninjaRepository.deleteById(id);
    }

    public Optional<NinjaDTO> atualizarNinja(Long id, NinjaDTO ninjaAtualizado) {
        Optional<NinjaModel> ninjaPorId = ninjaRepository.findById(id);
        if (ninjaPorId.isPresent()) {
            NinjaModel ninjaAtualizadoModel = ninjaMapper.map(ninjaAtualizado);
            ninjaAtualizadoModel.setId(id);
            NinjaModel saved = ninjaRepository.save(ninjaAtualizadoModel);
            return Optional.of(ninjaMapper.map(saved));
        }
        return Optional.empty();
    }



}
