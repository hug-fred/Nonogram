package tools;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class combinaisonsTest {

    @Test
    public void shouldTransposeMatrix(){
        Combinaisons combinaisons=new Combinaisons(2,4);
        combinaisons =Mathematique.transpose(combinaisons);
        Combinaisons expected = new Combinaisons();
        ArrayList comb1 = new ArrayList(Arrays.asList(0, 0, 0, 1, 1, 2));
        ArrayList comb2 = new ArrayList(Arrays.asList(1, 2, 3, 2, 3, 3));
        expected.add(comb1);
        expected.add(comb2);
        assertEquals(expected,combinaisons);
    }
}
