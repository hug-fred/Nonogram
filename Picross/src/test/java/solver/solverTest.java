package solver;

import math_library.Combinaisons;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class solverTest {

    @Test
    public void shouldDeleteCombLigne2() {
        Solver sol = new Solver("/Users/hugofriederich/IdeaProjects/Nonogram/Picross/src/main/resources/fichierlignecolonnes");
        Combinaisons combs=sol.translateCombinaisons(4);
        ArrayList<Integer> result= new ArrayList(Arrays.asList(0,1,0,0,0));

        Solver.supprCombs(result,combs);
        Combinaisons expected =new Combinaisons();
        ArrayList<Integer> comb1= new ArrayList(Arrays.asList(1,1,-1,-1,-1));

        ArrayList<Integer> comb2= new ArrayList(Arrays.asList(-1,1,1,-1,-1));

        expected.add(comb1);
        expected.add(comb2);

        for(int i =0;i<combs.size();i++){
            for(int j=0;j<result.size();j++){
                assertEquals(expected.get(i).get(j), combs.get(i).get(j));
            }
        }
    }
}
