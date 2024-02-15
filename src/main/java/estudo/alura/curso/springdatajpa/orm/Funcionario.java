package estudo.alura.curso.springdatajpa.orm;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name="funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private BigDecimal salario;
    private LocalDate dataContratacao;
    @ManyToOne
    @JoinColumn(name = "cargo_id")
    private Cargo cargo;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "funcionarios_unidades_trabalho",
            joinColumns = @JoinColumn(name = "funcionario_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name= "unidade_trabalho_id", referencedColumnName = "id")
    )
    private Collection<UnidadeTrabalho> unidadesDeTrabalho;

    public Funcionario() {
        this.unidadesDeTrabalho = new ArrayList<>();
    }

    public Funcionario(String nome, String cpf, BigDecimal salario, LocalDate dataContratacao) {
        this.nome = nome;
        this.cpf = cpf;
        this.salario = salario;
        this.dataContratacao = dataContratacao;
        this.unidadesDeTrabalho = new ArrayList<>();
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {
        this.salario = salario;
    }

    public LocalDate getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Collection<UnidadeTrabalho> getUnidadesDeTrabalho() {
        return unidadesDeTrabalho;
    }

    public void adicionarUnidadeTrabalho(UnidadeTrabalho unidadeTrabalho) {
        unidadeTrabalho.adicionarFuncionario(this);
        this.unidadesDeTrabalho.add(unidadeTrabalho);
    }

    @Override
    public String toString() {

        return "Funcionario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", salario=" + salario +
                ", dataContratacao=" + dataContratacao +
                '}';
    }
}
