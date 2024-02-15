package estudo.alura.curso.springdatajpa.service;

import estudo.alura.curso.springdatajpa.orm.Cargo;
import estudo.alura.curso.springdatajpa.orm.Funcionario;
import estudo.alura.curso.springdatajpa.orm.UnidadeTrabalho;
import estudo.alura.curso.springdatajpa.repository.iFuncionarioRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudFuncionarioService {

    private boolean system = true;

    private final iFuncionarioRepository funcionarioRepository;
    private final CrudCargoService crudCargoService;
    private final CrudUnidadeTrabalhoService crudUnidadeTrabalhoService;

    public CrudFuncionarioService(iFuncionarioRepository funcionarioRepository, 
                                  CrudCargoService crudCargoService,
                                  CrudUnidadeTrabalhoService crudUnidadeTrabalhoService ) {
        this.funcionarioRepository = funcionarioRepository;
        this.crudCargoService = crudCargoService;
        this.crudUnidadeTrabalhoService = crudUnidadeTrabalhoService;
    }

    public void inicial(Scanner scanner) {

        while(system) {
            System.out.println("""
                    Qual ação de funcionario você quer executar?
                    			
                    0 - Sair
                    1 - Salvar
                    2 - Atualizar    
                    3 - Visualizar     
                    4 - Deletar           			
                    """);

            int action = scanner.nextInt();
            scanner.nextLine();

            switch (action) {
                case 1: {
                    this.salvar(scanner);
                    break;
                }
                case 2: {
                    this.atualizar(scanner);
                    break;
                }
                case 3: {
                    this.visualizar();
                    break;
                }
                case 4: {
                    this.deletar(scanner);
                    break;
                }
                default: {
                    system = false;
                    break;
                }
            }
        }
    }

    private void salvar(Scanner scanner) {
        String nome = null;
        String cpf = null;
        BigDecimal salario = null;
        System.out.println("Nome do funcionario");
        nome = scanner.nextLine();
        System.out.println("CPF do funcionario");
        cpf = scanner.nextLine();
        System.out.println("Salário do funcionario");
        salario = scanner.nextBigDecimal();
        Funcionario funcionario = new Funcionario(nome,cpf,salario,LocalDate.now());
        Optional<Cargo> cargoOpt = this.crudCargoService.buscarPorId(scanner);
        cargoOpt.ifPresent(funcionario::setCargo);
        Optional<UnidadeTrabalho> unidadeTrabalhoOpt = this.crudUnidadeTrabalhoService.buscarPorId(scanner);
        unidadeTrabalhoOpt.ifPresent(funcionario::adicionarUnidadeTrabalho);
        funcionarioRepository.save(funcionario);
        System.out.println("Salvo");
    }

    private void atualizar(Scanner scanner) {

        System.out.println("Funcionarios cadastrados:");
        this.visualizar();
        System.out.println("Digite o id do funcionario que deseja atualizar:");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Optional<Funcionario> funcionarioOpt = this.funcionarioRepository.findById(id);
        if(funcionarioOpt.isPresent())
        {
            Funcionario funcionario = funcionarioOpt.get();
            String nome = null;
            String cpf = null;
            BigDecimal salario = null;
            System.out.println("Nome do funcionario");
            nome = scanner.nextLine();
            System.out.println("CPF do funcionario");
            cpf = scanner.nextLine();
            System.out.println("Salário do funcionario");
            salario = scanner.nextBigDecimal();
            funcionario.setId(id);
            funcionario.setNome(nome);
            funcionario.setCpf(cpf);
            funcionario.setSalario(salario);
            funcionarioRepository.save(funcionario);
            System.out.println("Atualizado");
        }


    }

    private void visualizar() {
        Iterable<Funcionario> funcionarios = this.funcionarioRepository.findAll();
        funcionarios.forEach(System.out::println);
    }

    private void deletar(Scanner scanner) {
        this.visualizar();
        System.out.println("Qual o id do funcionario que você deseja deletar?");
        Long id = scanner.nextLong();
        scanner.nextLine();
        this.funcionarioRepository.deleteById(id);
        System.out.println("Deletado");
    }

}
