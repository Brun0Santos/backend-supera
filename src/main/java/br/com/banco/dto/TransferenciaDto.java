package br.com.banco.dto;

import br.com.banco.entity.ContaEntity;
import br.com.banco.enums.Tipo;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TransferenciaDto {

    private Integer id;

    private LocalDate dataTransferencia;

    private BigDecimal valor;

    private Tipo tipo;

    private String nomeOperadorTransacao;

    private ContaEntity conta;
}