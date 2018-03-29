import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecursePermutationDups {
    public static void main (String args[]) {
        int[] nums = new int[] {1, 2, 3, 3};
        List<List<Integer>> list = subsets(nums);
        for (List<Integer> subList : list) {
            System.out.println(Arrays.toString(subList.toArray()));
        }
    }

    private static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, boolean[] used) {
        System.out.println("entry backtrack");
        if (tempList.size() == nums.length) {
            list.add(new ArrayList<>(tempList));
            System.out.println("list = " + Arrays.toString(list.toArray()));
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]) continue;
                System.out.println("enter for loop with i = " + i);
                tempList.add(nums[i]);
                System.out.println("tempList = " + Arrays.toString(tempList.toArray()));
                backtrack(list, tempList, nums, used);
                tempList.remove(tempList.size() - 1);
                System.out.println("before exit backtrack, tempList = " + Arrays.toString(tempList.toArray()));

            }
        }
        System.out.println("exit backtrack");
    }


}
