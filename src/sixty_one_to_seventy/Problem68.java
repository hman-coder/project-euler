package sixty_one_to_seventy;

import helpers.ArrayHelpers;

import java.util.Arrays;

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
public class Problem68 {
    public String[] runAlgorithm(int n) {
        assert n%2 == 0;

        // We will start indexing at 1 for easier processing, which explains why there is a +1
        Integer[][] solutions = new Integer[2*n][n+1];

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

            subroutine(availableValues, next, sum, 1, solutions, i);
        }

        String[] finalSolution = new String[solutions.length];
        int fs = 0;
        for(Integer[] s : solutions) {
            if (s != null && s[0] != null) {
                finalSolution[fs++] = solutionToProperStringFormat(s);
            }
        }

        return finalSolution;
    }

    /**
     * In something close to the Dynamic Programming paradigm, the solution is constructed as follows.
     * Define a {complementary value} as the value that a pair needs to reach the sum. That is, for a pair [i,j]
     * compValue = sum - [i+j].
     * 1) pick the first index that is available (i). This is always the outer (external) node.
     * 2) pick another that is available (j). This is always the internal (inner) node.
     * 3) Check whether the complementary value is already assigned to a previous pair.
     * 4) If this pair of solution already exists in an optimal solution of the same sum, then skip.
     * 5) Store these two values.
     * 6) If there is still room for more pairs to be added, the call recursively.
     * 7) If a solution is found, set the values of i, j, and the complementary value back to how they were.
     */
    private void subroutine(Integer[] availableValues,
                                             boolean[] next,
                                             int sum,
                                             int nextGroupName,
                                             Integer[][] solutions,
                                             int[] solutionIndex) {
        // We assume the the second index is the inner node
        // 1) pick the first index that is available
        for(int i = 1; i < availableValues.length; i++) {
            if(availableValues[i] != 0) continue;
            // Since i is always the outer node then it should never be picked if it's false in next (because at that
            // point it would be marked as an inner node in a previous iteration)
            if (! next[i]) continue;
            next[i] = false;
            availableValues[i] = nextGroupName;

            // 2) pick the next index that is available
            for(int j = 1; j < availableValues.length; j++) {
                if(j == i) continue;
                if(availableValues[j] != 0) continue;
                // Let elementsSum = i + j
                int elementsSum = i + j;
                if(elementsSum >= sum) continue;

                // Let compValue = sum - (i+j)
                int compValue = sum - elementsSum;

                // 3) If compValue is equal to one of the already picked values then continue.
                if(compValue == i || compValue == j) continue;

                // 4) if compValue > the highest index then the pair doesn't work. continue.
                if(compValue > availableValues.length-1) continue;

                // 5) If compValue is not available in next, then the pair doesn't work. continue
                if(! next[compValue]) continue;

                // 6) If the pair exists in a previous solution of a problem of size sum, then skip.
                if(solutionForThisPairDoesExist(sum, new int[]{i,j}, solutions)) continue;

                // ALL CONDITIONS ARE PASSED

                // 7) mark the two numbers with the same group name. j is the inner node
                availableValues[j] = -1 * availableValues[i];

                // 8) set compValue to be false in next.
                next[compValue] = false;

                // 9) set the outer node i to be false in next

                // 10) increase groupName
                nextGroupName++;

                // 11) if there is an index in availableValues (except at index 0) whose value isn't 0, then recurse
                if(ArrayHelpers.arrayContains(availableValues, 0, 1)) {
                    subroutine(availableValues, next, sum, nextGroupName, solutions, solutionIndex);

                } else {
                    // 12) a solution has been found.
                    Integer[] solution = formattedSolutionFromAvailableValues(availableValues);
                    // the 0 index in the solution is the sum
                    solution[0] = sum;
                    solutions[solutionIndex[0]] = solution;
                    solutionIndex[0]++;
                }

                // 13) Set everything back the way it was.
                availableValues[j] = 0;
                next[compValue] = true;
            }

            availableValues[i] = 0;
            next[i] = true;
        }
    }


    // A pair in a certain order exists in only 0 or 1 optimal solution for a problem of size sum
    private static boolean solutionForThisPairDoesExist(int sum, int[] pair, Integer[][] solutions) {
        for(Integer[] solution : solutions) {
            if(solution != null && solution[0] != null && solution[0] == sum)
                for(int i = 1; i < solution.length; i+=2) {
                    if(pair[0] == solution[i] && pair[1] == solution[i+1]) {
                        return true;
                    }
                }

        }
        return false;
    }

    /**
     * Returns a properly formatted array of the solution. This format will have the sum at index 0. Then, each pair
     * will occupy to sccessive indices. For example, [9, 6, 3, 4, 2, ....] indicates that, for a sum of 9, the pairs
     * are  [6,3], [4,2], and so on. These pairs are NOT necessarily sorted properly.
     */
    private static Integer[] formattedSolutionFromAvailableValues(Integer[] availableValues) {
        Integer[] ret = new Integer[availableValues.length];
        ret[0] = availableValues[0];
        int retIndex = 1;
        for(int i = 1; i < availableValues.length; i++) {
            int value = availableValues[i];
            if(value > 0) {
                for(int j = 1; j < availableValues.length; j++) {
                    if(availableValues[j] == value * -1) {
                        ret[retIndex++] = i;
                        ret[retIndex++] = j;
                        break;
                    }
                }
            }
        }

        return ret;
    }

    /**
     * Turns a solution (outputted by the method formattedSolutionFromAvailableValues).
     */
    private String solutionToProperStringFormat(Integer[] solution) {
        StringBuilder sb = new StringBuilder();
        sb.append("- sum: ").append(solution[0]).append(":   ");

        int value = Integer.MAX_VALUE;
        int index = -1;

        for (int i = 1; i < solution.length; i += 2) {
            if (solution[i] < value) {
                value = solution[i];
                index = i;
            }
        }

        outer_loop:
        for(int i = 0; i<(solution.length-1) /2; i++) {
            for(int j = 1; j < solution.length; j+=2) {
                if(solution[index] + solution[index + 1] + solution[j+1] == solution[0]) {
                    sb.append(solution[index]).append(solution[index+1]).append(solution[j+1]);
                    index = j;
                    continue outer_loop;
                }
            }
        }
        sb.append(" .");
        return sb.toString();
    }
}
