package acme.traffic.api.model.input;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutuacaoInput {
    @NotBlank
    private String descricao;

    @NotBlank
    @Positive
    private BigDecimal valorMulta;

}
