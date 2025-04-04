
import entities.Pet;
import enums.Sexo;
import enums.Tipo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.Collator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Boolean menuInicial = true;
        while (menuInicial){
            Scanner sc = new Scanner(System.in);
            System.out.println("1 - Iniciar o sistema para cadastro de PETS\n2 - Iniciar o sistema para alterar formulário");
            int m = sc.nextInt();
            sc.nextLine();
            if (m == 1){
                while (true){
                    System.out.println();
                    exibeMenu();
                    try {
                        int opc = Integer.parseInt(sc.nextLine());
                        if (opc <= 0 || opc > 6){
                            System.out.println("Opção invalida.");
                        }
                        else if (opc == 1){
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
                                Pet pet = new Pet(construtor.get(0), Tipo.valueOf(construtor.get(1).toUpperCase()), Sexo.valueOf(construtor.get(2).toUpperCase()), construtor.get(3), construtor.get(4),
                                        construtor.get(5), construtor.get(6), construtor.get(7), construtor.get(8));
                                Pet.addPet(pet);
                                salvarPet(pet, construtor);
                            }catch (IOException e){
                                e.printStackTrace();
                            }
                        }
                        else if (opc == 2){
                            ArrayList<Pet> petsFiltrados = buscaCriterios();
                            if (petsFiltrados != null){
                                int n = sc.nextInt();
                                sc.nextLine();
                                System.out.println(petsFiltrados.size());

                                Pet pet = n <= petsFiltrados.size() ? petsFiltrados.get(n-1) : null;
                                if (pet != null){
                                    Pet.alteraPet(pet);
                                }
                            }else{
                                System.out.println("Nenhum pet encontrado.");
                            }
                        }
                        else if (opc == 3){
                            ArrayList<Pet> petsFiltrados = buscaCriterios();
                            if (petsFiltrados != null){
                                int n = sc.nextInt();
                                sc.nextLine();
                                System.out.println(petsFiltrados.size());
                                Pet pet = n <= petsFiltrados.size() ? petsFiltrados.get(n-1) : null;
                                if (pet != null){
                                    Pet.removePet(pet);
                                }
                            }else{
                                System.out.println("Nenhum pet encontrado.");
                            }
                        }
                        else if (opc == 4){
                            Pet.toStringList();
                        }
                        else if (opc == 5 ){
                            if (buscaCriterios() == null){
                                System.out.println("Nenhum pet encontrado.");
                            }
                        }
                        else if (opc == 6){
                            menuInicial = false;
                            break;
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            else if (m == 2){
                while (true){
                    exibeMenuFormulario();
                    try(BufferedReader bf = new BufferedReader(new FileReader("formulario.txt"))) {
                        int opc = Integer.parseInt(sc.nextLine());
                        ArrayList<String> perguntas = new ArrayList<>();

                        String linha;
                        while ((linha = bf.readLine()) != null){
                            perguntas.add(linha);
                        }
                        if (opc <= 0 || opc > 5){
                            System.out.println("Opção invalida.");
                        }
                        else if (opc == 1){
                            System.out.println("Insira a nova pergunta:");
                            String pergunta = sc.nextLine();
                            perguntas.add((perguntas.size()+1) + " - "+pergunta);
                        }
                        else if (opc == 2){
                            System.out.println("Selecione qual pergunta alterar:");
                            for (String p : perguntas){
                                System.out.println(p);
                            }
                            int n = sc.nextInt();
                            sc.nextLine();
                            if (n <= 7){
                                System.out.println("As perguntas originais não podem ser alteradas.");
                            }
                            else{
                                System.out.println("Nova pergunta: ");
                                String pergunta = sc.nextLine();
                                perguntas.set(n-1, n+" - "+pergunta);
                            }
                        }else if (opc == 3){
                            System.out.println("Selecione qual pergunta remover:");
                            for (String p : perguntas){
                                System.out.println(p);
                            }
                            int n = sc.nextInt();
                            sc.nextLine();
                            if (n <= 7){
                                System.out.println("As perguntas originais não podem ser removidas.");
                            }
                            else{
                                for (int i = n; i <= perguntas.size(); i++){
                                    if(perguntas.get(i) != null){
                                        String replace = perguntas.get(i).replace("" + i, "" + (i - 1));
                                        perguntas.set(i-1, replace);
                                    }
                                    else{
                                        perguntas.set(i-1, "");
                                    }
                                }
                            }
                        }
                        try(BufferedWriter bw = new BufferedWriter(new FileWriter("formulario.txt"))) {
                            for (int i = 0; i < perguntas.size(); i++){
                                bw.write(perguntas.get(i));
                                bw.newLine();
                            }
                        }catch (IOException e){
                            e.printStackTrace();
                        }
                        if (opc == 4){
                            break;
                        }
                        if (opc == 5){
                            menuInicial = false;
                            break;
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
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

    public static void exibeMenuFormulario(){
        System.out.println("1.Criar nova pergunta");
        System.out.println("2.Alterar pergunta existente");
        System.out.println("3.Excluir pergunta existente");
        System.out.println("4.Voltar para o menu inicial");
        System.out.println("5.Sair");
    }

    public static void salvarPet(Pet pet, ArrayList<String> array) throws IOException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        LocalDateTime ldt = LocalDateTime.now();
        String data = dtf.format(ldt);
        String nomePasta = data+"-"+pet.getNome().toUpperCase().replace(" ", "")+".txt";
        if (Files.notExists(Path.of("petsCadastrados"))){
            Files.createDirectory(Path.of("petsCadastrados"));
        }
        Path path = Paths.get("petsCadastrados\\" + nomePasta);
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(path.toString()))){
            bw.write(pet.toString());
            if (array.size() > 9){
                int count = 8;
                bw.newLine();
                for (int i = 9; i < array.size() ; i++){
                    if (i == array.size()-1){
                        bw.write((count) + " - [EXTRA - PERGUNTA NOVA ADICIONADA] - "+array.get(i));
                    }else{
                        bw.write((count) + " - [EXTRA - PERGUNTA NOVA ADICIONADA] - "+array.get(i));
                        bw.newLine();
                    }
                    count++;

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static ArrayList<Pet> buscaCriterios(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Pet> petsFiltrados = new ArrayList<>();
        System.out.println("Qual o tipo do animal?\n1.Cachorro\n2.Gato");
        int tipo = sc.nextInt();
        sc.nextLine();
        for (Pet pet : Pet.getAnimaisCadastrados()){
            if (tipo == 1 && pet.getTipo().equalsIgnoreCase("Cachorro")){
                petsFiltrados.add(pet);
            }else if (tipo == 2 && pet.getTipo().equalsIgnoreCase("Gato")){
                petsFiltrados.add(pet);
            }
        }
        while (true){
            final Collator instance = Collator.getInstance();
            instance.setStrength(Collator.PRIMARY);
            System.out.println("Buscar por:\n1-Nome\n2-Sexo\n3-Idade\n4-Peso\n5-Raça\n6-Endereço");
            int n = sc.nextInt();
            int count = 1;
            sc.nextLine();
            switch (n){
                case 1:
                    System.out.println("Nome:");
                    String nome = sc.nextLine();
                    petsFiltrados.removeIf(pet -> !instance.equals(pet.getNome(), nome.trim()));
                    break;
                case 2:
                    System.out.println("Sexo:");
                    String sexo = sc.nextLine();
                    petsFiltrados.removeIf(pet -> !instance.equals(pet.getSexo(), sexo.trim()));
                    break;
                case 3:
                    System.out.println("Idade:");
                    String idade = sc.nextLine();
                    petsFiltrados.removeIf(pet -> !pet.getIdade().contains(idade));
                    break;
                case 4:
                    System.out.println("Peso:");
                    String peso = sc.nextLine();
                    petsFiltrados.removeIf(pet -> !pet.getPeso().toUpperCase().contains(peso.toUpperCase()));
                    break;
                case 5:
                    System.out.println("Raça:");
                    String raca = sc.nextLine();
                    petsFiltrados.removeIf(pet -> !pet.getRaca().toUpperCase().contains(raca.toUpperCase()));
                    break;
                case 6:
                    System.out.println("Endereço:");
                    String endereco = sc.nextLine();
                    petsFiltrados.removeIf(pet -> !pet.getEndereco().toUpperCase().contains(endereco.toUpperCase()));
                    break;
                default:
                    break;
            }
            count++;
            if (count <= 2){
                System.out.println("1-Adicionar outro criterio\n2-Finalizar.");
                n = sc.nextInt();
                sc.nextLine();
                if (n == 2){
                    break;
                }
            }
        }
        if (petsFiltrados.isEmpty()){
            return null;
        }
        for (Pet pet : petsFiltrados){
            System.out.println((petsFiltrados.indexOf(pet)+1)+". " +pet.getNome()+" - "+pet.getTipo()+" - "+pet.getSexo()+" - "+pet.getEndereco()+" - "+pet.getIdade()+" - "+pet.getPeso()+" - "+pet.getRaca());
        }
        return petsFiltrados;
    }
}
