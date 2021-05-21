public class ProblemsSixtyToSeventy {
    /**
     * (The description of the problem is long and is supported by illustrations. Go to
     * @link https://projecteuler.net/problem=68 for more details)
     * Using the numbers 1 to 10, and depending on arrangements, it is possible to form 16- and 17-digit strings.
     * What is the maximum 16-digit string for a "magic" 5-gon ring?
     *
     * Note: This problem might have a Dynamic Programming solution. However, I will work firstly on the first thing
     * that came to mind, and then check if a DP solution exists.
     *
     * answer:
     * Let's assume the input is n - which marks the highest number in the given set, with all precedent numbers
     * included in the set. n must be even, otherwise a magic n-gon ring cannot be formed.
     *
     * Observations & restrictions:
     * - The sum of each line of numbers s follows this bound: (1.5 * n) < s < (2 * n)
     * - The lower the sum s, the better the solution. That's because, in case of s being low, the outer ring will be
     * mostly filled with higher values, and vice versa.
     * - We probably cannot do better than Brute Force. But we can do a smart one.
     *
     * Key idea: Let s be the sum of each line of numbers. Loop from s = (1.5 * n) to s = (2 * n). We can reduce the
     * upper bound later based on observations. We will also use a memo array to store the upcoming information.
     */
    public static void problem68(int n) {
        int[] memo = new int[n/2];

    }
}
