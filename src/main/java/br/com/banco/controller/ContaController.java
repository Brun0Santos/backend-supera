package br.com.banco.controller;

import br.com.banco.dto.ContaDto;
import br.com.banco.service.ContaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class ContaController {
    @Autowired
    ContaService service;

    @Operation(summary = "Buscar dados da conta", description = "Buscar pelo id da conta bancaria")
    @GetMapping(value = "/conta", params = "idcontabancaria")
    public ResponseEntity<List<ContaDto>> getDadosTransferenciaFromIdConta(@Valid @RequestParam("idcontabancaria")
                                                                           Integer idcontabancaria) {
        var dadosTransferencia = service.getDadosTransferencia(idcontabancaria);
        return ResponseEntity.ok().body(dadosTransferencia);
    }
}
