package com.icat;

import sun.reflect.generics.tree.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yanqing on 2016/4/1.
 */
public class BinaryTree {
    public TreeNode root;

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        //Constructor
        TreeNode(int x) {
            val = x;
            left = null;
            right = null;
        }
    }

    public BinaryTree(){
        root = null;
    }

    /**
     * 建二叉树
     */
    public void buildBinaryTree() {
        List<TreeNode> nodeList = new ArrayList<TreeNode>();
        //get input data in each tree layer
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = null;
        try {
            str = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (str.length()==0) {
            System.out.println("No valid a input array!");
            return;
        }
        String[] strArr = str.split(" ");

        for(String data : strArr){
            nodeList.add(new TreeNode(Integer.parseInt(data)));
        }
        //0-nodeList.size()/2 has two children
        int i = 0;
        while (i < nodeList.size() / 2 - 1) {
            nodeList.get(i).left = nodeList.get(2 * i + 1);
            nodeList.get(i).right = nodeList.get(2 * i + 2);
            i++;
        }
        //i = nodeList.size()/2-1, considering last parent node
        nodeList.get(i).left = nodeList.get(i * 2 + 1);
        //odd size node tree has both left and right children
        if (nodeList.size() % 2 == 1) {
            nodeList.get(i).right = nodeList.get(2 * i + 2);
        }
        root =  nodeList.get(0);
    }
    /**
     * 前序遍历
     * @param node
     */
    public void preOrder(TreeNode node){
        if(node != null){
            System.out.print(node.val + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
    /**
     * 中序遍历
     * @param node
     */
    public void inOrder(TreeNode node){
        if(node != null){
            inOrder(node.left);
            System.out.print(node.val + " ");
            inOrder(node.right);
        }
    }
    /**
     * 后序遍历
     * @param node
     */
    public void postOrder(TreeNode node){
        if(node != null){
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.val + " ");
        }
    }

    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.buildBinaryTree();
        TreeNode root = tree.root;

        System.out.println("preOrder: ");
        tree.preOrder(tree.root);
        System.out.println();
        System.out.println("inOrder: ");
        tree.inOrder(tree.root);
        System.out.println();
        System.out.println("postOrder: ");
        tree.postOrder(tree.root);

    }
}
