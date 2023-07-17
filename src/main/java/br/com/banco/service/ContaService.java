package br.com.banco.service;

import br.com.banco.dto.ContaDto;
import br.com.banco.entity.TransferenciaEntity;
import br.com.banco.exceptions.IdContaNotFoundException;
import br.com.banco.repository.TransferenciaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContaService {
    @Autowired
    private TransferenciaRepository transferenciaRepository;

    @Autowired
    private ModelMapper mapper;

    public List<ContaDto> getDadosTransferencia(Integer id) {
        Optional<List<TransferenciaEntity>> conta = transferenciaRepository.findContaBancaria(id);
        if (conta.get().isEmpty()) {
            throw new IdContaNotFoundException();
        }
        return conta.get().stream().map(transferencia -> mapper.map(
                transferencia, ContaDto.class)).collect(Collectors.toList());
    }
}
