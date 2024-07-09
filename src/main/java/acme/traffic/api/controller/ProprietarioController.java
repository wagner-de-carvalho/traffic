package acme.traffic.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import acme.traffic.domain.model.Proprietario;
import acme.traffic.domain.repository.ProprietarioRepository;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@RequestMapping("/proprietarios")
@RestController
public class ProprietarioController {

    private final ProprietarioRepository proprietarioRepository;

    @GetMapping
    public List<Proprietario> listar() {
        return proprietarioRepository.findAll();
    }

    @GetMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> buscar(@PathVariable Long proprietarioId) {
        return proprietarioRepository.findById(proprietarioId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proprietario adicionar(@RequestBody Proprietario proprietario) {
        return proprietarioRepository.save(proprietario);
    }

    @PutMapping("/{proprietarioId}")
    public ResponseEntity<Proprietario> atualizar(@PathVariable Long proprietarioId,
            @RequestBody Proprietario proprietario) {

        if (proprietarioRepository.existsById(proprietarioId)) {
            proprietario.setId(proprietarioId);
            var proprietarioAtualizado = proprietarioRepository.save(proprietario);
            return ResponseEntity.ok(proprietarioAtualizado);
        }
        return ResponseEntity.notFound().build();
    }
}
