package test;

import math_library.Combinaisons;
import math_library.Mathematique;
import solver.Solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class MathTest {
    public static void main(String[] args) {

        Solver sol = new Solver("/Users/hugofriederich/IdeaProjects/Nonogram/Picross/src/main/resources/fichierlignecolonnes");
        Combinaisons combs=sol.translateCombinaisons(1);
        ArrayList<Integer> result= new ArrayList(Arrays.asList(0,1,0,0,0));

        System.out.println(combs);
        Solver.supprCombs(result,combs);
        System.out.println(combs);


    }
}
