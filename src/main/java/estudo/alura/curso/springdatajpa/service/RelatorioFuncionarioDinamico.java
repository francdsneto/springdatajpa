package estudo.alura.curso.springdatajpa.service;

import estudo.alura.curso.springdatajpa.orm.Funcionario;
import estudo.alura.curso.springdatajpa.repository.iFuncionarioRepository;
import estudo.alura.curso.springdatajpa.specification.SpecificationFuncionario;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioFuncionarioDinamico {

    private final iFuncionarioRepository funcionarioRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RelatorioFuncionarioDinamico(iFuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {
        System.out.println("Digite o nome:");
        String nome = scanner.nextLine();
        System.out.println("Digite o cpf:");
        String cpf = scanner.nextLine();
        System.out.println("Digite o salario:");
        BigDecimal salario = scanner.nextBigDecimal();
        scanner.nextLine();
        System.out.println("Digite a Data de Contratacao:");
        String dataContratacaoTxt = scanner.nextLine();
        LocalDate dataContratacao = null;

        if(nome.equalsIgnoreCase("null"))
        {
            nome = null;
        }

        if(cpf.equalsIgnoreCase("null"))
        {
            cpf = null;
        }

        if(salario.compareTo(BigDecimal.ZERO) == 0)
        {
            salario = null;
        }

        if(!dataContratacaoTxt.equalsIgnoreCase("null"))
        {
            dataContratacao = LocalDate.parse(dataContratacaoTxt,formatter);
        }

        List<Funcionario> funcionarios = funcionarioRepository.findAll(
                Specification
                        .where(
                                SpecificationFuncionario
                                        .nome(nome)
                                        .and(SpecificationFuncionario.cpf(cpf))
                                        .and(SpecificationFuncionario.salario(salario))
                                        .and(SpecificationFuncionario.dataContratacao((dataContratacao)))
                        )
        );

        funcionarios.forEach(System.out::println);
    }
}
