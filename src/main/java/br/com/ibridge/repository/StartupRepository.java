package br.com.ibridge.repository;

import br.com.ibridge.model.Startup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StartupRepository extends JpaRepository<Startup,Integer> {

    List<Startup> findByNomeContainsIgnoreCase(String nome);
    List<Startup> findAllByUsuarioId(int id);
}
