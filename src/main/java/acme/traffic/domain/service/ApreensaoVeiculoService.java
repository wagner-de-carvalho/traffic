package acme.traffic.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ApreensaoVeiculoService {
    private final RegistroVeiculoService registroVeiculoService;

    @Transactional
    public void apreender(Long veiculo_id) {
        var veiculo = registroVeiculoService.buscar(veiculo_id);
        veiculo.apreender();
    }

    @Transactional
    public void removerApreensao(Long veiculo_id) {
        var veiculo = registroVeiculoService.buscar(veiculo_id);
        veiculo.removerApreesao();
    }

}
