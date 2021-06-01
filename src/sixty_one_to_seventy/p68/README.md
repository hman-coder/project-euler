# Problem 68

## Definition
Consider the following "magic" 3-gon ring, filled with the numbers 1 to 6, and each line adding to nine.

![3-gon ring](https://projecteuler.net/project/images/p068_1.png)

Working clockwise, and starting from the group of three with the numerically lowest external node (4,3,2 in this example), each solution can be described uniquely. For example, the above solution can be described by the set: 4,3,2; 6,2,1; 5,1,3.

It is possible to complete the ring with four different totals: 9, 10, 11, and 12. There are eight solutions in total.

**Total**	     **Solution Set**
- 9	        4,2,3; 5,3,1; 6,1,2
- 9	        4,3,2; 6,2,1; 5,1,3
- 10        2,3,5; 4,5,1; 6,1,3
- 10        2,5,3; 6,3,1; 4,1,5
- 11        1,4,6; 3,6,2; 5,2,4
- 11        1,6,4; 5,4,2; 3,2,6
- 12        1,5,6; 2,6,4; 3,4,5
- 12        1,6,5; 3,5,4; 2,4,6

By concatenating each group it is possible to form 9-digit strings; the maximum string for a 3-gon ring is 432621513.

Using the numbers 1 to 10, and depending on arrangements, it is possible to form 16- and 17-digit strings. What is the maximum 16-digit string for a "magic" 5-gon ring?

![5-gon ring](https://projecteuler.net/project/images/p068_2.png)

## Solution
Let's assume the input is `n` - which marks the highest number in the given set, with all precedent numbers included in the set. n must be even, otherwise a magic n-gon ring cannot be formed.

### Observations
- The sum of each line of numbers `s` follows this bound: `(1 * n) < s < (2 * n)`
- The lower the sum `s`, the better the solution. That's because, in case of s being low, the outer ring will be mostly filled with higher values, and vice versa.

### Solution
Let `g` be a graph like the one shown in the problem definition. Let `g'` = `g` without the edges between the inner nodes. In this case what we will see is a group of SCC's consisting of two nodes and one edge. Applying that to `g` we get: `[4,3], [6,2], [5,1]` (outer node always at index 0, inner one at 1).
Let `f` be that set of SCC's. Let `sf(x)` be the sum of elements in `f[x]`. Let `nf(x)` be the inner node of `f[x+1]` (that is: `nf(x) = f[x+1][1]`).
We will notice that `sf(x) = s - nf(x)` (call this equation `*`). I.e, `sf(x)` is `s` minus the inner node in the next SCC (if `x` is the last index then replace `x+1` with `0`).
So what we need to do is define a proper `f` according to these rules:
- each `sf(x)` must have a unique value, call it `u(x)`. That's because `nf(x)` is also unique and cannot have an edge to another value.
- The difference between `u(x)` and `s` must not be greater than `n`, because otherwise there wouldn't be a value of`nf(x)` big enough to hold equation `*`.

In something close to the Dynamic Programming paradigm, the solution is constructed as follows. Define a complementary value `c` as the value that a pair needs to reach the sum. That is, for a pair `[i,j]` : `c = sum - [i+j].`
1. Pick the first index that is available (i). This is always the outer (external) node.
2. Pick another that is available (j). This is always the internal (inner) node.
3. Check whether `c` is already assigned to a previous pair.
4. If this pair of solution already exists in an optimal solution of the same sum, then skip.
5. Store these two values.
6. If there is still room for more pairs to be added, recurse.
7. If a solution is found, set the values of `i`, `j`, and `c` value back to how they were.