package entities;

import enums.Sexo;
import enums.Tipo;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Pet{
    private String nome;
    private Tipo tipo;
    private Sexo sexo;
    private String numeroCasa;
    private String cidade;
    private String rua;
    private String idade;
    private String peso;
    private String raca;

    static ArrayList<Pet> animaisCadastrados = new ArrayList<>();

    private final String NAO_INFORMADO = "NÃO INFORMADO";

    public Pet(String nome, Tipo tipo, Sexo sexo, String rua, String numeroCasa, String cidade,  String idade, String peso, String raca) {
        Pattern p = Pattern.compile("^[^A-Za-z\\s]+$");
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

    public static void addPet(Pet pet){
        animaisCadastrados.add(pet);
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo.getType();
    }

    public String getSexo() {
        return sexo.toString();
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

    public static ArrayList<Pet> getAnimaisCadastrados() {
        return animaisCadastrados;
    }
    public String getRaca() {
        return raca;
    }

    public String toString() {
        return "1 - "+nome+System.lineSeparator()+"2 - "+tipo+System.lineSeparator()+"3 - "+sexo+System.lineSeparator()+"4 - "+getEndereco()+System.lineSeparator()+"5 - "+getIdade()+System.lineSeparator()+"6 - "+getPeso()+System.lineSeparator()+"7 - "+getRaca();
    }
    public static void toStringList(){
        for (Pet pet : animaisCadastrados){
            System.out.println((animaisCadastrados.indexOf(pet)+1)+". " +pet.getNome()+" - "+pet.getTipo()+" - "+pet.getSexo()+" - "+pet.getEndereco()+" - "+pet.getIdade()+" - "+pet.getPeso()+" - "+pet.getRaca());
        }
    }
}
