package math_library;

import picrossgame.EtatCase;

import java.util.ArrayList;
import java.util.Iterator;

public class Combinaisons extends ArrayList {

    private ArrayList<ArrayList<Integer>> combs;


    public Combinaisons (int k,int n){
        generateCombinations(k,n);
    }

    public Combinaisons (){
        this.combs=new ArrayList<ArrayList<Integer>>();
    }


    /**
     * Génère toutes les combinaisons possibles de k éléments choisis parmi n éléments.
     * Il utilise une approche récursive pour générer les combinaisons.
     */
    private void generateCombinationsHelper(int n, int k, int start, int[] combinaison, ArrayList<ArrayList<Integer>> result) {
        if (k == 0) {
            ArrayList<Integer> combinaisonCourante = new ArrayList<>();
            for (int value : combinaison) {
                combinaisonCourante.add(value);
            }
            result.add(combinaisonCourante);
            return;
        }


        for (int i = start; i <= n -k+1; i++) {
            combinaison[combinaison.length - k] = i-1;
            generateCombinationsHelper(n, k - 1, i + 1, combinaison, result);
        }
    }

    /**
     * Classe dans une arrayList toutes les combinaisons de k parmi n éléments dans l'ordre croissant
     * @param n
     * @param k
     * @return ArrayList<ArrayList<Integer>>
     */
    public void generateCombinations(int k, int n) {
        ArrayList<ArrayList<Integer>> combinationsList = new ArrayList<>();
        int[] combination = new int[k];
        generateCombinationsHelper(n, k, 1, combination, combinationsList);

        this.combs=combinationsList;

    }



    public ArrayList<Integer> get(int i) {
        return(this.combs.get(i));
    }
    public int size() {
        return(this.combs.size());
    }
    public void add(ArrayList<Integer> comb){
        this.combs.add(comb);
    }
    public String toString(){
        return(this.combs.toString());
    }

    public void clear (){
        this.combs.clear();
    }



    public EtatCase[][] toTable(){
        int n = this.size();
        int m = this.get(0).size();

        EtatCase [][] table = new EtatCase[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                var nbr =this.get(i).get(j);
                if(nbr==1){
                    table[i][j]=EtatCase.PLEIN;
                }else if(nbr==-1){
                    table[i][j]=EtatCase.VIDE;
                }else{
                    table[i][j]=EtatCase.INCONNUE;
                }
            }
        }
        return(table);
    }





}
