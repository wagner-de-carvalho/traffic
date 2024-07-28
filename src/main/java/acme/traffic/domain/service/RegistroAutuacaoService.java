package acme.traffic.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import acme.traffic.domain.model.Autuacao;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroAutuacaoService {
    private RegistroVeiculoService registroVeiculoService;

    @Transactional
    public Autuacao registrar(Long veiculoId, Autuacao novaAutuacao) {
        var veiculo = registroVeiculoService.buscar(veiculoId);
        return veiculo.adicionarAutuacao(novaAutuacao);
    }
}
