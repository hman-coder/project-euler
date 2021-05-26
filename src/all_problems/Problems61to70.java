package all_problems;

import helpers.ArrayHelpers;
import sixty_one_to_seventy.Problem68;

import java.util.Arrays;

public class Problems61to70 {
    public static void problem68() {
        String[] solution = new Problem68().runAlgorithm(10);
        for(String s : solution) {
            if(s != null) System.out.println(s);
        }
    }
}
