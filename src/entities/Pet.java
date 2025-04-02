package entities;

import enums.Sexo;
import enums.Tipo;

import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.Scanner;
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
    private final Pattern PADRAO_NOME = Pattern.compile("^[^A-Za-z\\s]+$");
    private final String NAO_INFORMADO = "NÃO INFORMADO";

    public Pet(String nome, Tipo tipo, Sexo sexo, String rua, String numeroCasa, String cidade,  String idade, String peso, String raca) {
        Matcher caractereEspecial = PADRAO_NOME.matcher(nome);
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

    public static void alteraPet(Pet pet){
        int index = animaisCadastrados.indexOf(pet);
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Qual critério mudar:");
            System.out.println("1-Nome\n2-Idade\n3-Peso\n4-Raça\n5-Endereço\n6-Finalizar");
            int n = sc.nextInt();
            sc.nextLine();
            if (n == 1){
                System.out.println("Nome atualizado: ");
                String nome = sc.nextLine();
                pet.setNome(nome);
            }else if (n == 2){
                System.out.println("Idade atualizada: ");
                String idade = sc.nextLine();
                pet.setIdade(idade);
            }
            else if (n == 3){
                System.out.println("Peso atualizado:");
                String peso = sc.nextLine();
                pet.setPeso(peso);
            }else if (n == 4){
                System.out.println("Raça atualizada:");
                String raca = sc.nextLine();
                pet.setRaca(raca);
            }else if (n == 5){
                System.out.println("Endereço atualizado:");
                System.out.print("Rua: ");
                String rua = sc.nextLine();
                pet.setRua(rua);
                System.out.print("\nNumero: ");
                String numeroCasa = sc.nextLine();
                pet.setNumeroCasa(numeroCasa);
                System.out.print("\nCidade: ");
                String cidade = sc.nextLine();
                pet.setCidade(cidade);
            }else if (n == 6){
                break;
            }
            animaisCadastrados.set(index, pet);
        }
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

    public void setNome(String nome) {
        Matcher caractereEspecial = PADRAO_NOME.matcher(nome);
        if (caractereEspecial.find()) {
            throw new IllegalArgumentException("ERRO - Caractere invalido");
        }
        this.nome = nome.trim().isEmpty() ? NAO_INFORMADO : nome;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa.trim().isEmpty() ? NAO_INFORMADO : numeroCasa;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setIdade(String idade) {
        if (Float.parseFloat(idade) > 20 || Float.parseFloat(idade) < 0) {
            throw new IllegalArgumentException("ERRO - Idade Invalida!");
        }
        this.idade = idade.trim().isEmpty() ? NAO_INFORMADO : idade;
    }

    public void setPeso(String peso) {
        peso = peso.replace(',', '.');
        if (Float.parseFloat(peso) > 60 || Float.parseFloat(peso) < 0.5) {
            throw new IllegalArgumentException("ERRO - Peso Invalido!");
        }
        this.peso = peso.trim().isEmpty() ? NAO_INFORMADO : peso;
    }

    public void setRaca(String raca) {
        this.raca = raca.trim().isEmpty() ? NAO_INFORMADO : raca;
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
