package sixty_one_to_seventy;

import helpers.RunTimeTimer;
import sixty_one_to_seventy.p68.Problem68;

public class Problems61to70 {
    public static void problem68() {
        RunTimeTimer timer = new RunTimeTimer();
        timer.setStart();
        String[] solution = new Problem68().runAlgorithm(10);
        timer.setEnd();
        for(String s : solution) {
            if(s != null) System.out.println(s);
        }
        System.out.println();
        timer.print();
    }
}
