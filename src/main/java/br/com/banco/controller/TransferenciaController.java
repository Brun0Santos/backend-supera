package br.com.banco.controller;

import br.com.banco.dto.TransferenciaDto;
import br.com.banco.service.TransferenciaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/transferencia")
public class TransferenciaController {
    @Autowired
    private TransferenciaService service;

    @GetMapping
    @Operation(summary = "Dados da transferencia", description = "Buscar todos os dados")
    public ResponseEntity<List<TransferenciaDto>> todosDadosTransferencia() {
        var allDadosTransferencias = service.getAllDadosTransferencias();
        return ResponseEntity.ok().body(allDadosTransferencias);
    }

    @GetMapping("/data/{dataInicio}/{dataFim}")
    @Operation(summary = "Dados de transferencias por datas", description = "Buscar todos os dados em um periodo de tempo")
    public ResponseEntity<List<TransferenciaDto>> getTransferenciaEntreDatas(@PathVariable("dataInicio") String dataInicio,
                                                                             @PathVariable("dataFim") String dataFim) {
        var transferenciaEntreDatas = service.getTransferenciaEntreDatas(dataInicio, dataFim);
        return ResponseEntity.ok().body(transferenciaEntreDatas);
    }

    @GetMapping("/data/{dataInicio}/{dataFim}/{operador}")
    @Operation(summary = "Dados de transferencias por datas", description = "Buscar todos os dados em um intervalo de tempo de um operador")
    public ResponseEntity<List<TransferenciaDto>> getAllFiltros(@PathVariable("dataInicio") String dataInicio,
                                                                @PathVariable("dataFim") String dataFim,
                                                                @PathVariable("operador") String operador) {
        var dadosEntreDatasOperador = service.getAllFiltros(dataInicio, dataFim, operador);
        return ResponseEntity.ok().body(dadosEntreDatasOperador);
    }

    @GetMapping("/operador/{nomeOperador}")
    @Operation(summary = "Dados do operador", description = "Buscar dados por um operador especifico")
    public ResponseEntity<List<TransferenciaDto>> getDadosOperador(@PathVariable("nomeOperador") String nomeOperador) {
        List<TransferenciaDto> operadorTransferencias = service.getDadosFromNomeOperador(nomeOperador);
        return ResponseEntity.ok().body(operadorTransferencias);
    }
}