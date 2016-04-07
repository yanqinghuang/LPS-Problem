package com.icat;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import sun.reflect.generics.tree.Tree;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yanqing on 2016/3/22.
 * 最长回文子串
 */
public class LongestPalindromeStr{
    public int findLongestPalindrome(String s) {
        char[] ca = s.toCharArray();
        int rs = 0, re = 0;
        int max = 0;
        for(int i = 0; i < ca.length; i++) {
            if(isPalindrome(ca, i - max - 1, i)) {
                rs = i - max - 1; re = i;
                max += 2;
            } else if(isPalindrome(ca, i - max, i)) {
                rs = i - max; re = i;
                max += 1;
            }
        }
        return s.substring(rs, re + 1).length();
    }

    private boolean isPalindrome(char[] ca, int s, int e) {
        if(s < 0) return false;

        while(s < e) {
            if(ca[s++] != ca[e--]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        LongestPalindromeStr lps = new LongestPalindromeStr();
        int res = lps.findLongestPalindrome("asddsda");
        System.out.println("res: " + res);
    }
}