package estudo.alura.curso.springdatajpa.service;

import estudo.alura.curso.springdatajpa.orm.Cargo;
import estudo.alura.curso.springdatajpa.repository.iCargoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class CrudCargoService {

    private boolean system = true;

    private final iCargoRepository cargoRepository;

    public CrudCargoService(iCargoRepository cargoRepository) {
        this.cargoRepository = cargoRepository;
    }

    public void inicial(Scanner scanner) {

        while(system) {
            System.out.println("""
                    Qual ação de cargo você quer executar?
                    			
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
        System.out.println("Descricao do cargo");
        String descricao = scanner.nextLine();
        Cargo cargo = new Cargo();
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Salvo");
    }

    private void atualizar(Scanner scanner) {

        System.out.println("Cargos cadastrados:");
        this.visualizar();
        System.out.println("Digite o id do cargo que deseja atualizar:");
        Long id = scanner.nextLong();
        scanner.nextLine();
        Cargo cargo = new Cargo();
        System.out.println("Qual descrição deseja inserir neste cargo?");
        String descricao = scanner.nextLine();
        cargo.setId(id);
        cargo.setDescricao(descricao);
        cargoRepository.save(cargo);
        System.out.println("Atualizado");

    }

    private void visualizar() {
        Iterable<Cargo> cargos = this.cargoRepository.findAll();
        cargos.forEach(System.out::println);
    }

    private void deletar(Scanner scanner) {
        this.visualizar();
        System.out.println("Qual o id do cargo que você deseja deletar?");
        Long id = scanner.nextLong();
        scanner.nextLine();
        this.cargoRepository.deleteById(id);
        System.out.println("Deletado");
    }

    public Optional<Cargo> buscarPorId(Scanner scanner) {
        this.visualizar();
        System.out.println("Qual cargo deseja retornar?");
        Long id = scanner.nextLong();
        return this.cargoRepository.findById(id);
    }

}
