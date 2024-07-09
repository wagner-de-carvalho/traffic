package acme.traffic.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import acme.traffic.domain.model.Proprietario;

@Repository
public interface ProprietarioRepository extends JpaRepository<Proprietario, Long> {

}
