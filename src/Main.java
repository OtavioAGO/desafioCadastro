
import entities.Pet;
import enums.Sexo;
import enums.Tipo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
                    try(BufferedReader bf = new BufferedReader(new FileReader("formulario.txt"))) {
                        String line;
                        ArrayList<String> construtor = new ArrayList<>();
                        int count = 0;
                        while ((line = bf.readLine()) != null){
                            if (line.isBlank()){
                                continue;
                            }
                            System.out.println(line);
                            count++;
                            if (count == 4){
                                System.out.println("rua: ");
                                String rua = sc.nextLine();
                                construtor.add(rua);
                                System.out.println("numero da casa: ");
                                String numeroCasa = sc.nextLine();
                                construtor.add(numeroCasa);
                                System.out.println("cidade: ");
                                String cidade = sc.nextLine();
                                construtor.add(cidade);
                                continue;
                            }
                            String c = sc.nextLine();
                            construtor.add(c);
                        }
                        System.out.println(construtor);
                        Pet pet = new Pet(construtor.get(0), Tipo.valueOf(construtor.get(1).toUpperCase()), Sexo.valueOf(construtor.get(2).toUpperCase()), construtor.get(3), construtor.get(4),
                                construtor.get(5), construtor.get(6), construtor.get(7), construtor.get(8));
                        salvarPet(pet);
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
                if (opc == 6){
                    break;
                }
            } catch (Exception e){
                e.printStackTrace();
                continue;
            }

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
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path.toString()))){
            bw.write(pet.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
