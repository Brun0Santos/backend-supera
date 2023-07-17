package br.com.banco.repository;

import br.com.banco.entity.TransferenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransferenciaRepository extends JpaRepository<TransferenciaEntity, Integer> {

    @Query("SELECT t FROM TransferenciaEntity t WHERE t.dataTransferencia BETWEEN :dataInicio AND :dataFim")
    List<TransferenciaEntity> findByTransferenciaEntreDatas(@Param("dataInicio") LocalDate dataInicio,
                                                            @Param("dataFim") LocalDate dataFim);

    @Query("SELECT t FROM TransferenciaEntity t WHERE t.nomeOperadorTransacao = :nomeOperador")
    List<TransferenciaEntity> findNomeOperadorTransacao(@Param("nomeOperador") String nomeOperador);

    @Query("SELECT t FROM TransferenciaEntity t WHERE t.conta.id = :contaIdd")
    Optional<List<TransferenciaEntity>> findContaBancaria(@Param("contaIdd") Integer contaIdd);

    @Query(nativeQuery = true, value = "SELECT t.*, c.NOME_RESPONSAVEL "
            + "FROM TRANSFERENCIA t "
            + "INNER JOIN CONTA c "
            + "ON t.CONTA_ID=c.ID_CONTA "
            + "WHERE DATA_TRANSFERENCIA >= :dataInicio AND DATA_TRANSFERENCIA  <= :dataFim "
            + "AND NOME_RESPONSAVEL = :operadorTransacionado")
    List<TransferenciaEntity> findPorTodosFiltros(@Param("dataInicio") LocalDate dataInicio,
                                                  @Param("dataFim") LocalDate dataFim,
                                                  @Param("operadorTransacionado") String operadorTransacionado);

    @Query(nativeQuery = true, value = "SELECT t.*, c.NOME_RESPONSAVEL "
            + "FROM TRANSFERENCIA t "
            + "INNER JOIN CONTA c "
            + "ON t.CONTA_ID=c.ID_CONTA "
            + "WHERE NOME_RESPONSAVEL = :operadorTransacionado ")
    List<TransferenciaEntity> operadorTransacionado(@Param("operadorTransacionado") String operadorTransacionado);
}
