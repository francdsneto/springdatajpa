package estudo.alura.curso.springdatajpa.service;

import estudo.alura.curso.springdatajpa.orm.Cargo;
import estudo.alura.curso.springdatajpa.orm.UnidadeTrabalho;
import estudo.alura.curso.springdatajpa.repository.iUnidadeTrabalhoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudUnidadeTrabalhoService {

    private boolean system = true;

    private final iUnidadeTrabalhoRepository unidadeTrabalhoRepository;

    public CrudUnidadeTrabalhoService(iUnidadeTrabalhoRepository unidadeTrabalhoRepository) {
        this.unidadeTrabalhoRepository = unidadeTrabalhoRepository;
    }

    public void inicial(Scanner scanner) {

        while(system) {
            System.out.println("""
                    Qual ação de Unidade de Trabalho você quer executar?
                    			
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
        String descricao = null;
        String endereco = null;
        System.out.println("Descrição da Unidade de Trabalho");
        descricao = scanner.nextLine();
        System.out.println("Endereço da Unidade de Trabalho");
        endereco = scanner.nextLine();
        UnidadeTrabalho unidadeTrabalho = new UnidadeTrabalho(descricao,endereco);
        unidadeTrabalhoRepository.save(unidadeTrabalho);
        System.out.println("Salvo");
    }

    private void atualizar(Scanner scanner) {

        System.out.println("Unidades de Trabalho cadastradas:");
        this.visualizar();
        System.out.println("Digite o id da Unidade de Trabalho que deseja atualizar:");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Optional<UnidadeTrabalho> unidadeTrabalhoOpt = this.unidadeTrabalhoRepository.findById(id);
        if(unidadeTrabalhoOpt.isPresent())
        {
            UnidadeTrabalho unidadeTrabalho = unidadeTrabalhoOpt.get();
            String descricao = null;
            String endereco = null;
            System.out.println("Descrição da Unidade de Trabalho");
            descricao = scanner.nextLine();
            System.out.println("Endereço da Unidade de Trabalho");
            endereco = scanner.nextLine();
            unidadeTrabalho.setDescricao(descricao);
            unidadeTrabalho.setEndereco(endereco);
            unidadeTrabalhoRepository.save(unidadeTrabalho);
            System.out.println("Atualizado");
        }

    }

    private void visualizar() {
        Iterable<UnidadeTrabalho> unidadeTrabalhos = this.unidadeTrabalhoRepository.findAll();
        unidadeTrabalhos.forEach(System.out::println);
    }

    private void deletar(Scanner scanner) {
        this.visualizar();
        System.out.println("Qual o id da Unidade de Trabalho que você deseja deletar?");
        Long id = scanner.nextLong();
        scanner.nextLine();
        this.unidadeTrabalhoRepository.deleteById(id);
        System.out.println("Deletada");
    }

    public Optional<UnidadeTrabalho> buscarPorId(Scanner scanner) {
        this.visualizar();
        System.out.println("Qual Unidade de Trabalho deseja retornar?");
        Long id = scanner.nextLong();
        return this.unidadeTrabalhoRepository.findById(id);
    }

}
