import helpers.ArrayHelpers;

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
     * @Dynamic_programming: An optimal solution is composed of optimal solutions to smaller sub-problems. If we take
     * a sum s and pick any three numbers from the input that are equal to s, we can try to construct multiple optimal
     * solutions from that.
     *
     * @Brute_force_search: check all arrangements of the array. Find the ones that are compatible with our needs.
     *
     */
    public static String[] problem68(int n) {
        assert n%2 == 0;

        // There are at most 2n solutions that satisfy the constraints
        Integer[][] solutions = new Integer[n*n*n*n][n+1];

        // We will start indexing at 1 for easier processing, which explains why there is a +1

        int lowerBound = n+3;
        int upperBound = 2 * n;

        // solutions index. We use an array so that the subroutine can increment it after adding a new solution (int
        // is a primitive type which is not passed by reference but by object. Arrays are passed by reference)
        int[] i = {0};
        for(int sum = lowerBound; sum <= upperBound; sum++) {

            // availableValues will reference the pairs of values. If availableValues[x] = 0, then x has not been
            // picked yet. Pairs of values have the same absolute value. However, the inner node value will have a
            // negative value. For example if a pair p = [1,8] with 8 being the inner node value, then
            // availableValues[1] = y, availableValues[8] = -y.
            Integer[] availableValues = new Integer[n + 1];
            Arrays.fill(availableValues, 0);

            // next will determine whether a value x is available for being sum - sum of SCC (by calling next[x])
            boolean[] next = new boolean[n + 1];
            Arrays.fill(next, true);

            problem68_subroutine(availableValues, next, sum, 1, solutions, i);
        }

        String[] finalSolution = new String[solutions.length];
        int fs = 0;
        for(Integer[] s : solutions) {
            if (s != null && s[0] != null) {
                finalSolution[fs++] = problem68_solutionExtractor(s);
            }
        }

        return finalSolution;
    }

    private static void problem68_subroutine(Integer[] availableValues, boolean[] next, int sum, int nextGroupName, Integer[][] solutions, int[] solutionIndex) {
        // We are gonna assume the the second index is the inner node
        // 1) pick the first index that is available
        for(int i = 1; i < availableValues.length; i++) {
            if(availableValues[i] != 0) continue;
            // Since i is always the outer node then it should never be picked if it's false in next (because at that
            // point it would be marked as an inner node by a previous iteration)
            if (! next[i]) continue;

            boolean previousNextOfI = next[i];
            next[i] = false;
            availableValues[i] = nextGroupName;

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

                // 3) if nextValue > the highest index then the pair doesn't work. continue.
                if(nextValue > availableValues.length-1) continue;

                // 4) If nextValue is not available in next, then the pair doesn't work. continue
                if(! next[nextValue]) continue;

                if(solutionForThisPairDoesExist(sum, new int[]{i,j}, solutions)) continue;
                // ALL CONDITIONS ARE PASSED

                // 5) mark the two numbers with the same group name. j is the inner node
                availableValues[j] = -1 * availableValues[i];

                // 6) set nextValue to be false in next.
                next[nextValue] = false;

                // 7) set the outer node i to be false in next

                // 8) increase groupName
                nextGroupName++;

                // 9) if there is an index in availableValues (except at index 0) whose value isn't 0, then recurse
                if(ArrayHelpers.arrayContains(availableValues, 0, 1)) {
                    problem68_subroutine(availableValues, next, sum, nextGroupName, solutions, solutionIndex);

                } else {
                // 10) a solution has been found.
                    Integer[] solutionCopy = Arrays.copyOf(availableValues, availableValues.length);
                    // the 0 index in the solution is the sum
                    solutionCopy[0] = sum;
                    solutions[solutionIndex[0]] = solutionCopy;

                    solutionIndex[0] = solutionIndex[0] + 1;
                }

                availableValues[j] = 0;
                next[nextValue] = true;
            }

            availableValues[i] = 0;
            next[i] = previousNextOfI;
        }
    }

    // A pair in a certain order exists in only 0 or 1 optimal solution for a problem of size sum
    private static boolean solutionForThisPairDoesExist(int sum, int[] pair, Integer[][] solutions) {
        for(Integer[] solution : solutions) {
            if(solution != null && solution[0] != null && solution[0] == sum)
                for(int i = 1; i < solution.length; i+=2) {
                    if(pair[0] == solution[i] && pair[1] == solution[i+1]) {
                        System.out.println(true);
                        return true;
                    }
                }

        }

        return false;
    }

    private static String problem68_solutionExtractor(Integer[] solution) {
        StringBuilder sb = new StringBuilder();
        sb.append("{{ sum: ").append(solution[0]).append(":   ");
        int[] copy = Arrays.stream(solution).mapToInt(e -> e == null ? 0 : e).toArray();
        for(int i = 1; i < copy.length-1; i++) {
            int curr = copy[i];
            for(int j = i+1; j < copy.length; j++){
                int curr2 = copy[j];
                if(curr2 == curr * -1) {
                    sb
                            .append(Math.max(curr, curr2) == curr ? i : j)
                            .append(",")
                            .append(Math.min(curr, curr2) == curr ? i : j)
                            .append(" ;; ");
                }
            }
        }

        sb.append("}}\n");
        return sb.toString();
    }
}
