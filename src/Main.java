
import entities.Pet;
import enums.Sexo;
import enums.Tipo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true){
            exibeMenu();
            Scanner sc = new Scanner(System.in);
            try {
                int opc = Integer.parseInt(sc.nextLine());
                if (opc <= 0){
                    System.out.println("Opção invalida.");
                    continue;
                }
                if (opc == 1){
                    leArquivo();
                    System.out.println("Nome e sobrenome:");
                    String nome = sc.nextLine();
                    System.out.println("Tipo:");
                    String tipo = sc.nextLine();
                    System.out.println("Sexo:");
                    String sexo = sc.nextLine();
                    System.out.println("Rua:");
                    String rua = sc.nextLine();
                    System.out.println("Numero da casa:");
                    String numeroCasa = sc.nextLine();
                    System.out.println("Cidade:");
                    String cidade = sc.nextLine();
                    System.out.println("Idade:");
                    String idade = sc.nextLine();
                    System.out.println("Peso:");
                    String peso = sc.nextLine();
                    System.out.println("Raça:");
                    String raca = sc.nextLine();
                    Pet pet = new Pet(nome, Tipo.valueOf(tipo.toUpperCase()), Sexo.valueOf(sexo.toUpperCase()), numeroCasa, cidade, rua, idade, peso, raca);
                    salvarPet(pet);
                }
            } catch (Exception e){
                e.printStackTrace();
                continue;
            }

        }
    }
    public static void leArquivo(){
        Path path = Paths.get("formulario.txt");
        try(BufferedReader reader = Files.newBufferedReader(path)){
            reader.lines().forEach(System.out::println);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void exibeMenu(){
        System.out.println("1.Cadastrar um novo pet");
        System.out.println("2.Alterar os dados do pet cadastrado");
        System.out.println("3.Deletar um pet cadastrado");
        System.out.println("4.Listar todos os pets cadastrados");
        System.out.println("5.Listar pets por algum critério (idade, nome, raça)");
        System.out.println("6.Sair");
    }
    public static void salvarPet(Pet pet){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        LocalDateTime ldt = LocalDateTime.now();
        String data = dtf.format(ldt);
        String nomePasta = data+"-"+pet.getNome().toUpperCase().replace(" ", "")+".txt";
        Path path = Paths.get("petsCadastrados\\" + nomePasta);
        try(ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path))){
            oos.writeObject(pet.toString());
            oos.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
