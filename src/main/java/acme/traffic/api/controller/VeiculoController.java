package acme.traffic.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import acme.traffic.api.assembler.VeiculoAssembler;
import acme.traffic.api.model.VeiculoModel;
import acme.traffic.api.model.input.VeiculoInput;
import acme.traffic.domain.repository.VeiculoRepository;
import acme.traffic.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
    private final RegistroVeiculoService registroVeiculoService;
    private final VeiculoRepository veiculoRepository;
    private final VeiculoAssembler veiculoAssembler;

    @GetMapping
    public java.util.List<VeiculoModel> listar() {
        return veiculoAssembler.toCollectionModel(veiculoRepository.findAll());
    }

    @GetMapping("/{veiculoId}")
    public ResponseEntity<VeiculoModel> buscar(@PathVariable Long veiculoId) {
        return veiculoRepository.findById(veiculoId)
                .map(veiculoAssembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoModel cadastrar(@Valid @RequestBody VeiculoInput veiculoInput) {
        var novoVeiculo = veiculoAssembler.toEntity(veiculoInput);
        var veiculoCadastrado = registroVeiculoService.cadastrar(novoVeiculo);
        return veiculoAssembler.toModel(veiculoCadastrado);
    }

}
