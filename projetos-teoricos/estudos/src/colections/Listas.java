package colections;

import java.util.ArrayList;
import java.util.List;

public class Listas {
    public static void main(String[] args) {
        List listaNomes = new ArrayList<>();
        listaNomes.add("Sinistro");
        listaNomes.add("Repositorio");
        listaNomes.add("Reboleixon");
        listaNomes.add("Bacana");

        listaNomes.forEach(ln-> System.out.println(ln));
    }
}
