package br.com.banco.dto;

import br.com.banco.entity.TransferenciaEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ContaDto {
    private Integer id;
    private TransferenciaEntity transferencia;
}
