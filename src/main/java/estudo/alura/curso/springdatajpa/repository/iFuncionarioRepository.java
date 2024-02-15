package estudo.alura.curso.springdatajpa.repository;

import estudo.alura.curso.springdatajpa.orm.Cargo;
import estudo.alura.curso.springdatajpa.orm.Funcionario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iFuncionarioRepository extends CrudRepository<Funcionario,Long> {
}
