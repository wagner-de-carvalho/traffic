package acme.traffic.api.model;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VeiculoModel {
    private Long id;
    private ProprietarioResumoModel proprietario;
    private String marca;
    private String modelo;
    private String placa;
    private OffsetDateTime dataCadastro;
    private OffsetDateTime dataApreensao;
}
