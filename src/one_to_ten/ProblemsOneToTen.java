package one_to_ten;

import helpers.RunTimeTimer;
import one_to_ten.p01.Problem1;
import one_to_ten.p02.Problem2;
import one_to_ten.p03.Problem3;
import one_to_ten.p04.Problem4;

import java.util.Arrays;

public class ProblemsOneToTen {
    public static void problem1() {
        RunTimeTimer timer = new RunTimeTimer();
        timer.setStart();
        Problem1 p1 = new Problem1();
        long solution = p1.runAlgorithm(3,5,1000);
        timer.setEnd();
        System.out.println(solution);
        System.out.println();
        timer.print();
    }

    public static void problem2() {
        RunTimeTimer timer = new RunTimeTimer();
        timer.setStart();
        Problem2 p2 = new Problem2();
        long solution = p2.runAlgorithm(4000000);
        timer.setEnd();
        System.out.println(solution);
        System.out.println();
        timer.print();
    }

    public static void problem3() {
        RunTimeTimer timer = new RunTimeTimer();
        timer.setStart();
        Problem3 p3 = new Problem3();
        long[] solution = p3.runAlgorithm(600851475143L, 600851475143L, new long[]{});
        timer.setEnd();
        System.out.println(Arrays.toString(solution));
        System.out.println();
        timer.print();
    }

    public static void problem4() {
        RunTimeTimer timer = new RunTimeTimer();
        timer.setStart();
        Problem4 p4 = new Problem4();
        long[] solution = p4.runAlgorithm(3);
        timer.setEnd();
        System.out.println(Arrays.toString(solution));
        System.out.println();
        timer.print();
    }
}
