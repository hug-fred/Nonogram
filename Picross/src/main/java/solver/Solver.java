package solver;

import com.google.common.base.Predicates;
import picrossgame.EtatCase;
import picrossgame.Picross;
import picrossgame.SerieBloc;
import math_library.Combinaisons;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;

import static java.util.Arrays.stream;
import static math_library.Mathematique.transpose;


public class Solver {

    private Picross picrossGame;

    // attention changer avec getN et getM mais ne fonctionne pas
    final int n =5;
    final int m =5;
    private EtatCase[][] solution = new EtatCase[n][m];
    private Boolean [] lignes_termine = new Boolean[n];
    private Boolean [] colonnes_termine = new Boolean[m];


    public Solver(String fichierContraites){
        this.picrossGame=new Picross(fichierContraites);
    }

    public Picross getPicrossGame(){
        return(this.picrossGame);
    }


    /**
     * Première methode qui nettoie les blocs et blancs pour lesquelles leur position dans la grille est incertaine
     * @return une arraylist d'entiers contenant les emplacements des blancs et blocs dont on est certain de l'emplacement
     */
    public static ArrayList<Integer> compareCombinaisons(Combinaisons listCombinaisons) {
        ArrayList<Integer> emplacementCertains = new ArrayList<>();
        // taille d'une combinaison
        int tailleComb = listCombinaisons.get(0).size();
        if (listCombinaisons.size() == 1) {
            emplacementCertains = listCombinaisons.get(0);
            return emplacementCertains;
        }
        for (int i = 0; i < tailleComb; i++) {
            int sum = 0;
            for (int j = 0; j < listCombinaisons.size(); j++) {
                int val = listCombinaisons.get(j).get(i);
                sum += val;
            }
            if (sum == listCombinaisons.size()) {
                emplacementCertains.add(1);
            } else if (sum == -listCombinaisons.size()) {
                emplacementCertains.add(-1);
            } else {
                emplacementCertains.add(0);
            }
        }
        return emplacementCertains;
    }

    /**
     * Permet de supprimer les combinaisons contenant des infirmations déjà trouvées
     * @param result
     * @param combs
     * @return Combinaisons
     */
    public static void supprCombs(ArrayList<Integer> result, Combinaisons combs){
        //creation d'un iterrateur
        Iterator<ArrayList<Integer>> combIterator = combs.iterator();
        //tant qu'il y a des element dans combs
        while (combIterator.hasNext()){
            ArrayList<Integer> nextComb = combIterator.next();
            for (int j = 0; j < nextComb.size() ; j++) {

                if(result.get(j)==1 && nextComb.get(j)!=1){
                    combIterator.remove();
                }
                if(result.get(j)==-1 && nextComb.get(j)!=-1){
                    combIterator.remove();
                }
            }
        }
    }

    /**
     * Joint les deux résultats des lignes et colonnes dans une seule Combinaisons
     * @param lignes   : combinaisons lignes NON TRANSPOSES
     * @param colonnes : combinaisons colonnes NON TRANSPOSES
     * @return ArrayList<ArrayList < Integer>> : matrice de solution
     */
    public static Combinaisons joindreLignesColonnes(Combinaisons lignes, Combinaisons colonnes) {
        Combinaisons transColonne = transpose(colonnes);
        Combinaisons solutionMatrix = new Combinaisons();
        for (int i = 0; i < transColonne.size(); i++) {
            ArrayList<Integer> sousSolution = new ArrayList<>();
            for (int j = 0; j < transColonne.get(0).size(); j++) {
                if (transColonne.get(i).get(j) == 0 && lignes.get(i).get(j) == 0) {
                    sousSolution.add(0);
                } else if (transColonne.get(i).get(j) == 1 || lignes.get(i).get(j) == 1) {
                    sousSolution.add(1);
                } else {
                    sousSolution.add(-1);
                }
            }
            solutionMatrix.add(sousSolution);
        }
        return solutionMatrix;
    }

    /**
     * Translate l'ensemble des combinaisons pour un numéro de ligne de fichier donné
     * @param numeroLigneFichier
     * @return la liste des combinaisons translatees
     */
    public Combinaisons translateCombinaisons(int numeroLigneFichier) {
        PrintStream out = System.out;

        //pour traiter les ligne et les colonnes
        int m=this.picrossGame.getM();
        int n=this.picrossGame.getN();
        int tailleGrilleInter = 0;
        if (numeroLigneFichier > n) {
            tailleGrilleInter = m;
        } else if (numeroLigneFichier <= 0) {
            out.println("Le numéro de ligne choisis est incorrecte");
        } else {
            tailleGrilleInter = n;
        }

        //importation taille des blocs
        SerieBloc ligne = this.picrossGame.getContraintes().get(numeroLigneFichier - 1);

        //nombre de blocs dans un serieBloc
        int k = ligne.getTaille();
        //nombre de blancs entre les blocs
        int nbrBlancs = k - 1;
        //addition des tailles des blocs
        int tailleTotBlocs = 0;
        for (int i = 0; i < k; i++) {
            tailleTotBlocs += ligne.getBloc(i).getTaille();
        }
        // nombre de blancs en plus dans la ligne
        int nbrBlancPlus = m - (tailleTotBlocs + nbrBlancs);

        //liste dans laquelle seront mises les combinaisonsNonTranslatees translatées
        Combinaisons combinasonsTranslatees = new Combinaisons();

        //combinaisonsNonTranslatees trouvées non translatées
        Combinaisons combinaisonsNonTranslatees = new Combinaisons( k, nbrBlancPlus+k);


        for (int i=0;i<combinaisonsNonTranslatees.size();i++) {
            ArrayList<Integer> comb =combinaisonsNonTranslatees.get(i);
            ArrayList<Integer> combinaisonTranslatee = new ArrayList<>();
            //ajout des premières valeurs
            int premiereValeur = comb.get(0);
            for (int j = 0 ; j < premiereValeur; j++) {
                combinaisonTranslatee.add(-1);
            }

            for (int j = 0; j <= k - 1; j++) {
                //info taille du bloc
                int taillei = ligne.getBloc(j).getTaille();
                int diff;
                if (j < k - 1) {
                    //info taille du blanc
                    diff = comb.get(j + 1) - comb.get(j);
                } else {
                    diff = 0;
                }
                //ajout des blocs
                for (int l = 0; l < taillei; l++) {
                    combinaisonTranslatee.add(1);
                }

                //ajout des blancs après le blocs sans ajouter de blancs apres le dernier bloc
                if (j <= k - 2) {
                    for (int p = 0; p < diff; p++) {
                        combinaisonTranslatee.add(-1);
                    }
                }
            }
            //ajout des blancs en plus
            while (combinaisonTranslatee.size() < tailleGrilleInter) {
                combinaisonTranslatee.add(-1);
            }
            combinasonsTranslatees.add(combinaisonTranslatee);
        }
        return combinasonsTranslatees;
    }

    /**
     * Vérifie si le puzzle est résolu ou non
     * @return boolean
     */
    private boolean check_solved() {
        return stream(lignes_termine).allMatch(Predicates.equalTo(true))
                && stream(colonnes_termine).allMatch(Predicates.equalTo(true));
    }


    /**
     * Permet de résoudre le nonogram en considérant toutes les combinaisons possibles, en faisant leur intersection
     * et en supprimant les informations redondantes au fure et à mesure
     * @return EtatCase[][]
     */
    public EtatCase[][] resoudre(){
        boolean isSolved=false;

        ArrayList<Combinaisons> combinaisonsLignes = new ArrayList<>();
        ArrayList<Combinaisons> combinaisonsColonnes = new ArrayList<>();
        Combinaisons lignesResult = new Combinaisons();
        Combinaisons colonneResult = new Combinaisons();
        Combinaisons finalResult = new Combinaisons();

        //Initialisation
        for (int i = 1; i < n+1; i++) {
            Combinaisons combI = translateCombinaisons(i);
            combinaisonsLignes.add(combI);
        }
        for (int j = n+1; j < n+m+1; j++) {
            Combinaisons combJ = translateCombinaisons(j);
            combinaisonsColonnes.add(combJ);
        }

        // Bouclage tant que le puzzle n'est pas résolue
        while (isSolved==false){

            for (int i = 0; i < n; i++) {
                Combinaisons combI=combinaisonsLignes.get(i);
                ArrayList<Integer> resultLigne = compareCombinaisons(combI);
                lignes_termine[i]=verifyState(resultLigne);
                lignesResult.add(resultLigne);

            }
            for (int j = 0; j < m; j++) {
                Combinaisons combJ = combinaisonsColonnes.get(j);
                ArrayList<Integer> resultColonne = compareCombinaisons(combJ);
                colonnes_termine[j]=verifyState(resultColonne);
                colonneResult.add(resultColonne);
            }

            //met en commun le résultat sur les lignes et les colonnes

            finalResult=joindreLignesColonnes(lignesResult,colonneResult);
            lignesResult.clear();
            colonneResult.clear();

            for(int k=0;k<combinaisonsLignes.size();k++){
                supprCombs(finalResult.get(k),combinaisonsLignes.get(k));
            }
            Combinaisons transposedFinalResult = transpose(finalResult);
            for(int l=0;l<combinaisonsColonnes.size();l++){
                supprCombs(transposedFinalResult.get(l),combinaisonsLignes.get(l));
            }
            isSolved=check_solved();
        }

        solution= finalResult.toTable();

        return(solution);
    }

    /**
     * Verifie si une ligne est résolue ou non
     * @param finalComb
     * @return boolean  (true si résolue et false sinon)
     */
    private boolean verifyState(ArrayList<Integer> finalComb) {
        boolean state=true;
        if (finalComb.contains(0)){
            state=false;
        }
        return(state);
    }


}
