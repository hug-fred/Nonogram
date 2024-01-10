package math_library;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class mathematiqueTest {

    @Test
    public void shoudTransposeStringMatrix(){
        ArrayList<String>str1 =new ArrayList(Arrays.asList("1","2","3","4"));
        ArrayList<String>str2 =new ArrayList(Arrays.asList("5","6","7","8"));

        ArrayList<String>str3 =new ArrayList(Arrays.asList("1","5"));
        ArrayList<String>str4 =new ArrayList(Arrays.asList("2","6"));
        ArrayList<String>str5 =new ArrayList(Arrays.asList("3","7"));
        ArrayList<String>str6 =new ArrayList(Arrays.asList("4","8"));
        List<ArrayList<String>>matrix=new ArrayList<>();
        matrix.add(str1);
        matrix.add(str2);

        List<ArrayList<String>>expected=new ArrayList<>();
        expected.add(str3);
        expected.add(str4);
        expected.add(str5);
        expected.add(str6);
        List<ArrayList<String>> result=Mathematique.transposeStr(matrix);
        assertEquals(expected,result);
    }

}
