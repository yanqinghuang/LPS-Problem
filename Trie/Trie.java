package com.icat;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yanqing on 2016/4/2.
 * Trie ×ÖµäÊ÷/Ç°×ºÊ÷
 */
public class Trie {
    //Define trie node
    class TrieNode{
        private char nodeChar;
        private int freq; //Record the words counts who path through the node
        private TrieNode[] childNodes; //26 children TrieNodes

        //Constructor
        public TrieNode(Character ch){
            this.nodeChar = ch;
            childNodes = new TrieNode[26];
            freq = 1; //When new node means have one. default = 1
        }
    }

    private List<String> inputList = new ArrayList<String>();
    private List<String> queryList = new ArrayList<String>();
    private TrieNode Root;  //Thr root of entire trie

    public void getInput(){
        Scanner input = new Scanner(System.in);
        try {
            int inputWordsNum = Integer.parseInt(input.nextLine());
            for(int i=0; i<inputWordsNum; i++){
                inputList.add(input.nextLine());
            }
//            int queryWordsNum = input.nextInt();
            int queryWordsNum = Integer.parseInt(input.nextLine());
            for(int i=0; i<queryWordsNum; i++){
                queryList.add(input.nextLine());
            }
        }catch (InputMismatchException e){
            System.out.println("Input mismatch the format!");
        }
    }

    //Add new word to trie
    public void AddTrieNode(TrieNode root, String word){
        if(word.length()==0) return;

        int k = word.charAt(0) - 'a';
        if(root.childNodes[k]==null){
            root.childNodes[k] = new TrieNode(word.charAt(0));
        }
        else{
            root.childNodes[k].freq++;
        }
        AddTrieNode(root.childNodes[k], word.substring(1));
    }

    public void buildTrie(){
        //½¨¸ù26²æÊ÷
        TrieNode root = new TrieNode('0');
        for(String word: inputList){
            AddTrieNode(root, word);
        }
        this.Root = root;
    }
    //search each query str in trie and print the count result
    public void queryAndPrint(){
        for(String word: queryList){
            System.out.println(searchWord(Root, word));
        }
    }

    //search str in trie, return the node freq(the number of words start with str)
    public int searchWord(TrieNode root, String str){
        if(str.length()==0) {
            return root.freq;
        }
        int j = str.charAt(0) - 'a';
        if(root.childNodes[j]==null){
            return 0;
        }
        else{
           return searchWord(root.childNodes[j], str.substring(1));
        }
    }

    public static void main(String[] args) {
        Trie solution = new Trie();
        solution.getInput();
        solution.buildTrie();
        solution.queryAndPrint();
    }
}
