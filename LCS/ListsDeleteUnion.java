package com.icat;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by yanqing on 2015/12/21.
 */
public class ListsDeleteUnion {
    private  List<Integer> splitStr(String str){
        String[] list = str.split(" ");
        List<Integer> res = new LinkedList<Integer>();
        for (String s:list){
            try{
                res.add(Integer.parseInt(s));
            }catch (NumberFormatException e){
                System.out.println("Please enter num with such format: '1 2 3'");
            }
        }
        return res;
    }
    public static void main(String[] args) {
        ListsDeleteUnion ldu = new ListsDeleteUnion();
        Scanner input = new Scanner(System.in);
        String src = input.nextLine();
        String delStr = input.nextLine();

        List<Integer> srcList = ldu.splitStr(src);
        List<Integer> delList = ldu.splitStr(delStr);

        srcList.removeAll(delList);
        for(int i: srcList) System.out.print(i+" ");
        return ;
    }
}
