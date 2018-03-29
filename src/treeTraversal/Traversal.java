package treeTraversal;

import ListNode.ListNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Traversal {
    private static ListNode list;
    public static void main(String[] args) {
        TreeNode head = new TreeNode(0);
        head.left = new TreeNode(1);
        head.right = new TreeNode(2);
        head.left.left = new TreeNode(3);
        head.left.right = new TreeNode(4);
        head.right.left = new TreeNode(5);
        head.right.right = new TreeNode(6);

        TreeNodeString headStr = new TreeNodeString("head");
        headStr.left = new TreeNodeString("l");
        headStr.left.left = new TreeNodeString("ll");
        headStr.left.right = new TreeNodeString("lr");
        headStr.right = new TreeNodeString("r");
        headStr.right.left = new TreeNodeString("rl");
        headStr.right.right = new TreeNodeString("rr");

        int[] array = new int[]{3,24,34,1,43,23,5,2,21,43,12,4};
        ListNode listNodeHead = new ListNode(0);
        listNodeHead.next = new ListNode(1);
        listNodeHead.next.next = new ListNode(2);
        listNodeHead.next.next.next = new ListNode(3);
        listNodeHead.next.next.next.next = new ListNode(4);

//        sortedLinkedListToBalancedBST(listNodeHead);
        printBinaryTree(sortedLinkedListToBalancedBST(listNodeHead));
//        TreeNode newBST = arrayToBST(array);
//        printBinaryTree(newBST);

//        System.out.println("DFS");
//        dfs(head);
//        System.out.println("total depth = " + getDepth(head));
//        bfs(head);
//        System.out.println("invertTree");
//        invertTree(head);
//        System.out.println(tree2str(head));

//     System.out.println("diameter is " + getDiameter(head));
    }

    private static void printBinaryTree(TreeNode head)  {
        if (head == null) return;
        System.out.println(head.getVal());
        printBinaryTree(head.left);
        printBinaryTree(head.right);
    }

    private static TreeNode arrayToBST(int[] array) {
        Arrays.sort(array);
        return sortedArrayToBST(array, 0, array.length-1);
    }

    private static TreeNode sortedArrayToBST(int[] array, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(array[mid]);

        node.left = sortedArrayToBST(array, start, mid-1);
        node.right = sortedArrayToBST(array, mid + 1, end);
        return node;
    }

    private static TreeNode sortedLinkedListToBalancedBST(ListNode head) {
        int n = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            n++;
        }
        // use list to keep track of head
        list = head;
        return sortedLinkedListToBalancedBST(head, 0, n - 1);
    }

    /**
     * Bottom up approach
     * @param head
     * @param start
     * @param end
     * @return
     */
    private static TreeNode sortedLinkedListToBalancedBST(ListNode head, int start, int end) {
        if (start > end) return null;
        int mid = (start + end) / 2;
        TreeNode leftChild = sortedLinkedListToBalancedBST(head, start, mid-1);
        TreeNode parent = new TreeNode(list.getVal());
        parent.left = leftChild;
//        head = head.next;
        list = list.next;
        parent.right = sortedLinkedListToBalancedBST(head,mid + 1, end);
        return parent;
    }

    private static void dfs(TreeNode head) {
        if (head == null) return;
        System.out.println(head.getVal());
        dfs(head.left);
        dfs(head.right);
    }

    private static void bfs(TreeNode head) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(head);

        while (!q.isEmpty()) {
            TreeNode cur = q.remove();
            System.out.println(cur.getVal());

            if (cur.left != null)
                q.add(cur.left);
            if (cur.right != null)
                q.add(cur.right);
        }
    }

    private static int getDepth(TreeNode head) {
        System.out.println("entering getDepth");
        if (head == null) return 0;
        // if leaf then return 1
        if (head.left == null && head.right == null) return 1;

        // if left is null travel right
        if (head.left == null) {
            System.out.println("left is null travel right");
            return getDepth(head.right) + 1;
        }
        // if right is null travel left
        if (head.right == null) {
            System.out.println("right is null travel left");
            return getDepth(head.left) + 1;
        }

        int leftDepth = getDepth(head.left);
        int rightDepth = getDepth(head.right);
        System.out.println("at very last statement left = " + leftDepth + " rightDepth = " + rightDepth);

        return Math.min(leftDepth,rightDepth) + 1;
    }

    /**
     * check if binary tree is balanced
     * @param head
     * @return
     */
    private static int dfsHeight(TreeNode head) {
        if (head == null) return 0;

        int leftHeight = dfsHeight(head.left);
        if (leftHeight == -1) return -1;
        int rightHeight = dfsHeight(head.right);
        if (rightHeight == -1) return -1;

        // left and right differs more than 1 in height
        if (Math.abs(leftHeight - rightHeight) > 1) return -1;
        return Math.max(leftHeight,rightHeight) + 1;
    }

    boolean isBalanced(TreeNode head) {
        return dfsHeight(head) != 1;
    }

    public static TreeNode invertTree(TreeNode head) {
        if (head != null)
            System.out.println("entering invertTree with head = " + head.getVal());
        if (head == null)
            return head;

        TreeNode left = head.left;
        TreeNode right = head.right;

        System.out.println("recurse on right");
        head.left = invertTree(right);
        System.out.println("recurse on left");
        head.right = invertTree(left);

        return head;
    }

    public static String tree2str(TreeNode head) {
        if (head == null) return "";
        String result = head.getVal() + "";

        String left = tree2str(head.left);
        String right = tree2str(head.right);

        // both left and right nodes are empty
        if (left == "" && right == "") return result;
        // left node is empty
        if (left == "") return result + "()" + "(" + right + ")";
        // right node is empty
        if (right == "") return result + "(" + left + ")";
        // left and right nodes are not empty
        return result + "(" + left + ")" + "(" + right + ")";
    }

    public static int getDiameter(TreeNode head) {
        int[] result = new int[1];
        getMaxDepth(head,result);
        return result[0];
    }

    private static int getMaxDepth(TreeNode head, int[] result) {
        System.out.println("Entering getMaxDepth result = " + result[0]);
        if (head == null) return 0;

        System.out.println("recurse left result = " + result[0]);
        int left = getMaxDepth(head.left, result);
        System.out.println("recurse right result = " + result[0]);
        int right = getMaxDepth(head.right, result);

        // result keeps count of the depth counter
        result[0] += Math.max(result[0],left + right);
        System.out.println("returning from function result = " + result[0] + " left = " + left + " right = " + right);
        return Math.max(left,right)+1;
    }

    private static int getMaxDepth2(TreeNode head) {
        if (head == null) return 0;
        return Math.max(getMaxDepth2(head.left),getMaxDepth2(head.right)) + 1;
    }

    /**
     * O(n) runtime, O(logN) memory
     * @param head
     * @return
     */
    private static int getMinDepth(TreeNode head) {
        if (head == null) return 0;
        if (head.left == null) return getMinDepth(head.right) + 1;
        if (head.right == null) return getMinDepth(head.left) + 1;
        return Math.min(getMinDepth(head.left),getMinDepth(head.right)) + 1;
    }

    private static int getMinDepthBFS(TreeNode head) {
        if (head == null) return 0;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(head);
        // init rightMost
        TreeNode rightMost = head;
        int depth = 1;
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            // stop as soon as the first leaf is found
            if (node.left == null && node.right == null) break;
            if (node.left != null) q.add(node.left);
            if (node.right != null) q.add(node.right);
            // find the rightMost node, that's where the end of level is.
            if (node == rightMost) {
                depth++;
                rightMost = (node.right != null) ? node.right : node.left;
            }
        }
        return depth;
    }

    private static void printLeaves(TreeNode head) {
        if (head != null) {
            printLeaves(head.left);
            // print if it's leaf
            if (head.left == null && head.right == null) {
                System.out.println(head.getVal() + " ");
            }
            printLeaves(head.right);
        }
    }

    private static void printBoundaryLeft(TreeNode head) {
        if (head != null) {
            if (head.left != null) {
                // print top down order so print first
                System.out.println(head.getVal() + " ");
                printBoundaryLeft(head.left);
            } else if (head.right != null) {
                System.out.println(head.getVal() + " ");
                printBoundaryLeft(head.right);
            }
        }
    }

    private static void printBoundaryRightBottomUpOrder(TreeNode head) {
        if (head != null) {
            if (head.right != null) {
                // to ensure bottom up order, first call for right subtree
                // then print this node
                printBoundaryRightBottomUpOrder(head.right);
                System.out.println(head.getVal() + " ");
            }
        }
    }

    private static void printBoundary(TreeNode head) {
        if (head != null) {
            System.out.println(head.getVal() + " ");

            printBoundaryLeft(head.left);

            printLeaves(head.left);
            printLeaves(head.right);

            printBoundaryRightBottomUpOrder(head.right);
        }
    }

    private static boolean isBST(TreeNode head) {
        return valid(head, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean valid(TreeNode head, int minValue, int maxValue) {
        if (head == null) return true;
        return head.getVal() > minValue && head.getVal() < maxValue &&
                valid(head.left, minValue, head.getVal()) &&
                valid(head.right, head.getVal(), maxValue);
    }

    private static boolean isBSTInOrderTraversal(TreeNode head, TreeNode prev) {
        if (head == null) return true;
        if (isBSTInOrderTraversal(head.left, prev)) {
            // needs to be monotonically increasing traversing in level
            if (prev != null && head.getVal() <= prev.getVal()) return false;
            prev = head;
            return isBSTInOrderTraversal(head.right, prev);
        }
        return false;
    }

    private static int maxPathSum(TreeNode head) {
        int[] maxsum = new int[1];
        maxsum[0] = Integer.MIN_VALUE;
        findMax(head,maxsum);
        return maxsum[0];
    }

    private static int findMax(TreeNode head, int[] maxsum) {
        if (head == null) return 0;
        int left = findMax(head.left,maxsum);
        int right = findMax(head.right,maxsum);
        maxsum[0] = Math.max(head.getVal() + left + right, maxsum[0]);
        int ret = head.getVal() + Math.max(left, right);
        return ret > 0 ? ret : 0;
    }
}
