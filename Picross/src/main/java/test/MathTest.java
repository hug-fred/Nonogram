package test;

import math_library.Combinaisons;
import math_library.Mathematique;

public class MathTest {
    public static void main(String[] args) {

        Combinaisons combinaisons=new Combinaisons(2,4);
        System.out.println(combinaisons);
        System.out.println(Mathematique.transpose(combinaisons));

    }
}
