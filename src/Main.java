
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.InputMismatchException;
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

                }
            } catch (Exception e){
                System.out.println("Caractere invalido.");
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
    public
}
