package com.icat;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yanqing on 2016/3/25.
 * DP
 */
public class LongestComSeq {
    int[][] LCSeqLen; //LCSeqLen[i][j] stores the length of LCSeq of X[i] and Y[j]
    String str1;
    String str2;
    public void getInput(){
        Scanner input = new Scanner(System.in);
        str1 = input.nextLine();
        str2 = input.nextLine();
    }

    public void getLCSeq(String str1, String str2){
        int len1 = str1.length();
        int len2 = str2.length();
        LCSeqLen = new int[len2+1][len1+1]; // add  first 0 row and first 0 column
        if(len1==0 || len2 ==0){
            System.out.println("Please Enter Two Strings!");
            return;
        }
        // indexes of LCSeqLen array
        for(int i=1; i<len2+1; i++){
            for(int j=1; j<len1+1; j++){
                //Considering two cases
                if(str1.charAt(j-1)==str2.charAt(i-1)){
                    //xi = yi
                    LCSeqLen[i][j] = LCSeqLen[i-1][j-1] + 1;
                }
                else{  //xi != yi, pick the bigger of Ci-1,j  Ci,j-1
                    LCSeqLen[i][j] = Math.max(LCSeqLen[i-1][j] ,LCSeqLen[i][j-1]);
                }
            }
        }
        System.out.println();
    }
    //»ØËÝ
    public void print(){
        List<Character> res = new ArrayList<Character>();
        int j = str1.length(); // Column index of LCSeqLen arr
        int i = str2.length(); // Row index of LCSeqLen arr
        while (j>=1 && i>=1){
            if (LCSeqLen[i][j] == LCSeqLen[i-1][j]) i--; //value from up
            else if (LCSeqLen[i][j] == LCSeqLen[i][j-1]) j--; //value from left
            else{
                res.add(str1.charAt(j-1));         //value from upper left
                i--;
                j--;
            }
        }

        if(res.size()==0) {
            System.out.println("Sorry, there's no common sub-sequence between " + str1 +" and "+ str2);
            return;
        }

        System.out.print("LCSeqLen tmp arr : ");
        for(int ii=0; ii<str2.length()+1; ii++){
            System.out.println();
            for(int jj=0; jj<str1.length()+1; jj++){
                System.out.print(LCSeqLen[ii][jj] + " ");
            }
        }
        System.out.println();
        System.out.print("Longest Common Seb-sequence: ");
        //print each char of LCSeq in right order
        for(int k=res.size()-1; k>=0; k--){
            System.out.print(res.get(k));
        }
    }

    public static void main(String[] args) {
        LongestComSeq lcs = new LongestComSeq();
        lcs.getInput();
        lcs.getLCSeq(lcs.str1, lcs.str2);
        lcs.print();
    }
}
