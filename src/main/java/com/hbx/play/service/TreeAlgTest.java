package com.hbx.play.service;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author danyu.bx
 * @description: TODO
 * @date 2019/8/27 3:23 PM
 */
public class TreeAlgTest {

    public static void main(String[] args) {
        /*
         *                  1
         *                 /  \
         *               2     3
         *              /  \   /\
         *             4  5   6  7
         */
        int[] arr = new int[]{
                1, 2, 3, 4, 5, 6, 7
        };
        /*
         *                  13
         *                 /  \
         *               65    5
         *              /  \    \
         *             97  25   37
         *            /    /\   /
         *           22   4 28 32
         */
//        int[] arr={13,65,5,97,25,0,37,22,0,4,28,0,0,32,0};//非完全二叉树，拿0补位，后续再在判断值是否为0，本demo没有进行
        //可参考java-二叉树广度优先实现、深度优先之前序实现(非递归) https://blog.csdn.net/liuxiao723846/article/details/42004003

        TreeNode root = getBinaryTree(arr, 0);
        getDFS(root);
        myFindDFS(root);

        getBFS(root);
        myFindBFS(root);
    }

    //获取二叉树
    private static TreeNode getBinaryTree(int[] arr, int index) {
        // TODO Auto-generated method stub
        TreeNode node = null;
        if (index < arr.length) {
            int value = arr[index];
            node = new TreeNode(value);
            node.left = getBinaryTree(arr, index * 2 + 1);
            node.right = getBinaryTree(arr, index * 2 + 2);
            return node;
        }
        return node;
    }

    //深度优先遍历
    private static void getDFS(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();

        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode temp = stack.peek();
            System.out.print(temp.value + "\t");
            stack.pop();
            //这里利用了堆的--先进后出的特性，所以右节点要在左节点前入堆，这里如果不好理解建议在Debug下，查看stack的变化内容就比较容易理解了
            if (temp.right != null) {
                stack.push(temp.right);
            }
            if (temp.left != null) {
                stack.push(temp.left);
            }
        }
        System.out.println("深度优先遍历结束");
    }

    //广度优先遍历
    private static void getBFS(TreeNode root) {
        if (root == null) {
            return;
        }
        ArrayList<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        while (queue.size() > 0) {
            TreeNode temp = queue.get(0);
            queue.remove(0);
            System.out.print(temp.value + "\t");
            //这里利用了队列的--先进先出的特性，所以左节点要在右节点前入堆，这里如果不好理解建议在Debug下，查看stack的变化内容就比较容易理解了
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
        System.out.println("广度优先遍历结束");
    }


    /**
     * 深度优先搜索
     */
    private static void myFindDFS(TreeNode root) {
        if (null == root) {
            return;
        }
        Stack<TreeNode> helpStack = new Stack<>();
        helpStack.push(root);

        while (!helpStack.empty()) {
            // 暂存栈顶元素
            TreeNode temp = helpStack.peek();

            TreeNode out = helpStack.pop();
            System.out.print(out.value + "\t");

            if (null != temp.right) {
                helpStack.push(temp.right);
            }
            if (null != temp.left) {
                helpStack.push(temp.left);
            }
        }
        System.out.println(".");
        System.out.println(".");
        System.out.println(".");
        return;
    }

    /**
     * 广度优先搜素
     *
     * @param root
     */
    private static void myFindBFS(TreeNode root) {
        if (null == root) {
            return;
        }
        ArrayList<TreeNode> helpList = new ArrayList<>();
        helpList.add(root);

        while (!helpList.isEmpty()) {
            TreeNode temp = helpList.get(0);
            System.out.print(temp.value + "\t");

            helpList.remove(0);

            if (null != temp.left) {
                helpList.add(temp.left);
            }
            if (null != temp.right) {
                helpList.add(temp.right);
            }
        }
        return;
    }


    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        if (null == root) {
            return null;
        }
        ArrayList<Integer> re = new ArrayList<>();

        ArrayList<TreeNode> helpList = new ArrayList<>();
        helpList.add(root);

        while (!helpList.isEmpty()) {
            TreeNode temp = helpList.remove(0);
            re.add(temp.value);

            if (null != temp.left) {
                helpList.add(temp.left);
            }
            if (null != temp.right) {
                helpList.add(temp.right);
            }
        }
        return re;
    }


    //二叉树结构
    static class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode(int value) {
            this.value = value;
        }
    }

}
