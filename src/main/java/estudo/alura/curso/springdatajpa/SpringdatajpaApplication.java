package estudo.alura.curso.springdatajpa;

import estudo.alura.curso.springdatajpa.orm.Cargo;
import estudo.alura.curso.springdatajpa.repository.iCargoRepository;
import estudo.alura.curso.springdatajpa.service.CrudCargoService;
import estudo.alura.curso.springdatajpa.service.CrudFuncionarioService;
import estudo.alura.curso.springdatajpa.service.CrudUnidadeTrabalhoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class SpringdatajpaApplication implements CommandLineRunner {

	private Boolean system = true;
	private final CrudCargoService cargoService;
	private final CrudUnidadeTrabalhoService unidadeTrabalhoService;
	private final CrudFuncionarioService funcionarioService;

	public SpringdatajpaApplication(CrudCargoService cargoService,
									CrudUnidadeTrabalhoService unidadeTrabalhoService,
									CrudFuncionarioService funcionarioService) {
		this.cargoService = cargoService;
		this.unidadeTrabalhoService = unidadeTrabalhoService;
		this.funcionarioService = funcionarioService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringdatajpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Scanner scanner = new Scanner(System.in);

		while(system)
		{
			System.out.println("""
			Qual ação você quer executar?
			
			0 - Sair
			1 - Cargo
			2 - Unidade de Trabalho
			3 - Funcionario		
			""");

			int action = scanner.nextInt();
			scanner.nextLine();

			switch (action) {
				case  1 : {
					cargoService.inicial(scanner);
					break;
				}
				case  2 : {
					unidadeTrabalhoService.inicial(scanner);
					break;
				}
				case  3 : {
					funcionarioService.inicial(scanner);
					break;
				}
				default: {
					system = false;
					break;
				}
			}

		}


	}
}
