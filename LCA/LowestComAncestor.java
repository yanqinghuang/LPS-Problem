package com.icat;
import com.icat.BinaryTree.TreeNode;
/**
 * Created by yanqing on 2016/4/1.
 */


public class LowestComAncestor {
    private TreeNode root;
    private TreeNode u;
    private TreeNode v;
    private TreeNode com;
    //Constructor
    public LowestComAncestor(TreeNode root, TreeNode u, TreeNode v){
        this.root = root;
        this.u = u;
        this.v = v;
    }
    public void getLCA(TreeNode node){
        if(node==null) {
            return;
        }
        //if u(v) is in node's left subtree and v(u) is in node's right subtree, then we find the common ancestor.
        else if((inSubtree(u, node.left)&&inSubtree(v, node.right)) || (inSubtree(v, node.left)&&inSubtree(u, node.right))){
            com = node;
        }
        else{    //recursively get
            getLCA(node.left);
            getLCA(node.right);
        }
        return;
    }
    //Wether p is in the subTree of node
    public boolean inSubtree(TreeNode p, TreeNode node){
        if(node==null) return false;
        else if(p==node) return true;
        else{
            return (inSubtree(p, node.left) || inSubtree(p, node.right));
        }
    }
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();
        tree.buildBinaryTree();
        TreeNode root = tree.root;
        TreeNode u = null;
        TreeNode v = null;
        try{
            u = root.left.left;
            v = root.left.left;
            System.out.println("u node: " + u.val);
            System.out.println("v node: " + v.val);
        }catch (NullPointerException e){
            System.out.println("Please enter valid u, v node.");
            return;
        }

        TreeNode res = null;
        LowestComAncestor lca = new LowestComAncestor(root, u, v);
        if (root==null) {
            System.out.println("There's no common ancestor!");
            return;
        }
        if (lca.inSubtree(u,v)) res = v;     // v is the ancestor of u
        else if (lca.inSubtree(v,u)) res = u;  // u is the ancestor of v
        else {
            lca.getLCA(root);
            res = lca.com;
        }
        System.out.println("This Lowest Common Ancestor is " + res.val);
        return;

    }
}
