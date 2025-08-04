package dev.java10X.CadastroDeNinjas.Ninjas;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NinjaRepository extends JpaRepository<NinjaModel, Long> {
    @Override
    List<NinjaModel> findAll();

    Optional<NinjaModel> findById(Long id);

    @Override
    <S extends NinjaModel> S save(S entity);

    @Override
    void deleteById(Long aLong);
}
