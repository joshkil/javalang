import java.util.Map;
import java.util.HashMap;

public class MinCostStairs{

    public static int calculateMinMemo(int[] stairs){
        Map<Integer, Integer> memo = new HashMap<Integer, Integer>();
        return Math.min(cost(stairs, 0, memo), cost(stairs, 1, memo));
    }

    public static int calculateMinTab(int[] stairs){
        int[] tab = new int[stairs.length]; // java int arrays initialize to 0

        // Initialize the last two steps to their cost
        tab[tab.length-1] = stairs[tab.length-1];
        tab[tab.length-2] = stairs[tab.length-2];

        // Work backward down the stairs tabulating the best answer from each step
        for(int i = tab.length-3; i >= 0; i--){
            tab[i] = stairs[i] + Math.min(tab[i+1], tab[i+2]);
        }
        return tab[0];
    }
    
    /*
     * Calculate the cost to reach the top of the stairs from stair n. 
     */
    public static int cost(int[] stairs, int n, Map<Integer, Integer> memo){
        if(n == stairs.length){
            // we are at the top
            return 0;
        }
        if(n == stairs.length-1){ // we are on the final step
            return stairs[stairs.length-1];
        }
        if(n == stairs.length-2){ // we are on the next to last step
            return stairs[stairs.length-2];
        }
        if(memo.containsKey(n)){
            return memo.get(n);
        }else{
            memo.put(n, stairs[n] + Math.min(cost(stairs, n+1, memo), cost(stairs, n+2, memo)));
        }
        return memo.get(n);
    }

    public static void main(String[] agrs){
        System.out.println("Answer to MinCostStairs:");
        int[] stairs = new int[]{1, 5, 3, 2, 6, 1};
        System.out.println(calculateMinMemo(stairs));
        System.out.println(calculateMinTab(stairs));
    }
}

