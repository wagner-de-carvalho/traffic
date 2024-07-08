package acme.traffic.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Proprietario {
    private Long id;
    private String nome;
    private String email;
    private String telefone;
}
