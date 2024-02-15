package estudo.alura.curso.springdatajpa.specification;

import estudo.alura.curso.springdatajpa.orm.Funcionario;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SpecificationFuncionario {

    public static Specification<Funcionario> nome(String nome) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if(nome == null)
            {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            else
            {
                return criteriaBuilder.like(root.get("nome"),"%"+nome+"%");
            }
        };
    }

    public static Specification<Funcionario> cpf(String cpf) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if(cpf == null)
            {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            else
            {
                return criteriaBuilder.equal(root.get("cpf"),cpf);
            }
        };
    }

    public static Specification<Funcionario> salario(BigDecimal salario) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if(salario == null)
            {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            else
            {
                return criteriaBuilder.greaterThan(root.get("salario"),salario);
            }
        };
    }

    public static Specification<Funcionario> dataContratacao(LocalDate dataContratacao) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            if(dataContratacao == null)
            {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            else
            {
                return criteriaBuilder.greaterThan(root.get("dataContratacao"),dataContratacao);
            }
        };
    }

}
