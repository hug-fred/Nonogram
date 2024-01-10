package picrossgame;


public class MainPicross {
    public static void main(String[] args) {

        String puzzle1="/Users/hugofriederich/IdeaProjects/Nonogram/Picross/src/main/resources/fichierlignecolonnes";
        String puzzle2="/Users/hugofriederich/IdeaProjects/Nonogram/Picross/src/main/resources/fichierContraintes2";
        String puzzle7x7="/Users/hugofriederich/IdeaProjects/Nonogram/Picross/src/main/resources/fichierContraintes7x7";
        String puzzle3x3="/Users/hugofriederich/IdeaProjects/Nonogram/Picross/src/main/resources/fichierContraintes3x3";

        Picross picrossGame = new Picross(puzzle7x7);
        int n = picrossGame.getN();
        int m = picrossGame.getM();

        picrossGame.jouer(puzzle7x7);
    }
}