package test;

import math_library.Combinaisons;
import picrossgame.*;
import com.google.common.collect.Lists;
import solver.Solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;


public class MainPicross {
    private static String nomDuFichier;


    /**
     * remplissage du tableau avec les valeurs de la solution
     */
    public static EtatCase[][] SOLUTION = new EtatCase[][] {
            {EtatCase.VIDE,EtatCase.VIDE,EtatCase.VIDE,EtatCase.PLEIN,EtatCase.PLEIN},
            {EtatCase.PLEIN,EtatCase.PLEIN,EtatCase.VIDE,EtatCase.PLEIN,EtatCase.PLEIN},
            {EtatCase.PLEIN,EtatCase.PLEIN,EtatCase.PLEIN,EtatCase.VIDE,EtatCase.VIDE},
            {EtatCase.PLEIN,EtatCase.PLEIN,EtatCase.VIDE,EtatCase.VIDE,EtatCase.VIDE},
            {EtatCase.VIDE,EtatCase.PLEIN,EtatCase.PLEIN,EtatCase.VIDE,EtatCase.VIDE}};

    public static ArrayList<SerieBloc> TEST = Lists.newArrayList(Arrays.stream(new Integer[][]{
            {2},
            {2, 2},
            {3},
            {2},
            {2},
            // #colonnes
            {3},
            {4},
            {1, 1},
            {2},
            {2}
    }).map(l -> new SerieBloc((Arrays.stream(l).map(Bloc::new).collect(Collectors.toList())))).collect(Collectors.toList()));


    public static void main(String[] args) {

        String puzzle1="/Users/hugofriederich/IdeaProjects/Nonogram/Picross/src/main/resources/fichierlignecolonnes";
        String puzzle2="/Users/hugofriederich/IdeaProjects/Nonogram/Picross/src/main/resources/fichierContraintes2";
        String puzzle7x7="/Users/hugofriederich/IdeaProjects/Nonogram/Picross/src/main/resources/fichierContraintes7x7";
        String puzzle3x3="/Users/hugofriederich/IdeaProjects/Nonogram/Picross/src/main/resources/fichierContraintes3x3";

        Picross picrossGame = new Picross(puzzle3x3);
        int n = picrossGame.getN();
        int m = picrossGame.getM();

        picrossGame.jouer(puzzle3x3);
    }
}