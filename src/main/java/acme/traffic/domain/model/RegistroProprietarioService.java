package acme.traffic.domain.model;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import acme.traffic.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroProprietarioService {
    private final ProprietarioRepository proprietarioRepository;

    @Transactional
    public Proprietario gravar(Proprietario proprietario) {
        return proprietarioRepository.save(proprietario);
    }

    @Transactional
    public void excluir(Long proprietarioId) {
        proprietarioRepository.deleteById(proprietarioId);
    }
}
