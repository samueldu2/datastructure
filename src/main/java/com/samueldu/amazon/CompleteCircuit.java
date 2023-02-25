package com.samueldu.amazon;

/**
 * There are two integer arrays A and B, of size N given. There are N gas stations along a circular route, where the amount of gas at station i is A[i]. You have a car with an unlimited gas tank, and it costs B[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 * Return the minimum starting gas station's index if you can travel around the circuit once, otherwise return -1.
 * <p>
 * You can only travel in one direction. i to i+1, i+2, … n-1, 0, 1, 2.. Completing the circuit means starting at i and ending up at i again.
 */
public class CompleteCircuit {

    /**
     * The question says that we have two arrays of int. The first one is cost, and another one is gas. We have a car and need to travel to "n" places where n is the above two arrays' length.
     * <p>
     * Now, according to the question, we can start from a point. Let's say 3, so that means to reach the next point, we need gas greater than the cost at 3.
     * <p>
     * See the example:
     * <p>
     * Suppose the gas array is: {1, 2, 3, 4, 5}
     * <p>
     * And the cost arrays is: {3, 4, 5, 1, 2}
     * <p>
     * Here, we can see that the 3rd term from gas is: 4 (Index starts from 0).
     * <p>
     * And the same for the cost is 1, so to go for the 5th position our gas should be greater than the cost. As we can see in this case, it is as 4 > 1.
     * <p>
     * Here you see that this much gas is used to go to the next point. Even after reaching there, you will leave with 3 gas as 4-1=3.
     * <p>
     * So, at the next point, you will initially have 3 units of gas.
     * <p>
     * Now, the question is asking from which point we should start so that we can traverse all the points.
     * <p>
     * Now, let's take the gas array and cost array as following:
     * <p>
     * gas = {1,2}
     * <p>
     * cost= {2,1}
     * <p>
     * Now, let's start from the first element that is index 0.
     * <p>
     * So, the gas in our car after first travel will be = gas[0]-cost[0] = 1-2=-1.
     * <p>
     * Since it would be a negative number, that means we can't traverse using the 0 index.
     * <p>
     * Now, let's start from the second element, that is the index 1;
     * <p>
     * The gas in our car after first travel will be = gas[1]-cost[1]=2-1=1.
     * <p>
     * We have left with 1 unit of gas, after that we will come to the 0th index so we will be having = 1+gas[0]-cost[0]=1+1-2=0
     * <p>
     * So, that means we will be able to complete our journey from the 1 index.
     * <p>
     * So, there are two ways to solve this problem:
     * <p>
     * <p>
     * The first solution would be to use two loops and check whether the index exists but will take higher time and O(n²) time complexity.
     * <p>
     * Another way to solve this problem is to follow the steps given below:
     * <p>
     * Take a variable start and initialize it to 0, and another variable end and then initialize it to 1.
     * We will loop from start to end and keep increment end until it reaches the same value as the start. For example, if our array is of length 5 then: 1->2->3->4->0. In that case, we will know that start is the index from where the answer comes from.
     * For storing the current gas in our car, we will use another variable curr and then if at any point of time, if curr becomes negative, that means that index cannot be the starting point. In that case, we will increment the start by 1.
     * Here, the main problem is that we know how to move forward in an array. I mean, we know how to increment the current pointer. But how do we get back? Here, the pointer goes like this: 1->2->3->4->5->6……. and so on. It won't come back like: 1->2->3->4->1.
     * To solve this problem, we have to use a simple trick. Instead of increment the value like : n=n+1, we will use : n=(n+1)%length.
     * In that case, when the pointer gets to the end, it will again start from zero.
     * See the complete code for the solution of the above problem:
     *
     * @param gas
     * @param cost
     * @return
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas.length == 0) {
            return -1;
        }
        if (gas.length == 1) {
            return gas[0] - cost[0] < 0 ? -1 : 0;
        }
        //First we will declare the variables which were start, end
        int start = 0;
        int end = 1;
        //Initially the first value of curr will be the cost incur to go to //the next location
        int curr = gas[0] - cost[0];
        //Running a loop till start meets end, meaning till our cycle gets //completed
        while (start != end) {
            //If the curr value is negative, then keep incrementing the start //till it became positive
            while (curr < 0 && start != end) {
                //We need to remove the value of current start that we have added //before
                curr = curr - (gas[start] - cost[start]);
                start = (start + 1) % gas.length;
                //If we again come back to start, that means no solution exist and //we return -1
                if (start == 0)
                    return -1;
            }
            //Adding the gas that we got after visiting the current index
            curr += gas[end] - cost[end];
            end = (end + 1) % gas.length;
        }
        //If in the end, curr became negative that means it was impossible //to traverse all the location and hence return -1
        if (curr < 0)
            return -1;
        //If that's not the case, then we got the answer.
        return start;
    }
}