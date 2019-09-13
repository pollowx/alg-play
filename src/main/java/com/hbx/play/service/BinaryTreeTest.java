package com.hbx.play.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/28 11:19 AM
 */
public class BinaryTreeTest {

//    public static void main(String[] args) {

    // start
//        int[] sequence = new int[]{2, 4, 3, 7, 9, 10, 5};
//
//        System.out.println(verifySquenceOfBST(sequence));
//
//        System.out.println(myVerifySquenceOfBST(sequence));
//
//        System.out.println(myVerifySquenceOfBSTWithStack(sequence));

    // start
//        TreeNode root = new TreeNode(1);
//
//        TreeNode treeNode4 = new TreeNode(4);
//        TreeNode treeNode5 = new TreeNode(5);
//
//        TreeNode treeNode6 = new TreeNode(6);
//        TreeNode treeNode7 = new TreeNode(7);
//
//        TreeNode treeNode2 = new TreeNode(2);
//        TreeNode treeNode3 = new TreeNode(3);
//
//        treeNode2.left = treeNode4;
//        treeNode2.right = treeNode5;
//
//        treeNode3.right = treeNode6;
//
//        treeNode6.right = treeNode7;
//
//        root.left = treeNode2;
//        root.right = treeNode3;
//
//        System.out.println(treeDepth(root));
//        //System.out.println(treeDepthB(root));
//        System.out.println(treeDepthC(root));

//    }

    // --------------------------------------------------------------------------------------------------------- //

    /**
     * 判断输入序列是否是二叉搜索树的后序遍历，递归
     *
     * @param sequence
     * @return
     */
    public static boolean verifySquenceOfBST(int[] sequence) {
        if (null == sequence || sequence.length == 0) {
            return false;
        }
        // 后序搜索的话，最后一个元素是root节点，所有比他大的在右边，比他小的在左边
        return isBTS(sequence, 0, sequence.length - 1);
    }


    private static boolean isBTS(int[] data, int start, int end) {
        if (start >= end) {
            return true;
        }

        int curElement = data[end];
        int splitIndex;

        for (splitIndex = start; splitIndex < end && data[splitIndex] < curElement; splitIndex++) ;

        for (int i = splitIndex; i < end; i++) {
            if (data[i] < curElement) {
                return false;
            }
        }
        return isBTS(data, start, splitIndex - 1) && isBTS(data, splitIndex, end - 1);
    }


    public static boolean myVerifySquenceOfBST(int[] sequence) {
        if (null == sequence) {
            return false;
        }
        return myIsBTS(sequence, 0, sequence.length - 1);
    }

    public static boolean myIsBTS(int[] data, int start, int end) {
        // 递归结束的条件
        if (start >= end) {
            return true;
        }

        // 拿到根结点
        int root = data[end];
        int splitIndex = 0;

        // 每次找到splitIndex的位置, 大于当前的节点
        for (splitIndex = start; splitIndex < end && data[splitIndex] < root; splitIndex++) ;

        // 验证splitIndex到end - 1的位置元素是否都大于root
        for (int i = splitIndex; i < end; i++) {
            // 右边的节点都大于root
            if (data[i] < root) {
                return false;
            }
        }
        return myIsBTS(data, start, splitIndex - 1) && myIsBTS(data, splitIndex, end - 1);
    }

    // 看到高手写的，反思：要注意利用栈（入栈出栈这种）, 多思考
    // 思路是二叉树的后序遍历是中序遍历的入栈顺序的一个弹出操作，即先得到这个二叉搜索树的中序遍历，压栈，check该序列是否是这个中序遍历的一个弹出序列
    private static boolean myVerifySquenceOfBSTWithStack(int[] sequence) {
        if (null == sequence) {
            return false;
        }
        int[] middleSequence = sequence.clone();

        // 先得到中序遍历，二叉搜索树的出入序列直接排序就行了
        Arrays.sort(middleSequence);

        return judgePopOrder(middleSequence, sequence);
    }

    private static boolean judgePopOrder(int[] middleSequence, int[] sequence) {
        Stack<Integer> helpStack = new Stack<>();

        // sequence的位置
        int k = 0;
        for (int i = 0; i < middleSequence.length; i++) {
            // 判断栈顶元素和k位置是否相等，不等继续入栈
            // 入栈一个
            helpStack.push(middleSequence[i]);

            while (!helpStack.isEmpty() && helpStack.peek() == sequence[k]) {
                helpStack.pop();
                k++;
            }
        }
        return helpStack.isEmpty();
    }

    // --------------------------------------------------------------------------------------------------------- //

    static ArrayList<ArrayList<Integer>> findPathReList = new ArrayList<>();
    static ArrayList<Integer> finderPathMan = new ArrayList<>();

    /**
     * <p>  输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径</p>
     * <p>  路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径</p>
     * <p>  (注意: 在返回值的list中，数组长度大的数组靠前)</p>
     *
     * @param root
     * @param target
     * @return
     */
    private static ArrayList<ArrayList<Integer>> findPath(TreeNode root, int target) {
        // 可以采用动态规划，或者递归来做. 思路是先减去root节点的值，往下寻找
        if (null == root) {
            return new ArrayList<>();
        }

        // 先用递归来做
        finderPathMan.add(root.val);
        target = target - root.val;

        if (target == 0 && BinaryTreeTest.isLeafNode(root)) {
            findPathReList.add(new ArrayList<>(finderPathMan));
        }

        findPath(root.left, target);
        findPath(root.right, target);

        // 没找到，回退一个节点
        finderPathMan.remove(finderPathMan.size() - 1);

        return findPathReList;
    }


    /**
     * 非递归版本， 这个会有问题，不能直接用
     *
     * @param root
     * @param target
     * @return
     */
    private static ArrayList<ArrayList<Integer>> findPathB(TreeNode root, int target) {
        Stack<TreeNode> helpFindPathBStack = new Stack<>();

        // 借助栈来保存当前节点，如果当前节点
        helpFindPathBStack.push(root);
        target = target - root.val;

        ArrayList<ArrayList<Integer>> re = new ArrayList<>();

        TreeNode currentNode = helpFindPathBStack.peek();

        while (!helpFindPathBStack.isEmpty()) {
            ArrayList<Integer> selectedList = new ArrayList<>();

            // 找到符合条件的叶子结点
            if (BinaryTreeTest.isLeafNode(currentNode) && target == 0) {
                // root -> current
                helpFindPathBStack.pop();

                re.add(selectedList);

                currentNode = helpFindPathBStack.peek().right;
            }

            if (null != currentNode.left) {
                helpFindPathBStack.push(currentNode.left);
            }

            if (null != currentNode.right) {
                helpFindPathBStack.push(currentNode.left);
            }
        }

        return re;
    }

    private static int findPathEle(TreeNode root, int target) {
        if (BinaryTreeTest.isLeafNode(root) && target == root.val) {
            return root.val;
        }
        if (null != root.left) {
            root = root.left;
        }

        if (null != root.right) {
            root = root.right;
        }

        return findPathEle(root, target - root.val);
    }

    private static boolean isLeafNode(TreeNode treeNode) {
        if (null == treeNode) {
            return false;
        }

        return null == treeNode.left && null == treeNode.right;
    }


    // --------------------------------------------------------------------------------------------------------- //

    /**
     * <p>输入一棵二叉树，求该树的深度。从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度</p>
     *
     * @param root
     * @return
     */
    public static int treeDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int left = treeDepth(root.left);
        int right = treeDepth(root.right);

        return Math.max(left, right) + 1;
    }

    public static int treeDepthC(TreeNode pRoot) {
        if (pRoot == null) {
            return 0;
        }
        ArrayList<TreeNode> helpList = new ArrayList<>();
        helpList.add(pRoot);

        int depth = 0;
        int currentLevelCount = 0;
        int nextListSize = 1;

        while (!helpList.isEmpty()) {
            TreeNode temp = helpList.get(0);
            helpList.remove(0);

            currentLevelCount++;
            if (temp.left != null) {
                helpList.add(temp.left);
            }

            if (temp.right != null) {
                helpList.add(temp.right);
            }

            if (currentLevelCount == nextListSize) {  // 判断当前层已经出去了几个元素
                currentLevelCount = 0;
                nextListSize = helpList.size();  // 记录当前层的数量，准备使用
                depth++;
            }
        }

        return depth;
    }

    // --------------------------------------------------------------------------------------------------------- //

    public static void main(String[] args) {
//        TreeNode root = new TreeNode(1);
//
//        TreeNode treeNode4 = new TreeNode(4);
//        TreeNode treeNode5 = new TreeNode(5);
//
//        TreeNode treeNode6 = new TreeNode(6);
//        TreeNode treeNode7 = new TreeNode(7);
//        TreeNode treeNode8 = new TreeNode(8);
//
//        TreeNode treeNode2 = new TreeNode(2);
//        TreeNode treeNode3 = new TreeNode(3);
//
//        treeNode2.left = treeNode4;
//        treeNode2.right = treeNode5;
//
//        treeNode3.right = treeNode6;
//
//        treeNode6.right = treeNode7;
//
//        treeNode7.right = treeNode8;
//
//        root.left = treeNode2;
//        root.right = treeNode3;

        TreeNode root = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);

        treeNode2.left = treeNode4;
        treeNode4.left = treeNode5;

        treeNode3.right = treeNode6;
        treeNode6.right = treeNode7;

        root.left = treeNode2;
        root.right = treeNode3;

        System.out.println(isBalanced_Solution(root));
    }

    /**
     * <p>输入一棵二叉树，判断该二叉树是否是平衡二叉树</p>
     *
     * @param root
     * @return
     */
    public static boolean isBalanced_Solution(TreeNode root) {
        // 这里是最普通的递归，要先判断整个树的左边和右边的高度差
        // 之后再递归判断整个树的左侧节点是否符合
        // 然后再判断整个树的右侧节点是否符合
        // 这里问题最大的就是整个树上的节点会被多遍历很多次
        if (null == root) {
            return true;
        }
        return Math.abs(leftNodeRightNodeLevelJudge(root.left) - leftNodeRightNodeLevelJudge(root.right)) <= 1
                && isBalanced_Solution(root.left)
                && isBalanced_Solution(root.right);
    }

    public static int leftNodeRightNodeLevelJudge(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int left = leftNodeRightNodeLevelJudge(root.left);
        int right = leftNodeRightNodeLevelJudge(root.right);

        // 优化开始，下面是用了剪枝，把子节点的平衡枝剪掉，这个优化是有问题的，倒V型到树剪枝完毕会出现两边高度和原树不一致的情况
//        if (left != 0 && left == right) {
//            root.left = root.right = null;
//        }
//        if (right > left && (right - left <= 1)) {
//            root.right = null;
//        }
//        if (left > right && (left - right <= 1)) {
//            root.left = null;
//        }
        // 优化结束

        return Math.max(left, right) + 1;
    }

    /**
     * @param root
     * @return
     */
    public static boolean myIsBalanced_Solution(TreeNode root) {
        // 这个方法是先判断最左侧的叶子结点的父节点是否平衡，然后左子树遍历完毕后移动到右子树

        return myGetTreeDeep(root) != -1;
    }

    public static int myGetTreeDeep(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = myGetTreeDeep(root.left);
        if (left == -1) {
            return -1;
        }

        int right = myGetTreeDeep(root.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(left - right) > 1 ? -1 : Math.max(left, right) + 1; // 树的深度
    }

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }
}
