package helpers;

/**
 * Simplifies calculation of runtime of an algorithm
 */
public class RunTimeTimer {
    long start;
    long end;

    public void setStart() {
        start = System.currentTimeMillis();
    }

    public void setEnd() {
        end = System.currentTimeMillis();
    }

    public void print() {
        System.out.println("Time in millis: " + (end - start));
    }
}
