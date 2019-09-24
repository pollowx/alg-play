package com.hbx.play.zcytest.binarytree;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @Auther: bingxin
 * @Date: 2019-09-18 13:20
 * @Description:
 */
public class BinaryTreeAlg {

//    public static void main(String[] args) {
//        Node root = new Node(1);
//
//        Node Node4 = new Node(4);
//        Node Node5 = new Node(5);
//
//        Node Node6 = new Node(6);
//
//        Node Node2 = new Node(2);
//        Node Node3 = new Node(3);
//
//        Node2.left = Node4;
//        Node2.right = Node5;
//
//        Node3.right = Node6;
//
//        root.left = Node2;
//        root.right = Node3;
//
//        //inOrderByNormal(root);
//
////        morrisIn(root);
////        System.out.println(" ");
////        inOrderByNormal(root);
//
//        printBinaryTreeByLevel(root);
//    }

    /**
     * 递归前序遍历
     * @param head
     */
    public static void preOrder(Node head) {
        if (null == head) {
            return;
        }
        System.out.println(head.value + "\t");
        preOrder(head.left);
        preOrder(head.right);
    }

    /**
     * 递归中序遍历
     * @param head
     */
    public static void inOrder(Node head) {
        if (null == head) {
            return;
        }
        preOrder(head.left);
        System.out.println(head.value + "\t");
        preOrder(head.right);
    }

    /**
     * 递归后序遍历
     * @param head
     */
    public static void posOrder(Node head) {
        if (null == head) {
            return;
        }
        preOrder(head.left);
        preOrder(head.right);
        System.out.println(head.value + "\t");
    }

    /**
     * 前序普通方式遍历
     * @param head
     */
    public static void preOrderByNormal(Node head) {
        if (null == head) {
            return;
        }
        Stack<Node> helpStack = new Stack<>();
        helpStack.push(head);

        while (!helpStack.isEmpty()) {
            Node temp = helpStack.pop();
            System.out.println(temp.value);

            if (null != temp.left) {
                helpStack.push(temp.left);
            }
            if (null != temp.right) {
                helpStack.push(temp.right);
            }
        }
    }

    /**
     * 中序遍历二叉树
     * @param head
     */
    public static void inOrderByNormal(Node head){
        if (null == head) {
            return;
        }
        Stack<Node> helpStack = new Stack<>();
        while (!helpStack.isEmpty() || null != head) {
            if (null != head) {
                helpStack.push(head);
                head = head.left;
            } else {
                Node temp = helpStack.pop(); // 第一次是最左子节点
                System.out.print(temp.value + "\t");
                head = temp.right; // 最左的子节点有没有右子树， 没有的话打印上一个根节点
            }
        }
    }

    /**
     * 后续遍历，用两个栈
     * @param head
     */
    public static void posOrderByNormalTwoStack(Node head){
        if (null == head) {
            return;
        }
        Stack<Node> helpStack1 = new Stack<>();
        Stack<Node> helpStack2 = new Stack<>();

        helpStack1.push(head);
        while (!helpStack1.isEmpty()) {
            Node temp = helpStack1.pop();
            helpStack2.push(temp);
            if (null != temp.left) {
                helpStack2.push(temp.left);
            }
            if (null != temp.right) {
                helpStack2.push(temp.right);
            }
        }

        while (!helpStack2.isEmpty()) {
            System.out.print(helpStack2.pop().value + "\t");
        }
    }

    /**
     * BFS遍历二叉树，广度优先遍历
     * @param root
     */
    private void bfsBinaryTree(Node root) {
        if (null == root) {
            return;
        }
        LinkedList<Node> helpList = new LinkedList<Node>();
        helpList.add(root);

        while(!helpList.isEmpty()) {
            Node temp = helpList.poll();
            System.out.print(temp.value + "\t");
            if (null != temp.left) {
                helpList.add(temp.left);
            }

            if (null != temp.right) {
                helpList.add(temp.right);
            }
        }
    }

    /**
     * DFS遍历二叉树，深度优先遍历
     * @param root
     */
    private void dfsBinaryTree(Node root) {
        if (null == root) {
            return;
        }
        Stack<Node> helpStack = new Stack<>();
        helpStack.push(root);

        while (!helpStack.isEmpty()){
            Node temp = helpStack.pop();

            System.out.print(temp.value + "\t");

            if (null != temp.right) {
                helpStack.push(temp.right);
            }

            if (null != temp.left) {
                helpStack.push(temp.left);
            }
        }
    }

    /**
     * morris序列
     * @param head
     */
    public static void morris(Node head) {
        if (null == head) {
            return;
        }
        Node cur = head;
        Node mostRight = null;
        while (null != cur) {
            mostRight = cur.left; // 当前节点的左子树
            if (null != mostRight) {
                while (null != mostRight.right && mostRight.right != cur) {
                    mostRight = mostRight.right; // 找到最右侧的
                }
            }
            // 从while循环出来mostRight已经指向了最右侧的节点
            if (mostRight.right == null) { // 这是右侧节点的判断，如果是null 代表可能没有，也可能是第一次到达该节点
                // 赋值
                mostRight.right = cur;
                cur = cur.left; // 往左移动
                continue;
            } else {
                mostRight.right = null; // 第二次
            }

            // 向右移动
            cur = cur.right;
        }
    }

    /**
     * morris的先序遍历
     * 对于只遍历一次的节点，遍历到的时候直接打印
     * 对于遍历两次的节点，只在第一次遍历的时候打印就好
     * @param head
     */
    public static void morrisPre(Node head) {
        if (null == head) {
            return;
        }
        Node cur = head;
        Node mostRight = null;

        while (cur != null) {
            mostRight = cur.left;
            if (mostRight == null) { // 没有左子树，直接打印跟节点
                System.out.print(cur.value);
            } else {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
                if (mostRight.right == null) { // 第一次遍历到
                    System.out.print(cur.value + "\t");

                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                } else { // 第二次遍历到
                    mostRight.right = null;
                }
            }
            cur = cur.right;
        }
    }

    /**
     * morris的中序遍历
     * 对于只遍历一次的节点，遍历到的时候直接打印
     * 对于遍历两次的节点，在第二次遍历的时候打印
     * @param head
     */
    public static void morrisIn(Node head) {
        if (null == head) {
            return;
        }
        Node cur = head;
        Node mostRight = null;

        while(null != cur) {
            mostRight = cur.left;
            if (null == mostRight) {
                // 没有左子树
                System.out.print(cur.value + "\t");
            } else {
                while (mostRight.right != null && mostRight.right != cur) { // find mostRight
                    mostRight = mostRight.right;
                }
                if (mostRight.right != null) { // 第二次遍历到
                    System.out.print(mostRight.right.value + "\t");
                    mostRight.right = null;
                } else {
                    mostRight.right = cur;
                    cur = cur.left;
                    continue;
                }
            }
            cur = cur.right;
        }
    }

    /**
     * 按层打印二叉树
     * @param head
     */
    public static void printBinaryTreeByLevel(Node head) {
        if (null == head) {
            return;
        }
        LinkedList<Node> helpList = new LinkedList<>();
        helpList.add(head);

        int currentCount = 0;
        int nextSize = 1;
        while(!helpList.isEmpty()) {
            Node temp = helpList.poll();

            System.out.print(temp.value + "\t");

            currentCount++;
            if (temp.left != null) {
                helpList.add(temp.left);
            }

            if (temp.right != null) {
                helpList.add(temp.right);
            }

            if (currentCount == nextSize) { // 一层遍历完毕
                nextSize = helpList.size();
                currentCount = 0;

                System.out.println(" ");
            }
        }
    }

//    public static void main(String[] args) {
//        Node root = new Node(5);
//
//        Node node4 = new Node(4);
//        Node node1 = new Node(1);
//
//
//        Node node3 = new Node(3);
//        Node node6 = new Node(6);
//
//        Node node7 = new Node(7);
//
//        node3.left = node4;
//        node3.right = node1;
//
//        node6.right = node7;
//
//        root.left = node3;
//        root.right = node6;
//
//        Node root2 = new Node(3);
//
//        Node root2Node4 = new Node(4);
//        Node root2Node1 = new Node(1);
//
//        root2.left = root2Node4;
//        root2.right = root2Node1;
//
//        System.out.println(judgeATreeContainsBTree(root, root2));
//        System.out.println(judgeATreeContainsBTreeByRecursive(root, root2));
//
//        System.out.println(judgeATreeContainsAllBTree(root, root2));
//
//        //getTwoErrorNodes(root);
//    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 搜索二叉树里面有两个节点放错了左右顺序，请找出这两个节点
     * @param head
     * @return
     */
    public static Node[] getTwoErrorNodes(Node head) {
        if (null == head) {
            return null;
        }
        // 思路是：如果有两个节点顺序有问题，那么中序遍历就会有逆序产生，正常的中序遍历是升序，如果两个节点错了顺序那么就会有逆序产生
        Node[] res = new Node[2];

        // 中序遍历
        Stack<Node> helpStack = new Stack<>();
        Node cur = head;

        Node tempPre = null;

        while (!helpStack.isEmpty() || cur != null) {
            if (cur != null) {
                helpStack.push(cur);
                cur = cur.left;
            } else {
                Node temp = helpStack.pop();
                if (tempPre != null &&
                        temp.value < tempPre.value){
                    if (res[0] == null) {
                        res[0] = tempPre;  // 找到第一个
                    } else {
                        res[1] = temp;
                    }
                }
                tempPre = temp;

                cur = temp.right;
            }
        }
        return res;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 判断A树是否包含B树的子结构
     * @param t1
     * @param t2
     * @return
     */
    public static boolean judgeATreeContainsBTree(Node t1, Node t2) {
        if (t1 == null || t2 == null) {
            return false;
        }
        // 思路是在T1的每个节点上来找value值是t2的节点，如果没有就直接false了

        Stack<Node> helpStack = new Stack<>();
        Node cur = t1;
        while (!helpStack.isEmpty() || null != cur) {
            if (null != cur) {
                helpStack.push(cur);
                cur = cur.left;
            } else {
                Node temp = helpStack.pop();
                if (temp.value == t2.value) { // 寻找t1的子节点
                    return aTreeEqualbTree(temp, t2);
                }
                cur = temp.right;
            }
        }
        return false;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 判断A树是否包含B树的子结构，递归
     * @param t1
     * @param t2
     * @return
     */
    public static boolean judgeATreeContainsBTreeByRecursive(Node t1, Node t2) {
        if (t2 == null) {
            return true;
        }
        if (t1 == null) {
            return false;
        }

        return aTreeEqualbTree(t1, t2) ||
                judgeATreeContainsBTreeByRecursive(t1.left, t2) ||
                judgeATreeContainsBTreeByRecursive(t1.right, t2);
    }

    /**
     * 看t2是不是完全等于t1
     * @param t1
     * @param t2
     * @return
     */
    public static boolean aTreeEqualbTree(Node t1, Node t2) {
        // 递归的跳出条件
        if (null == t2) {
            return true;
        }
        if (t1 == null || t1.value != t2.value) {
            return false;
        }
        return aTreeEqualbTree(t1.left, t2.left) && aTreeEqualbTree(t1.right, t2.right);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 判断A树是否完全包含B树的结构
     * @param t1
     * @param t2
     * @return
     */
    public static boolean judgeATreeContainsAllBTree(Node t1, Node t2) {
        if (t1 == null || t2 == null) {
            return false;
        }
        Stack<Node> helpStack = new Stack<>();
        Node cur = t1;

        while(!helpStack.isEmpty() || null != cur) {
            if (cur != null) {
                helpStack.push(cur);
                cur = cur.left;
            } else {
                Node temp = helpStack.pop();
                if (temp.value == t2.value) {
                    return checkTreeEqual(temp, t2);
                }
                cur = temp.right;
            }
        }
        return false;
    }

    /**
     * 判断两个树完全相等
     * @param t1
     * @param t2
     * @return
     */
    public static boolean checkTreeEqual(Node t1, Node t2) {
        if (t2 == null) {
            return true;
        }
        if (t1 == null || t1.value != t2.value) {
            return false;
        }
        return checkTreeEqual(t1.left, t2.left) &&
                checkTreeEqual(t2.left, t1.left) &&
                checkTreeEqual(t1.right, t2.right) &&
                checkTreeEqual(t2.right, t1.right);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 判断B树是否完全属于A树的一部分，KMP算法，时间复杂度O(N)
     * @param t1
     * @param t2
     * @return
     */
    public static boolean judgeATreeContainsBTreeByKMP(Node t1, Node t2) {
        if (t1 == null || t2 == null) {
            return false;
        }

        // 序列化A树和B树
        String str1 = serialPreTreeToString(t1);

        String str2 = serialPreTreeToString(t2);

        return getIndexOf(str1, str2) != -1;
    }

    /**
     * 前序遍历序列化二叉树
     * @param head
     * @return
     */
    public static String serialPreTreeToString(Node head) {
        if (null == head) {
            return "#!";
        }

        String res = head.value + "!";
        res += serialPreTreeToString(head.left);
        res += serialPreTreeToString(head.right);
        return res;
    }

//    public static void main(String[] args) {
//
//        getIndexOf("BBC ABCDAB ABCDABCDABDE", "ABCDABD");
//
//    }

    /**
     * str里是否含有match
     * @param str
     * @param match
     * @return
     */
    public static int getIndexOf(String str, String match){
        if (null == str || null == match || match.length() < 1 || str.length() < match.length()) {
            return -1;
        }
        char[] strChars = str.toCharArray();
        char[] matchChars = match.toCharArray();
        int si = 0; // 指向strChars
        int mi = 0; // 指向matchChars

        int[] next = getNextArray(matchChars); // matchChars数组的next匹配，也就是match没匹配到的时候，str的下一个要跳转的index条件

        while (si < strChars.length && mi < matchChars.length) {
            if (strChars[si] == matchChars[mi]) { // 相等就继续下一个
                si++;
                mi++;
            } else if (next[mi] == -1) { // -1代表是next数组的第一个位置，当前的mi -> next为-1，就str[]下一个
                si++;
            } else {
                mi = next[mi]; // mi的指针往后跳next[mi]个, 而且是可能往回跳的
            }
        }
        return mi == matchChars.length ?
                si - mi // 在si开始0 -> mi位置上
                : -1; // 没找到
    }

//    public static void main(String[] args) {
//        //String str = "ABCDABD";
//        String str = "ACDA";
//
//        getNextArray(str.toCharArray());
//    }

    /**
     * 找matchChars的前缀和后缀匹配的next数组
     * @param matchChars
     * @return
     */
    public static int[] getNextArray(char[] matchChars) {
        if (null == matchChars) {
            return null;
        }
        if (matchChars.length == 1) {
            return new int[] {-1};
        }

        int[] next = new int[matchChars.length];
        next[0] = -1; // next 0 就是-1
        next[1] = 0; // next 1 是0, 空串

        int pos = 2; // 从下标2开始
        int cn = 0; //

        while (pos < matchChars.length) {
            if (matchChars[pos - 1] == matchChars[cn]) {  // 找到了匹配, 从下标1开始和下标0比
                next[pos++] = ++cn;
            } else if (cn > 0) { // 找到了至少一个匹配
                cn = next[cn]; // cn赋值发生变化
            } else {
                next[pos++] = 0; // 没找到
            }
        }
        return next;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 判断树是否是平衡树二叉树
     * @param head
     * @return
     */
    public static boolean isBalanceTree(Node head) {
        return processIsBalanceTree(head).isBalance;
    }

    /**
     * 递归判断是否是平衡树二叉树
     * @param head
     * @return
     */
    public static ReturnType processIsBalanceTree(Node head) {
        if (null == head) {
            return new ReturnType(true, 0); // 递归跳出的条件
        }

        ReturnType leftReturn = processIsBalanceTree(head.left);
        if (!leftReturn.isBalance) {
            return leftReturn;
        }

        ReturnType rightReturn = processIsBalanceTree(head.right);
        if (!rightReturn.isBalance) {
            return rightReturn;
        }
        int treeHight = Math.max(leftReturn.height, rightReturn.height) + 1;

        int hightJu = Math.abs(leftReturn.height - rightReturn.height);

        return new ReturnType(hightJu <= 1, treeHight);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static void main(String[] args) {
        int[] arr = {2, 1, 3, 6, 5, 7, 4};

        isPostArray(arr);

    }

    /**
     * check一个数组是否是搜索二叉树的后续遍历
     * @param arr
     * @return
     */
    public static boolean isPostArray(int[] arr) {
        if (null == arr || arr.length  == 0) {
            return false;
        }
        return isPost(arr, 0, arr.length - 1);
    }

    /**
     * 递归判断是否是后续遍历序列
     * @param arr
     * @param start
     * @param end
     * @return
     */
    public static boolean isPost(int[] arr, int start, int end) {
        // 首先是递归跳出的条件
        if (start == end) {
            return true;
        }
        int middlePre = -1;
        int middleNext = end;
        for (int i = start; i < end; i++) {
            if (arr[end] > arr[i]) {
                middlePre = i;
            } else {
                middleNext = middleNext == end ? i : middleNext;
            }
        }

        if (middlePre == -1 || middleNext == end) {
            return isPost(arr, start, end - 1);
        }

        if (middlePre != middleNext - 1) { // 不符合条件, 他俩相距甚远，大于1
            return false;
        }

        return isPost(arr, start, middlePre) && isPost(arr, middleNext, end - 1);
    }

    /**
     * 重建二叉树
     * @param arr
     * @return
     */
    public static Node rebuildNodeTreeByPostArray(int[] arr) {
        if (null == arr || arr.length == 0) {
            return null;
        }
        return buildNodeTree(arr, 0, arr.length - 1);
    }

    public static Node buildNodeTree(int arr[], int start, int end) {
        if (start > end) {
            return null;
        }

        int middlePre = -1;
        int middleNext = end;

        for (int i = start; i < end; i++) {
            if (arr[end] > arr[i]) {
                middlePre = i;
            } else {
                middleNext = middleNext == end ? i : middleNext;
            }
        }
        Node root = new Node(arr[arr.length - 1]);

        root.left = buildNodeTree(arr, start, middlePre);
        root.right = buildNodeTree(arr, middleNext, end - 1);

        return root;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 判断二叉树是否是搜索二叉树
     * @param head
     * @return
     */
    public static boolean isBTS(Node head) {
        if (null == head) {
            return true;
        }
        boolean res = true;
        Node pre = null;

        Node cur = head;
        Node mostRight = null;

        while (cur != null) {
            mostRight = cur.left;

            if (mostRight != null) {
                while (mostRight.right != null && mostRight.right != cur) {
                    mostRight = mostRight.right;
                }
            }

            if (mostRight.right == null) {
                mostRight.right = cur;
                cur = cur.left;
                continue;
            } else {
                mostRight.right = null;
            }

            if (null != pre && pre.value > cur.value) { // 到这里的时候pre实际上是左子树，所以和cur当前的节点比较下
                res = false;
            }

            pre = cur;
            cur = cur.right;
        }
        return res;
    }

    public static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     * 是否是平衡二叉树
     */
    public static class ReturnType{
        boolean isBalance;
        int height;

        public ReturnType(boolean isBalance, int height) {
            this.isBalance = isBalance;
            this.height = height;
        }
    }

}
