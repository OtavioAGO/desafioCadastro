package entities;

import enums.Sexo;
import enums.Tipo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pet {
    private String nome;
    private Tipo tipo;
    private Sexo sexo;
    private String numeroCasa;
    private String cidade;
    private String rua;
    private String idade;
    private String peso;
    private String raca;

    private final String NAO_INFORMADO = "NÃO INFORMADO";

    public Pet(String nome, Tipo tipo, Sexo sexo, String numeroCasa, String cidade, String rua, String idade, String peso, String raca) {
        Pattern p = Pattern.compile("[\\W\\d]");
        Matcher caractereEspecial = p.matcher(nome);
        if (caractereEspecial.find()) {
            throw new IllegalArgumentException("ERRO - Caractere invalido");
        }
        this.nome = nome.trim().isEmpty() ? NAO_INFORMADO : nome;
        this.tipo = tipo;
        this.sexo = sexo;
        this.numeroCasa = numeroCasa.trim().isEmpty() ? NAO_INFORMADO : numeroCasa;
        this.cidade = cidade;
        this.rua = rua;
        if (Float.parseFloat(idade) > 20 || Float.parseFloat(idade) < 0) {
            throw new IllegalArgumentException("ERRO - Idade Invalida!");
        }
        this.idade = idade.trim().isEmpty() ? NAO_INFORMADO : idade;
        if (!idade.equals(NAO_INFORMADO) && Float.parseFloat(idade) < 1) {
            this.idade = idade + " anos";
        }
        peso = peso.replace(',', '.');
        if (Float.parseFloat(peso) > 60 || Float.parseFloat(peso) < 0.5) {
            throw new IllegalArgumentException("ERRO - Peso Invalido!");
        }
        this.peso = peso.trim().isEmpty() ? NAO_INFORMADO : peso;
        this.raca = raca.trim().isEmpty() ? NAO_INFORMADO : raca;
    }

    public String getNome() {
        return nome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public Sexo getSexo() {
        return sexo;
    }
    public String getEndereco(){
        return rua +", " + numeroCasa + ", " + cidade;
    }
    public String getIdade() {
        return idade+" anos";
    }

    public String getPeso() {
        return peso+"kg";
    }

    public String getRaca() {
        return raca;
    }

    @Override
    public String toString() {
        return "1 - "+nome+"\n2 - "+tipo+"\n3 - "+sexo+"\n4 - "+getEndereco()+"\n5 - "+getIdade()+"\n6 - "+getPeso()+"\n7 - "+getRaca();
    }
}
