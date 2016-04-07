package com.icat;

import java.util.Scanner;

/**
 * Created by yanqing on 2016/4/7.
 * 最长回文子序列
 */
public class LongestPlalindromeSeq {
    private String str;

    public void getInput(){
        Scanner sc = new Scanner(System.in);
        str = sc.nextLine();
    }
//  Dynamically
    public int plalindromeDp(){
        int[][] dp = new int[str.length()][str.length()];
        //initial dp
        for(int i=0; i<str.length(); i++) dp[i][i]=1;
        //len is the length of sub-str, increasing from 1 to the str.length()
        for(int len=1; len<str.length(); len++){
            for(int j=0; j+len<str.length(); j++){ //j is the index of str
                if(str.charAt(j)==str.charAt(j+len)){
                    dp[j][j+len] = 2 + dp[j+1][j+len-1]; //overlap sub-problem
                }
                else dp[j][j+len] = Math.max(dp[j][j+len-1], dp[j+1][j+len]);
            }
        }
        return dp[0][str.length()-1];
    }
    // Recursively get the max plalindrome sub-seq
//    public int plalindromeRecur( int i, int j){
////        dp = new int[str.length()][str.length()];
//        if (i>j) return 0;
//        else if (i==j) {
//            return 1;
//        }
//        else{
//            if (str.charAt(i)==str.charAt(j)){
//                return ( 2 + plalindromeRecur(i+1, j-1));
//            }
//            else {
//                return Math.max(plalindromeRecur(i+1, j), plalindromeRecur(i, j-1));
//            }
//        }

//    }
    public static void main(String[] args) {
        LongestPlalindromeSeq lps = new LongestPlalindromeSeq();
        lps.getInput();
//        int res = lps.plalindromeRecur(0,lps.str.length()-1);
        int res = lps.plalindromeDp();
        System.out.println(res);
    }
}
