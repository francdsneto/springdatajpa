package estudo.alura.curso.springdatajpa.repository;

import estudo.alura.curso.springdatajpa.orm.Funcionario;
import estudo.alura.curso.springdatajpa.orm.UnidadeTrabalho;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iUnidadeTrabalhoRepository extends CrudRepository<UnidadeTrabalho,Long> {
}
