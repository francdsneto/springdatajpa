package estudo.alura.curso.springdatajpa.repository;

import estudo.alura.curso.springdatajpa.orm.Cargo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface iCargoRepository extends CrudRepository<Cargo,Long> {
}
