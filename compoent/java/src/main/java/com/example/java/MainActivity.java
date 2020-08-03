package com.example.java;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.LinkedList;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList arrayList = new ArrayList();
        arrayList.add("aa");
        arrayList.add("bb");
        arrayList.add("cc");

        arrayList.add(0, "dd");
        final MyTextView viewById = findViewById(R.id.tv_test);
        findViewById(R.id.root).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                viewById.setText("10");
//                viewById.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        viewById.setText("3");
//                    }
//                }, 100);
//                ListNodes listNode1 = new ListNodes(1);
//                ListNodes listNode2 = new ListNodes(2);
//                ListNodes listNode3 = new ListNodes(3);
//                ListNodes listNode4 = new ListNodes(4);
//                ListNodes listNode5 = new ListNodes(5);
//
//                listNode1.next = listNode2;
//                listNode2.next = listNode3;
//                listNode3.next = listNode4;
//                listNode4.next = listNode5;
//                reverseList(listNode1);
                String length = decodeString("3[a]2[bc]");
                int thirdMax = thirdMax(new int[]{1, 2, 2});
                Log.e("tag", "length:---length= " + thirdMax);


            }
        });

        Log.e("tag", "onCreate: -------arrayList=" + arrayList.toString());

    }


//    public int lengthOfLongestSubstring(String s) {
//
//        if(s==null || s=="" || s.length()==0){
//            return 0;
//        }
//
//        Set<Character> list=new HashSet<>();
//        int size=0;
//        for(int i=0;i<s.length();i++){
//            char ch=s.charAt(i);
//            if(list.contains(ch)){
//                size= Math.max(size,list.size());
//                list.clear();
//                list.add(ch);
//            }else{
//                list.add(ch);
//            }
//        }
//        return size;
//    }

    public String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        int multi = 0;
        LinkedList<Integer> stack_multi = new LinkedList<>();
        LinkedList<String> stack_res = new LinkedList<>();
        for (Character c : s.toCharArray()) {
            if (c == '[') {
                stack_multi.addLast(multi);
                stack_res.addLast(res.toString());
                multi = 0;
                res = new StringBuilder();
            } else if (c == ']') {
                StringBuilder tmp = new StringBuilder();
                int cur_multi = stack_multi.removeLast();
                for (int i = 0; i < cur_multi; i++) tmp.append(res);
                res = new StringBuilder(stack_res.removeLast() + tmp);
            } else if (c >= '0' && c <= '9') multi = multi * 10 + Integer.parseInt(c + "");
            else res.append(c);
        }
        return res.toString();
    }

    public int thirdMax(int[] nums) {
        int max = 0;

        if (nums.length < 3) {
            for (int i = 0; i < nums.length; i++) {
                max = Math.max(max, nums[i]);
            }
            return max;
        } else {
            int secondMax = -1;
            int thirdMax = -1;
            int maxIndex = -1;
            int secondIndex = -1;
            int thirdIndex = -1;


            for (int i = 0; i < nums.length; i++) {
                if (max <= nums[i]) {
                    max = nums[i];
                    maxIndex = i;
                }
            }
            for (int i = 0; i < nums.length; i++) {
                if (maxIndex != i && secondMax <= nums[i]) {
                    secondMax = nums[i];
                    secondIndex = i;
                }
            }
            if (nums.length == 3 && nums[maxIndex] == secondMax) {
               return maxIndex;
            }
            for (int i = 0; i < nums.length; i++) {
                if (maxIndex != i && secondIndex != i && thirdMax <= nums[i]) {

                    if (nums[i] == secondMax) {
                        continue;
                    }
                    thirdMax = nums[i];
                    thirdIndex = i;
                }
            }
            if (thirdIndex != -1) {
                return nums[thirdIndex];
            } else {
                return max;
            }
        }
    }


    /**
     * 反转链表 递归方法
     *
     * @param head
     * @return
     */
    public ListNodes reverseList(ListNodes head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNodes p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }


    public class ListNodes {
        int value;
        ListNodes next;

        ListNodes(int x) {
            value = x;
        }
    }
}
