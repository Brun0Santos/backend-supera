package br.com.banco.service;

import br.com.banco.dto.TransferenciaDto;
import br.com.banco.entity.TransferenciaEntity;
import br.com.banco.exceptions.OperadorNotFoundException;
import br.com.banco.exceptions.ParserDataException;
import br.com.banco.repository.TransferenciaRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransferenciaService {
    @Autowired
    private TransferenciaRepository repository;

    @Autowired
    private ModelMapper mapper;

    public List<TransferenciaDto> getAllDadosTransferencias() {
        List<TransferenciaEntity> dadosTransferencia = repository.findAll();
        if (dadosTransferencia.isEmpty()) {
            throw new OperadorNotFoundException("Lista de transferencia do operador vazia");
        }
        return dadosTransferencia.stream().map(
                transferencia -> mapper.map(transferencia, TransferenciaDto.class)).collect(Collectors.toList());
    }

    public List<TransferenciaDto> getDadosFromNomeOperador(String nameOperador) {
        Optional<List<TransferenciaEntity>> dadosOperadorTransacao = Optional.of(
                repository.operadorTransacionado(nameOperador));
        if (dadosOperadorTransacao.get().isEmpty()) {
            throw new OperadorNotFoundException("Operador Nao Encontrado");
        }
        return dadosOperadorTransacao.get().stream().map(
                dadosTransferencia -> mapper.map(dadosTransferencia, TransferenciaDto.class)
        ).collect(Collectors.toList());
    }

    public List<TransferenciaDto> getTransferenciaEntreDatas(String dataInicio, String dataFim) {
        try {
            LocalDate inicio = dataParser(dataInicio);
            LocalDate fim = dataParser(dataFim);
            var transferencias = repository.findByTransferenciaEntreDatas(inicio, fim);
            return parserTransferenciaDto(transferencias);
        } catch (DateTimeParseException ex) {
            log.error(ex.getMessage());
            throw new ParserDataException();
        }
    }

    public List<TransferenciaDto> getAllFiltros(String dataInicio, String dataFim, String operador) {
        try {
            LocalDate inicio = dataParser(dataInicio);
            LocalDate fim = dataParser(dataFim);
            var dados = repository.findPorTodosFiltros(inicio, fim, operador);
            return parserTransferenciaDto(dados);
        } catch (ParserDataException ex) {
            log.error(ex.getMessage());
            throw new ParserDataException();
        }
    }

    public LocalDate dataParser(String data) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (data != null) {
            return LocalDate.parse(data, pattern);
        }
        throw new ParserDataException();
    }

    public List<TransferenciaDto> parserTransferenciaDto(List<TransferenciaEntity> transferencias) {
        if (transferencias.isEmpty()) throw new OperadorNotFoundException("Dados entre as datas nao encontrado");
        return transferencias.stream().map(
                        t -> mapper.map(t, TransferenciaDto.class))
                .collect(Collectors.toList());
    }
}
