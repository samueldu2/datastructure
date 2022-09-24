package com.samueldu.leetcode.topinterviewquestions.others;

/**
 * Hamming Distance
 *
 * Solution
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 *
 * Given two integers x and y, return the Hamming distance between them.
 *
 *
 *
 * Example 1:
 *
 * Input: x = 1, y = 4
 * Output: 2
 * Explanation:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 *        ↑   ↑
 * The above arrows point to positions where the corresponding bits are different.
 * Example 2:
 *
 * Input: x = 3, y = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * 0 <= x, y <= 231 - 1
 */
public class HammingDistance {
    /**
     * Approach 1: Built-in BitCounting Functions
     * Intuition
     *
     * First of all, let us talk of the elephant in the room. As one can imagine, we have various built-in functions that could count the bit 1 for us, in all (or at least most of) programming languages. So if this is the task that one is asked in a project, then one should probably just go for it, rather than reinventing the wheel. We given two examples in the following.
     *
     * Now, since this is a LeetCode problem, some of you would argue that using the built-in function is like "implementing a LinkedList with LinkedList", which we fully second as well. So no worry, we will see later some fun hand-crafted algorithms for bit counting.
     *
     * Algorithm
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: \mathcal{O}(1)O(1)
     *
     * There are two operations in the algorithm. First, we do the XOR operation which takes a constant time.
     *
     * Then, we call the built-in bitCount function. In the worst scenario, the function would take \mathcal{O}(k)O(k) time where kk is the number of bits for an integer number. Since the Integer type is of fixed size in both Python and Java, the overall time complexity of the algorithm becomes constant, regardless the input numbers.
     *
     * Space Complexity: \mathcal{O}(1)O(1), a temporary memory of constant size is consumed, to hold the result of XOR operation.
     *
     * We assume that the built-in function also takes a constant space.
     * @param x
     * @param y
     * @return
     */
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);

    }


    /**
     *Approach 2: Bit Shift
     * Intuition
     *
     * In order to count the number of bit 1, we could shift each of the bit to either the leftmost or the rightmost position and then check if the bit is one or not.
     *
     * More precisely, we should do the logical shift where zeros are shifted in to replace the discarded bits.
     *
     * pic
     *
     * Here we adopt the right shift operation, where each bit would has its turn to be shifted to the rightmost position. Once shifted, we then check if the rightmost bit is one, which we can use either the modulo operation (i.e. i % 2) or the bit AND operation (i.e. i & 1). Both operations would mask out the rest of the bits other than the rightmost bit.
     *
     * Algorithm
     *
     *
     * Complexity Analysis
     *
     * Time Complexity: \mathcal{O}(1)O(1), since the Integer is of fixed size in Python and Java, the algorithm takes a constant time. For an Integer of 32 bit, the algorithm would take at most 32 iterations.
     *
     * Space Complexity: \mathcal{O}(1)O(1), a constant size of memory is used, regardless the input.
     */

    public int hammingDistanceBitByBitCheck(int x, int y) {
        int xor = x ^ y;
        int distance = 0;
        while (xor != 0) {
            if (xor % 2 == 1)
                distance += 1;
            xor = xor >> 1;
        }
        return distance;
    }

    /**
     *Approach 3: Brian Kernighan's Algorithm
     * Intuition
     *
     * In the above approach, one might wonder that "rather than shifting the bits one by one, is there a faster way to count the bits of one ?". And the answer is yes.
     *
     * If we is asked to count the bits of one, as humans, rather than mechanically examining each bit, we could skip the bits of zero in between the bits of one, e.g. 10001000.
     *
     * In the above example, after encountering the first bit of one at the rightmost position, it would be more efficient if we just jump at the next bit of one, skipping all the zeros in between.
     *
     * This is the basic idea of the Brian Kernighan's bit counting algorithm, which applies some smart bit and arithmetic operations to clear the rightmost bit of one. Here is the secret recipe.
     *
     * When we do AND bit operation between number and number-1, the rightmost bit of one in the original number would be cleared.
     *
     * pic
     *
     * Based on the above idea, we then can count the bits of one for the input of 10001000 in 2 iterations, rather than 8.
     *
     * Algorithm
     *
     *
     * Note, according to the online book of Bit Twiddling Hacks, the algorithm was published as an exercise in 1988, in the book of the C Programming Language 2nd Ed. (by Brian W. Kernighan and Dennis M. Ritchie), though on April 19, 2006 Donald Knuth pointed out that this method "was first published by Peter Wegner in CACM 3 (1960), 322. (Also discovered independently by Derrick Lehmer and published in 1964 in a book edited by Beckenbach.)". By the way, one can find many other tricks about bit operations in the aforementioned book.
     *
     * Complexity Analysis
     *
     * Time Complexity: \mathcal{O}(1)O(1).
     *
     * Similar as the approach of bit shift, since the size (i.e. bit number) of integer number is fixed, we have a constant time complexity.
     *
     * However, this algorithm would require less iterations than the bit shift approach, as we have discussed in the intuition.
     *
     * Space Complexity: \mathcal{O}(1)O(1), a constant size of memory is used, regardless the input.
     */

    public int hammingDistanceBrainKernighan(int x, int y) {
        int xor = x ^ y;
        int distance = 0;
        while (xor != 0) {
            distance += 1;
            // remove the rightmost bit of '1'
            xor = xor & (xor - 1);
        }
        return distance;
    }
}
