package solver;

import math_library.Combinaisons;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class solverTest {

    @Test
    public void shouldDeleteComb() {
        Solver sol = new Solver("/Users/hugofriederich/IdeaProjects/Nonogram/Picross/src/main/resources/fichierlignecolonnes");
        Combinaisons combs=sol.translateCombinaisons(1);
        ArrayList<Integer> result= new ArrayList<>();
        result.add(0);
        result.add(1);
        result.add(0);
        result.add(0);
        result.add(0);
        Solver.supprCombs(result,combs);
        Combinaisons expected =new Combinaisons();
        ArrayList<Integer> comb1= new ArrayList<>();
        result.add(1);
        result.add(1);
        result.add(-1);
        result.add(-1);
        result.add(-1);
        ArrayList<Integer> comb2= new ArrayList<>();
        result.add(-1);
        result.add(1);
        result.add(1);
        result.add(-1);
        result.add(-1);

        expected.add(comb1);
        expected.add(comb2);

        assertEquals(expected, combs);
    }
}
