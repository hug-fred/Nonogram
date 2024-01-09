package picrossgame;

import math_library.Mathematique;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class Picross {
    private int n;       // nombre de ligne
    private int m;      // nombre de colonnes
    private ArrayList<SerieBloc> contraintes;   //liste des valeurs des contraintes du Picross


    public Picross(ArrayList<SerieBloc> contraintes, int n, int m) {
        this.contraintes = contraintes;
        this.n = n;
        this.m = m;
    }

    /**
     * Constructeur de la classe Picross.
     * Lit le fichier texte contenant les séries de blocs horizontaux et verticaux
     * du Picross et initialise la liste des valeurs.
     *
     * @param nomDuFichier le nom du fichier à lire
     */
    public Picross(String nomDuFichier)  {
        try {
            lireContraintes(nomDuFichier);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retourne le nombre de lignes.
     *
     * @return le nombre de lignes
     */
    public int getN() {
        return this.n;
    }

    /**
     * Retourne le nombre de colonnes.
     *
     * @return le nombre de colonnes
     */
    public int getM() {
        return this.m;
    }

    /**
     * Retourne la liste des valeurs.
     *
     * @return la liste des valeurs
     */
    public ArrayList<SerieBloc> getContraintes() {
        return this.contraintes;
    }

    /**
     * Lit le fichier texte contenant les séries de blocs horizontaux et verticaux
     * du Picross et retourne une ArrayList de séries de blocs.
     *
     * @param nomDuFichier le nom du fichier à lire
     */
    public void lireContraintes (String nomDuFichier) throws IOException {
        FileReader fichier = new FileReader(nomDuFichier);
        BufferedReader bufferedReader = new BufferedReader(fichier);
        ArrayList<SerieBloc> blocHV = new ArrayList<>();    //liste des series de blocs représentant les contraintes

        String line;
        while ((line=bufferedReader.readLine())!=null) {
            String[] champs = line.split(";");
            SerieBloc sB = new SerieBloc();
            for (int i = 0; i < champs.length; i++) {
                if (champs[i].contains("#")) {                    //suppression des String "lignes" et "colonnes"
                    i++;
                } else {
                    sB.addBloc(new Bloc(Integer.parseInt(champs[i])));
                }
            }
            blocHV.add(sB);
        }
        // suppression des valeurs nulles de blocHV
        blocHV.removeIf(SerieBloc::isEmpty);
        this.n = blocHV.get(0).getBloc(0).getTaille();       // Attribution de la taille du picross
        this.m = blocHV.get(1).getBloc(0).getTaille();

        blocHV.remove(0);                            // Suppression des deux valeurs de la taille du picross
        blocHV.remove(0);
        this.contraintes = blocHV;
    }

    /**
     * Donne la taille maximum des seriesblocs des lignes dans les contraintes
     *
     * @return la valeur maximale
     */
    public int maxContraintesLignes() {
        int max = 0;
        for (int i = 0; i < this.getN(); i++) {
            if (this.contraintes.get(i + 1).getTaille() > this.contraintes.get(i).getTaille()) {
                max = this.contraintes.get(i + 1).getTaille();
            }
        }
        return max;
    }

    /**
     * Donne la taille maximum des seriesblocs des colonnes dans les contraintes
     *
     * @return la valeur maximale
     */
    public int maxContraintesColonnes() {
        int max = 0;
        for (int i = this.getN(); i < this.getM() + this.getN() - 1; i++) {
            if (this.contraintes.get(i + 1).getTaille() > this.contraintes.get(i).getTaille()) {
                max = this.contraintes.get(i + 1).getTaille();
            }
        }
        return max;
    }


    /**
     * Permets à l'utilisateur de rentrer les coordonées d'une case à remplir
     */
    public static void rentrerCoordonees(EtatCase[][] tableauSuppose) {
        PrintStream out = System.out;
        var suppressionMode = false;
        var emptyMode = false;
        try {                                                     //demander a l'utilisateur de rentrer des coordonées
            Scanner scanner = new Scanner(System.in);
            out.println("Rentrer les coordonées d'une case [x y] ou taper x pour supprimer ou e pour vider");
            out.println("Donner coordonnée à remplir:");
            var token =scanner.nextLine();

            if (token.startsWith("x")) {
                suppressionMode = true;
                out.println("Donner coordonnée à supprimer:");
                token =scanner.nextLine();
            }
            if (token.startsWith("e")) {
                emptyMode = true;
                out.println("Donner coordonnée à remplir comme vide:");
                token =scanner.nextLine();
            }
            var valeurs = token.split(" ");
            var x = Integer.valueOf(valeurs[0])-1;
            var y = Integer.valueOf(valeurs[1])-1;
            if (suppressionMode) {
                tableauSuppose[x][y]=EtatCase.INCONNUE;
            } else if (emptyMode) {
                tableauSuppose[x][y]=EtatCase.VIDE;
            }else {
                tableauSuppose[x][y]=EtatCase.PLEIN;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            out.println(" Attention les coordonnées ne se situent pas sur le plateau de jeu");
        }
    }

    /**
     * affichage dans la console du plateau de jeu
     */
    public void jouer(EtatCase[][] tableauSolutions) {
        PrintStream out = System.out;
        EtatCase[][] tableauARemplir = new EtatCase[this.n][this.m];
        List<ArrayList<String>> contraintesVerticales = this.afficherContraintesColonnes();
        List<ArrayList<String>> contraintesHorizontales = this.afficherContraintesLignes();


        while (!Arrays.deepEquals(tableauARemplir, tableauSolutions)) {  //demande a l'utilisateur de rentrer des valeurs

            out.println("Solution :");                      //affichage solution
            out.println();
            int n=this.getN();
            int m=this.getM();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    out.print(affichageCase(tableauSolutions[i][j]));
                }
                out.println();
            }
            out.println();

            // affichage des contraintes des colonnes
            for (ArrayList<String> ligne : contraintesVerticales) {
                for (int i = 0; i < this.maxContraintesLignes() + 1; i++) {
                    out.print(" ");
                }
                for (String num : ligne) {
                    out.print(num);
                }
                out.println();
            }

            for (int i = 0; i < this.getN(); i++) {                          //affichage tableau + contraintes horizontales
                for (int k = 0; k < contraintesHorizontales.get(0).size(); k++) {
                    out.print(contraintesHorizontales.get(i).get(k));
                }
                out.print(" ");
                for (int j = 0; j < this.getM(); j++) {
                    out.print(affichageCase(tableauARemplir[i][j]));
                }
                out.println();
            }
            System.out.println();
            rentrerCoordonees(tableauARemplir);

        }
        out.println();
        out.println("You Win");

    }

    /**
     * Permet d'afficher les contraintes sur les premières lignes de la console
     *
     * @return une liste de contraintes
     */
    public List<ArrayList<String>> afficherContraintesColonnes() {
        ArrayList<ArrayList<String>> contraintesHV = Mathematique.castSerieBlocToString(this.contraintes);
        List<ArrayList<String>> contraintesVerticales = contraintesHV.subList(this.m, this.n + this.m);
        for (ArrayList<String> contrainte : contraintesVerticales) {
            if (contrainte.size() == this.maxContraintesLignes()) {
                continue;
            } else {
                while (contrainte.size() < this.maxContraintesColonnes()) {       //completer avec des espaces pour avoir des list de mêmes tailles
                    contrainte.addFirst(" ");
                }
            }
        }
        List<ArrayList<String>> contraintesTranspose = Mathematique.transposeStr(contraintesVerticales);
        return contraintesTranspose;
    }

    /**
     * Permet d'afficher les contraintes en chaque début de lignes
     *
     * @return une liste de contraintes
     */
    public List<ArrayList<String>> afficherContraintesLignes() {
        ArrayList<ArrayList<String>> contraintesHV = Mathematique.castSerieBlocToString(this.contraintes);
        List<ArrayList<String>> contraintesVerticales = contraintesHV.subList(0, this.n);
        for (ArrayList<String> contrainte : contraintesVerticales) {
            if (contrainte.size() == this.maxContraintesLignes()) {
                continue;
            } else {
                while (contrainte.size() < this.maxContraintesColonnes()) {       //completer avec des espaces pour avoir des list de mêmes tailles
                    contrainte.addFirst(" ");
                }
            }
        }
        return contraintesVerticales;
    }

    public static String affichageCase (EtatCase etatCase){
        String etat="o";
        if(etatCase==EtatCase.VIDE){
            etat="-";
        }if (etatCase==EtatCase.PLEIN){
            etat="x";
        }
        return(etat);
    }

}



