package picrossgame;



public class Bloc {
    private final int taille;


    public Bloc (int t){
        this.taille=t;
    }

    public int getTaille(){
        return(this.taille);
    }

    public String toString(){
        return(String.valueOf(this.taille)) ;
    }
}

