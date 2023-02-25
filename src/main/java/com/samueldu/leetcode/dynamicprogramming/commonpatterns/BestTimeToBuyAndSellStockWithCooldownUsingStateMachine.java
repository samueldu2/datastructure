package com.samueldu.leetcode.dynamicprogramming.commonpatterns;

/**
 * Best Time to Buy and Sell Stock with Cooldown
 *
 * Solution
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
 *
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 *
 *
 * Example 1:
 *
 * Input: prices = [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 * Example 2:
 *
 * Input: prices = [1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 */
public class BestTimeToBuyAndSellStockWithCooldownUsingStateMachine {
    /**
     *
     * Approach 1: Dynamic Programming with State Machine
     * Intuition
     *
     * First of all, let us take a different perspective to look at the problem, unlike the other algorithmic problems.
     *
     * Here, we will treat the problem as a game, and the trader as an agent in the game. The agent can take actions that lead to gain or lose of game points (i.e. profits). And the goal of the game for the agent is to gain the maximal points.
     *
     * In addition, we will introduce a tool called state machine, which is a mathematical model of computation. Later one will see how the state machine coupled with the dynamic programming technique can help us solve the problem easily.
     *
     * In the following sections, we will first define a state machine that is used to model the behaviors and states of the game agent.
     *
     * Then, we will demonstrate how to apply the state machine to solve the problem.
     *
     * Definition
     *
     * Let us define a state machine to model our agent. The state machine consists of three states, which we define as follows:
     *
     * state held: in this state, the agent holds a stock that it bought at some point before.
     *
     * state sold: in this state, the agent has just sold a stock right before entering this state. And the agent holds no stock at hand.
     *
     * state reset: first of all, one can consider this state as the starting point, where the agent holds no stock and did not sell a stock before. More importantly, it is also the transient state before the held and sold. Due to the cooldown rule, after the sold state, the agent can not immediately acquire any stock, but is forced into the reset state. One can consider this state as a "reset" button for the cycles of buy and sell transactions.
     *
     * At any moment, the agent can only be in one state. The agent would transition to another state by performing some actions, namely:
     *
     * action sell: the agent sells a stock at the current moment. After this action, the agent would transition to the sold state.
     *
     * action buy: the agent acquires a stock at the current moment. After this action, the agent would transition to the held state.
     *
     * action rest: this is the action that the agent does no transaction, neither buy or sell. For instance, while holding a stock at the held state, the agent might simply do nothing, and at the next moment the agent would remain in the held state.
     *
     * Now, we can assemble the above states and actions into a state machine, which we show in the following graph where each node represents a state, and each edge represents a transition between two states. On top of each edge, we indicate the action that triggers the transition.
     *
     * state machine
     *
     * Notice that, in all states except the sold state, by doing nothing, we would remain in the same state, which is why there is a self-looped transition on these states.
     *
     * Deduction
     *
     * Now, one might wonder how exactly the state machine that we defined can help to solve the problem.
     *
     * As we mentioned before, we model the problem as a game, and the trader as an agent in the game. And this is where our state machine comes into the picture. The behaviors and the states of the game agent can be modeled by our state machine.
     *
     * mario game
     *
     * Given a list stock prices (i.e. price[0...n]), our agent would walk through each price point one by one. At each point, the agent would be in one of three states (i.e. held, sold and reset) that we defined before. And at each point, the agent would take one of the three actions (i.e. buy, sell and rest), which then would lead to the next state at the next price point.
     *
     * Now if we chain up each state at each price point, it would form a graph where each path that starts from the initial price point and ends at the last price point represents a combination of transactions that the agent could perform through out the game.
     *
     * graph of state transition
     *
     * The above graph shows all possible paths that our game agent agent walks through the list, which corresponds to all possible combinations of transactions that the trader can perform with the given price sequence.
     *
     * In order to solve the problem, the goal is to find such a path in the above graph that maximizes the profits.
     *
     * In each node of graph, we also indicate the maximal profits that the agent has gained so far in each state of each step. And we highlight the path that generates the maximal profits. Don't worry about them for the moment. We will explain in detail how to calculate in the next section.
     *
     * Algorithm
     *
     * In order to implement the above state machine, we could define three arrays (i.e. held[i], sold[i] and reset[i]) which correspond to the three states that we defined before.
     *
     * Each element in each array represents the maximal profits that we could gain at the specific price point i with the specific state. For instance, the element sold[2] represents the maximal profits we gain if we sell the stock at the price point price[2].
     *
     * According to the state machine we defined before, we can then deduce the formulas to calculate the values for the state arrays, as follows:
     *
     * \text{sold}[i] = \text{hold}[i-1] + \text{price}[i] \\ \text{held}[i] = \max{(\text{held}[i-1], \quad \text{reset}[i-1] - \text{price}[i])} \\ \text{reset}[i] = \max{(\text{reset}[i-1], \quad \text{sold}[i-1])}sold[i]=hold[i−1]+price[i]
     * held[i]=max(held[i−1],reset[i−1]−price[i])
     * reset[i]=max(reset[i−1],sold[i−1])
     *
     * Here is how we interpret each formulas:
     *
     * \text{sold}[i]sold[i]: the previous state of sold can only be held. Therefore, the maximal profits of this state is the maximal profits of the previous state plus the revenue by selling the stock at the current price.
     *
     * \text{held}[i]held[i]: the previous state of held could also be held, i.e. one does no transaction. Or its previous state could be reset, from which state, one can acquire a stock at the current price point.
     *
     * \text{reset}[i]reset[i]: the previous state of reset could either be reset or sold. Both transitions do not involve any transaction with the stock.
     *
     * Finally, the maximal profits that we can gain from this game would be \max{(\text{sold}[n], \text{reset}[n])}max(sold[n],reset[n]), i.e. at the last price point, either we sell the stock or we simply do no transaction, to have the maximal profits. It makes no sense to acquire the stock at the last price point, which only leads to the reduction of profits.
     *
     * In particular, as a base case, the game should be kicked off from the state reset, since initially we don't hold any stock and we don't have any stock to sell neither. Therefore, we assign the initial values of sold[-1] and held[-1] to be Integer.MIN_VALUE, which are intended to render the paths that start from these two states impossible.
     *
     * As one might notice in the above formulas, in order to calculate the value for each array, we reuse the intermediate values, and this is where the paradigm of dynamic programming comes into play.
     *
     * More specifically, we only need the intermediate values at exactly one step before the current step. As a result, rather than keeping all the values in the three arrays, we could use a sliding window of size 1 to calculate the value for \max{(\text{sold}[n], \text{reset}[n])}max(sold[n],reset[n]).
     *
     * In the following animation, we demonstrate the process on how the three arrays are calculated step by step.
     *
     * Current
     * 6 / 7
     * As a byproduct of this algorithm, not only would we obtain the maximal profits at the end, but also we could recover each action that we should perform along the path, although this is not required by the problem.
     *
     * In the above graph, by starting from the final state, and walking backward following the path, we could obtain a sequence of actions that leads to the maximal profits at the end, i.e. [buy, sell, cooldown, buy, sell].
     * Complexity Analysis
     *
     * Time Complexity: \mathcal{O}(N)O(N) where NN is the length of the input price list.
     *
     * We have one loop over the input list, and the operation within one iteration takes constant time.
     * Space Complexity: \mathcal{O}(1)O(1), constant memory is used regardless the size of the input.
     *
     */
    public int maxProfit(int[] prices) {
        int sold = Integer.MIN_VALUE, held = Integer.MIN_VALUE, reset = 0;

        for (int price : prices) {
            int preSold = sold;

            sold = held + price;
            held = Math.max(held, reset - price);
            reset = Math.max(reset, preSold);
        }

        return Math.max(sold, reset);
    }

}
