package acme.traffic.domain.service;

import java.time.OffsetDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import acme.traffic.domain.exception.NegocioException;
import acme.traffic.domain.model.StatusVeiculo;
import acme.traffic.domain.model.Veiculo;
import acme.traffic.domain.repository.VeiculoRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroVeiculoService {
    private final VeiculoRepository veiculoRepository;
    private final RegistroProprietarioService registroProprietarioService;

    public Veiculo buscar(Long veiculoId) {
        return veiculoRepository.findById(veiculoId)
                .orElseThrow(() -> new NegocioException("Veículo não encontrado"));
    }

    @Transactional
    public Veiculo cadastrar(Veiculo novoVeiculo) {
        if (novoVeiculo.getId() != null) {
            throw new NegocioException("Veículo a ser cadastrado não deve possuir um código");
        }
        boolean placaEmUso = veiculoRepository.findByPlaca(novoVeiculo.getPlaca())
                .filter(veiculo -> !veiculo.equals(novoVeiculo))
                .isPresent();

        if (placaEmUso) {
            throw new NegocioException("Placa já em uso");
        }

        var proprietario = registroProprietarioService.buscar(novoVeiculo.getProprietario().getId());
        novoVeiculo.setProprietario(proprietario);
        novoVeiculo.setStatus(StatusVeiculo.REGULAR);
        novoVeiculo.setDataCadastro(OffsetDateTime.now());
        return veiculoRepository.save(novoVeiculo);
    }
}
