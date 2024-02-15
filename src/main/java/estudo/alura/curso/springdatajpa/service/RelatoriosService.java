package estudo.alura.curso.springdatajpa.service;

import estudo.alura.curso.springdatajpa.orm.Funcionario;
import estudo.alura.curso.springdatajpa.repository.iFuncionarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatoriosService {

    private final iFuncionarioRepository funcionarioRepository;
    private boolean system = true;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RelatoriosService(iFuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {

        while(system) {
            System.out.println("""
                    Qual ação de cargo você quer executar?
                    			
                    0 - Sair
                    1 - Buscar funcionário por nome   
                    2 - Buscar funcionario por nome, data de contratação e salário maior que...    
                    3 - Buscar funcionários com data de contratação maior que...			
                    """);

            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 1: {
                    this.buscaFuncionarioPorNome(scanner);
                    break;
                }
                case 2: {
                    this.buscaFuncionariosNomeDataContratacaoSalarioMaiorQue(scanner);
                    break;
                }
                case 3: {
                    this.buscarFuncionariosPorDataDeContratacaoMaiorQue(scanner);
                    break;
                }
                default: {
                    system = false;
                    break;
                }
            }
        }
    }

    private void buscaFuncionarioPorNome(Scanner scanner) {
        System.out.println("Digite o nome do funcionário que você deseja buscar:");
        String nome = scanner.nextLine();
        List<Funcionario> funcionarios = this.funcionarioRepository.findByNome(nome);
        funcionarios.forEach(System.out::println);
    }

    private void buscaFuncionariosNomeDataContratacaoSalarioMaiorQue(Scanner scanner) {
        System.out.println("Qual o nome do funcionario que deseja buscar?");
        String nome = scanner.nextLine();
        System.out.println("Qual a data de contratacao?");
        String data = scanner.nextLine();
        System.out.println("O salário deve ser maior que qual valor?");
        BigDecimal salario = scanner.nextBigDecimal();
        LocalDate dataContratacao = LocalDate.parse(data, formatter);
        List<Funcionario> funcionarios = this.funcionarioRepository.findByNomeDataContratacaoSalarioMaiorQue(nome, dataContratacao, salario);
        funcionarios.forEach(System.out::println);
    }

    private void buscarFuncionariosPorDataDeContratacaoMaiorQue(Scanner scanner) {
        System.out.println("Qual a data de contratacao de referência?");
        String data = scanner.nextLine();
        LocalDate dataContratacao = LocalDate.parse(data, formatter);
        List<Funcionario> funcionarios = this.funcionarioRepository.funcionarioPorDataContratacaoMaiorQue(dataContratacao);
        funcionarios.forEach(System.out::println);
    }

}
