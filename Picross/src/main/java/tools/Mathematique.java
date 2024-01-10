package tools;

import picrossgame.SerieBloc;

import java.util.ArrayList;
import java.util.List;

public class Mathematique {


    /**
     * Retourne la transposée d'une matrice d'entiers de n'importe quelle taille
     * @param matrix
     * @return ArrayList<ArrayList<Integer>> matrixTransposée
     */

    public static Combinaisons transpose(Combinaisons matrix) {
        // Vérifie que la matrice est non nulle.
        if (matrix == null) {
           throw new IllegalArgumentException("La matrice ne peut pas être nulle.");
        }

        // Vérifie que la matrice a au moins une ligne.
        if (matrix.size() == 0) {
            throw new IllegalArgumentException("La matrice ne peut pas être vide.");
        }


        // Crée une nouvelle ArrayList pour stocker la matrice transposée.
        Combinaisons transposedMatrix = new Combinaisons();

        for (int j = 0; j < matrix.get(0).size(); j++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int i = 0; i < matrix.size(); i++) {
                row.add(matrix.get(i).get(j));
            }
            transposedMatrix.add(row);
        }
        return(transposedMatrix);
    }


    /**
     * Retourne la transposée d'une matrice de chaines de caractères de n'importe quelle taille
     * @param matrix
     * @return ArrayList<ArrayList<Integer>> matrixTransposée
     */
    public static List<ArrayList<String>> transposeStr(List<ArrayList<String>> matrix) {
        // Vérifie que la matrice est non nulle.
        if (matrix == null) {
            throw new IllegalArgumentException("La matrice ne peut pas être nulle.");
        }

        // Vérifie que la matrice a au moins une ligne.
        if (matrix.size() == 0) {
            throw new IllegalArgumentException("La matrice ne peut pas être vide.");
        }


        // Crée une nouvelle ArrayList pour stocker la matrice transposée.
        ArrayList<ArrayList<String>> transposedMatrix = new ArrayList<>();

        for (int j = 0; j < matrix.get(0).size(); j++) {
            ArrayList<String> row = new ArrayList<>();
            for (int i = 0; i < matrix.size(); i++) {
                row.add(matrix.get(i).get(j));
            }
            transposedMatrix.add(row);
        }
        return(transposedMatrix);
    }

    /**
     * Transforme une ArrayList<SerieBloc> en ArrayList<ArrayList<String>>
     * @param listeValeurs la liste des valeurs
     * @return une liste de contraintes
     */
    public static ArrayList<ArrayList<String>> castSerieBlocToString(ArrayList<SerieBloc> listeValeurs){
        ArrayList<ArrayList<String>> contraintesHV = new ArrayList<>();
        for(SerieBloc serieBloc:listeValeurs){
            ArrayList<String> elt=new ArrayList<>();
            for(int i=0;i<serieBloc.getTaille();i++){
                elt.add(serieBloc.getBloc(i).toString());
            }
            contraintesHV.add(elt);
        }
        return contraintesHV;
    }

}


