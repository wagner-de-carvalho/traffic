package acme.traffic.api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import acme.traffic.domain.model.Proprietario;
import acme.traffic.domain.repository.ProprietarioRepository;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@RequestMapping("/proprietarios")
@RestController
public class ProprietarioController {

    private final ProprietarioRepository proprietarioRepository;;

    @GetMapping
    public List<Proprietario> listar() {
        return proprietarioRepository.findAll();
    }
}
