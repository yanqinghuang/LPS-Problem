package com.icat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yanqing on 2016/3/23.
 * Dynamic Program
 */
public class LongestComStr {
    private String str1;
    private String str2;

    public void getLCStr(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();
        int maxLen = Math.max(len1, len2);
        int[] c = new int[maxLen];      //each line
        int maxCount = 0;               // Storing the length of longest sub-strings during DP
        int[] maxIndex = new int[maxLen]; // Storing the last indexes of (multi)longest sub-strings
        int maxIndexindex=0;            // maxIndexindex+1 = maxIndex.size(), indexing for multi longest sub-strings

        for(int i=0; i<len2; i++){
            //Scanning column in reverse order, promising c[j-1] stores the value of below row, or c[j-1] is the value of left column calculated new.
            for(int j = len1 - 1; j >= 0; j--){
                if(str2.charAt(i)==str1.charAt(j)){
                    if(i==0||j==0){
                        c[j] = 1;
                    }
                    else{
                        //c[j-1] stores the value of below row
                        c[j] = c[j-1] + 1;
                    }
                    //Update the max value
                    if (c[j] > maxCount) {
                        maxCount = c[j];
                        maxIndexindex = 0;
                        maxIndex[maxIndexindex] = j; // after maxIndexindex may remain dirty nums, doesn't matter;
                    }
                    //One more sub-strings who having same maxCount
                    else if (c[j] == maxCount) {
                        maxIndex[++maxIndexindex] = j;
                    }
                }
                else c[j] = 0;
            }
        }
        int[] newMaxIndexA = Arrays.copyOf(maxIndex, maxIndexindex+1); //Delete dirty nums after maxIndexindex
        //make sure output sub-strings in right order, the same order as the original string
        Arrays.sort(newMaxIndexA);
        //print Longest Common Sub-Strings
        for(int ii=0; ii<=maxIndexindex; ii++){
            System.out.print("The " + (ii + 1) + " Longest Common Sub-string: ");
            for(int jj=newMaxIndexA[ii]-maxCount+1; jj<=newMaxIndexA[ii]; jj++){
                System.out.print(str1.charAt(jj));
            }
            System.out.println();
        }
        return;
    }

    public void getInputData(){
        BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));
        try {
           str1 = br1.readLine();
           str2 = br2.readLine();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LongestComStr lcs = new LongestComStr();
//        String str1 = new String("ffffabcxyzabcrst");
//        String str2 = new String("ffffopqrstabc");
        lcs.getInputData();
        lcs.getLCStr(lcs.str1, lcs.str2);
    }
}
