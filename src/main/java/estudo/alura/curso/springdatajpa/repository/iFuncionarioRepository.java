package estudo.alura.curso.springdatajpa.repository;

import estudo.alura.curso.springdatajpa.orm.Cargo;
import estudo.alura.curso.springdatajpa.orm.Funcionario;
import estudo.alura.curso.springdatajpa.orm.FuncionarioProjecao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface iFuncionarioRepository extends JpaRepository<Funcionario,Long>, JpaSpecificationExecutor<Funcionario> {

    List<Funcionario> findByNome(String nome);

    /**
     * Método usando derived query
     * @param nome
     * @param dataContratacao
     * @param salario
     * @return
     */
//    List<Funcionario> findByNomeAndDataContratacaoAndSalarioGreaterThan(String nome, LocalDate dataContratacao, BigDecimal salario);

    @Query("SELECT f FROM Funcionario f WHERE f.nome like :nome AND f.dataContratacao = :dataContratacao AND f.salario >= :salario")
    List<Funcionario> findByNomeDataContratacaoSalarioMaiorQue(String nome, LocalDate dataContratacao, BigDecimal salario);


    /**
     * Observações
     *
     * 1 - Caso fosse necessário buscar algo dentro de uma classe, como cargo, poderia se usar
     *     uma busca no seguinte padrão - findByCargoDescricao(String descricao)
     *
     * 2 - Como unidade de trabalho é uma classe onde o nome é composto por 2 palavras, utiliza-se
     *     _ para separa a classe do atributo na consulta - findByUnidadeTrabalho_Descricao(String descricao)
     */

    @Query(value = "SELECT f.* FROM Funcionarios f WHERE f.data_contratacao >= :dataReferencia", nativeQuery = true)
    List<Funcionario> funcionarioPorDataContratacaoMaiorQue(LocalDate dataReferencia);

    @Query(value = "SELECT f.id, f.nome, f.salario FROM funcionarios f",
           nativeQuery = true)
    List<FuncionarioProjecao> findFuncionarioSalario();

}
