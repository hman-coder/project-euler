package one_to_ten;

import helpers.RunTimeTimer;
import one_to_ten.p01.Problem1;
import one_to_ten.p02.Problem2;
import one_to_ten.p03.Problem3;
import one_to_ten.p04.Problem4;
import one_to_ten.p05.Problem5;
import one_to_ten.p06.Problem6;
import one_to_ten.p07.Problem7;

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

    public static void problem5() {
        RunTimeTimer timer = new RunTimeTimer();
        Problem5 p5 = new Problem5();
        timer.setStart();
        long solution = p5.runAlgorithm(20);
        timer.setEnd();
        System.out.println(solution);
        System.out.println();
        timer.print();

        System.out.println("\n-- Alternative method");
        timer.setStart();
        solution = p5.runAlternativeAlgorithm(20);
        timer.setEnd();
        System.out.println(solution);
        System.out.println();
        timer.print();
    }

    public static void problem6() {
        RunTimeTimer timer = new RunTimeTimer();
        Problem6 p6 = new Problem6();
        timer.setStart();
        long solution = p6.runAlgorithm(100);
        timer.setEnd();
        System.out.println(solution);
        System.out.println();
        timer.print();
    }

    public static void problem7() {
        RunTimeTimer timer = new RunTimeTimer();
        Problem7 p7 = new Problem7();
        timer.setStart();
        long solution = p7.runAlgorithm(10001);
        timer.setEnd();
        System.out.println(solution);
        System.out.println();
        timer.print();
    }
}
