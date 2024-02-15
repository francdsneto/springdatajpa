package estudo.alura.curso.springdatajpa.orm;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name="unidades_trabalho")
public class UnidadeTrabalho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String endereco;
    @ManyToMany(mappedBy = "unidadesDeTrabalho", fetch = FetchType.EAGER)
    private Collection<Funcionario> funcionarios;

    public UnidadeTrabalho() {
        this.funcionarios = new ArrayList<>();
    }

    public UnidadeTrabalho(String descricao, String endereco) {
        this.descricao = descricao;
        this.endereco = endereco;
        this.funcionarios = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Collection<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void adicionarFuncionario(Funcionario funcionario) {
        this.funcionarios.add(funcionario);
    }

    @Override
    public String toString() {
        return "UnidadeTrabalho{" +
                "id=" + id +
                ", descricao='" + descricao + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
