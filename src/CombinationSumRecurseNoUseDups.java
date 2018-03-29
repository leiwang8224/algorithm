import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumRecurseNoUseDups {
    public static void main (String args[]) {
        int[] nums = new int[] {1, 2, 7};
        List<List<Integer>> list = subsets(nums,10);
        for (List<Integer> subList : list) {
            System.out.println(Arrays.toString(subList.toArray()));
        }
    }

    private static List<List<Integer>> subsets(int[] nums, int target) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, target, 0);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int remain, int start) {
        System.out.println("entering backtrack with remian = "+ remain);
        if(remain < 0) return;
        else if(remain == 0) list.add(new ArrayList<>(tempList));
        else{
            for(int i = start; i < nums.length; i++){
                if (i > start && nums[i] == nums[i-1]) continue;
                tempList.add(nums[i]);
                System.out.println("tempList = " + Arrays.toString(tempList.toArray()) + " i = " + i);
                backtrack(list, tempList, nums, remain - nums[i], i+1); // not i + 1 because we can reuse same elements
                tempList.remove(tempList.size() - 1);
            }
        }
        System.out.println("exit backtrack with remian = "+ remain + " tempList = " + Arrays.toString(tempList.toArray()));

    }


}
