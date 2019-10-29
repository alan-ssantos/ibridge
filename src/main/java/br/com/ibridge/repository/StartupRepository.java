package br.com.ibridge.repository;

import br.com.ibridge.model.Startup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StartupRepository extends JpaRepository<Startup,Integer> {
}
