package picrossgame;


public class MainPicross {
    public static void main(String[] args) {

        String puzzle1="/Users/hugofriederich/IdeaProjects/Nonogram/Picross/src/main/resources/1puzzle5x5";
        String puzzle2="/Users/hugofriederich/IdeaProjects/Nonogram/Picross/src/main/resources/2puzzle5x5";
        String puzzle7x7="/Users/hugofriederich/IdeaProjects/Nonogram/Picross/src/main/resources/1puzzle7x7";
        String puzzle3x3="/Users/hugofriederich/IdeaProjects/Nonogram/Picross/src/main/resources/1puzzle3x3";
        String puzzle10x10="/Users/hugofriederich/IdeaProjects/Nonogram/Picross/src/main/resources/1puzzle10x10";

        Picross picrossGame = new Picross(puzzle10x10);
        int n = picrossGame.getN();
        int m = picrossGame.getM();

        picrossGame.jouer(puzzle10x10);
    }
}