package picrossgame;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class SerieBloc {
    private ArrayList<Bloc> serieBloc;


    public SerieBloc(List<Bloc> serieBlocList) {
        this.serieBloc = Lists.newArrayList(serieBlocList);
    }
    public SerieBloc() {
        this.serieBloc= new ArrayList<Bloc>();
    }




    /*
    Methodes par défauts
     */
    public Bloc getBloc(int i) {
        return this.serieBloc.get(i);
    }

    public void setBloc(int i, Bloc bloc) {     //changer un bloc d'une serie de bloc par un autre
        this.serieBloc.set(i, bloc);
    }

    public int getNombreBloc() {
        return this.serieBloc.size();
    }

    public void addBloc (Bloc newBloc){
        this.serieBloc.add(newBloc);
    }

    public void removeBloc(int i){
        this.serieBloc.remove(i);
    }

    public int getTaille(){
        return(this.serieBloc.size());
    }


    public String toString (){
        StringBuilder chaineDeCharactere= new StringBuilder();
        for (Bloc elt: this.serieBloc){
            chaineDeCharactere.append(elt.toString()).append(" ,");
        }
        return chaineDeCharactere.toString();
    }

    /*
    Autres méthodes
     */

    /**
     * Pour savoir si la serieBloc est vide
     * @return boolan true si vide
     */
    public boolean isEmpty() {
        boolean result=false;
        if (this.serieBloc.isEmpty()){
            result=true;
        }
        return(result);
    }



}


