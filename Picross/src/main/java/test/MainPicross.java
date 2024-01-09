package test;

import picrossgame.*;
import com.google.common.collect.Lists;

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

        Picross picrossGame = new Picross(TEST,5,5);
        int n = picrossGame.getN();
        int m = picrossGame.getM();
        int tailleMaxH = picrossGame.maxContraintesLignes();
        int tailleMaxV = picrossGame.maxContraintesColonnes();
        picrossGame.jouer(SOLUTION);
    }
}