package acme.traffic.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import acme.traffic.domain.model.Proprietario;
import org.springframework.web.bind.annotation.GetMapping;

@RequestMapping("/proprietarios")
@RestController
public class ProprietarioController {

    @GetMapping
    public List<Proprietario> listar() {
        var p1 = new Proprietario(1L, "Carlos", "carlos@mail.com", "11 999547891");
        var p2 = new Proprietario(2L, "Maria", "maria@mail.com", "45 555412785");
        return Arrays.asList(p1, p2);
    }
}
