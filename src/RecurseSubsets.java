import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecurseSubsets {
    public static void main (String args[]) {
        int[] nums = new int[] {1, 2, 3};
        List<List<Integer>> list = subsets(nums);
    }

    private static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, 0);
        return list;
    }

    private static void backtrack(List<List<Integer>> list, List<Integer> tempList, int[] nums, int start) {
        System.out.println("entry backtrack");
        list.add(new ArrayList<>(tempList));
        System.out.println("list = " + Arrays.toString(list.toArray()));
        for (int i = start; i < nums.length; i ++) {
            System.out.println("enter for loop with i = " + i);
            tempList.add(nums[i]);
            System.out.println("tempList = " + Arrays.toString(tempList.toArray()));
            backtrack(list, tempList, nums, i + 1);
            tempList.remove(tempList.size() - 1);
            System.out.println("before exit backtrack, tempList = " + Arrays.toString(tempList.toArray()));

        }
        System.out.println("exit backtrack");
    }


}
