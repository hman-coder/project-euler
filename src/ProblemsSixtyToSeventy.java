import java.util.Arrays;

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
     * @Observations_&_restrictions:
     * - The sum of each line of numbers s follows this bound: (1.5 * n) < s < (2 * n)
     * - The lower the sum s, the better the solution. That's because, in case of s being low, the outer ring will be
     * mostly filled with higher values, and vice versa.
     * - We probably cannot do better than Brute Force. But we can do a smart one.
     *
     * @Key_idea: Let s be the sum of each line of numbers.
     * Let g be a graph like the one shown in the problem definition. Let g` = g without the edges between the inner
     * nodes. In this case what we will see is a group of SCC's consisting of two nodes and one edge. Applying that to
     * g we get: [4,3], [6,2], [5,1] (outer node always at index 0, inner one at 1). Let f be that set of SCC's. Let sf(x)
     * be the sum of elements in f[x]. Let nf(x) be the inner node of f[x+1] (that is: nf(x) = f[x+1][1]).
     * We will notice that sf(x) = s - nf(x) (call this equation *). I.e, sf(x) is s minus the inner node in the next SCC (if x is the last
     * index then replace x+1 with 0).
     * So what we need to do is define a proper f according to these rules:
     * - each sf(x) must have a unique value, call it u(x). That's because nf(x) is also unique and cannot have an edge
     * to another value.
     * - The difference between u(x) and s must not be greater than n, because otherwise there wouldn't be a value of
     * nf(x) big enough to hold equation *.
     *
     */
    public static String[] problem68(int n) {
        assert n%2 == 0;

        // There are at most 2n solutions that satisfy the constraints
        String[] solutions = new String[2*n];

        // We will start indexing at 1 for easier processing, which explains why there is a +1

        int lowerBound = (int) (1.5 * n); // Since n is even then 1.5*n must be an int;
        int upperBound = 2 * n;

        // solutions index
        int i = 0;
        for(int sum = lowerBound; sum <= upperBound; sum++) {
            // availableValues will reference the pairs of values. If availableValues[x] = 0, then x has not been
            // picked yet. Pairs of values have the same absolute value. However, the inner node value will have a
            // negative value. For example if a pair p = [1,8] with 8 being the inner node value, then
            // availableValues[1] = y, availableValues[8] = -y.
            int[] availableValues = new int[n+1];
            Arrays.fill(availableValues, 0);

            // next will determine whether a value x is available for being sum - sum of SCC (by calling next[x])
            boolean[] next = new boolean[n+1];
            Arrays.fill(next, true);

            int[] solution = problem68_subroutine(availableValues, next, sum, 1);
            if(solution.length != 0)
                solutions[i++] = "sum: " + sum + ":   " + problem68_solutionExtractor(solution);
        }
        return solutions;
    }

    private static int[] problem68_subroutine(int[] availableValues, boolean[] next, int sum, int nextGroupName) {
        // 1) pick the first index that is available
        for(int i = 1; i < availableValues.length; i++) {
            if(availableValues[i] != 0) continue;

            // 2) pick the next index that is available
            for(int j = 1; j < availableValues.length; j++) {
                if(j == i) continue;
                if(availableValues[j] != 0) continue;
                // Let elementsSum = i + j
                int elementsSum = i + j;
                if(elementsSum >= sum) continue;

                // Let nextValue = sum - (i+j)
                int nextValue = sum - elementsSum;

                // If nextValue is equal to one of the already picked values then continue.
                if(nextValue == i || nextValue == j) continue;

                // 3) if x > the highest index then the pair doesn't work. continue.
                if(nextValue > availableValues.length-1) continue;

                // 4) If x is not available in next, then the pair doesn't work. continue
                if(! next[nextValue]) continue;

                // 5) mark the two numbers with the same group name
                availableValues[j] = availableValues[i] = nextGroupName;

                // 6) set x to be false in next.
                next[nextValue] = false;

                // 7) if x is a part of a pair, set the value of the other value in the pair to be false in
                // next, and set x to be negative in availableValues
                int groupNameOfX = availableValues[nextValue];
                if(groupNameOfX != 0) {
                    availableValues[nextValue] *= -1;
                    for(int x2 = 1; x2 < availableValues.length; x2++)
                        if(nextValue != x2 && availableValues[x2] == groupNameOfX) next[x2] = false;
                }

                // 8) if one of the picked numbers v is not available in next, then v should have a negative value, and
                // the other value in the pair should be set to false in next.
                if(! next[i]) availableValues[i] *= -1;
                if(! next[j]) availableValues[j] *= -1;
                if(! next[i] || ! next[j]) next[i] = next[j] = false;

                // 9) increase groupName
                nextGroupName++;

                // 10) if there is an index in availableValues (except 0) whose value isn't 0, then return a recursion
                for(int t = 1; t < availableValues.length; t++)
                    if(availableValues[t] == 0) {
                        return problem68_subroutine(availableValues, next, sum, nextGroupName);
                    }

                // 11) a solution has been found. return it.
                return availableValues;
            }
        }


        return new int[]{};
    }


    private static String problem68_solutionExtractor(int[] solution) {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < solution.length-1; i++) {
            int curr = solution[i];
            for(int j = i+1; j < solution.length; j++){
                int curr2 = solution[j];
                if(curr2 == curr * -1) {
                    sb
                            .append(Math.max(curr, curr2) == curr ? i : j)
                            .append(",")
                            .append(Math.min(curr, curr2) == curr ? i : j)
                            .append(" ;; ");
                }
            }
        }

        return sb.toString();
    }
}
