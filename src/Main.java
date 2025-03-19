
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        leArquivo();
    }
    public static void leArquivo(){
        Path path = Paths.get("formulario.txt");
        try(BufferedReader reader = Files.newBufferedReader(path)){
            reader.lines().forEach(System.out::println);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
