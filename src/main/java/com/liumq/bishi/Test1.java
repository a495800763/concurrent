package com.liumq.bishi;

public class Test1 {

    public static void main(String[] args) {
        int[] arr = {10,7,2,4,7,62,3,4,2,1,8,9,19};
        quickSort(arr, 0, arr.length-1);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }

    }

    public static int getNum(int m) {
        //除以二的余数
        int left = m % 2;
        //除以二的商
        int count = m / 2;
        int result = -1 * count;
        return left == 0 ? result : (result + m);
    }


    public static void quickSort(int[] arr,int low,int high) {
        int i,j,temp,t;
        if(low>high){
            return;
        }
        i=low;
        j=high;
        //选最低位做基准位
        temp = arr[low];

        while (i<j) {
            //先看右边，依次往左递减
            while (temp<=arr[j]&&i<j) {
                j--;
            }
            //再看左边，依次往右递增
            while (temp>=arr[i]&&i<j) {
                i++;
            }
            //当前的ij 满足 arr[j]比temp小 但 arr[i]比temp大 ，应该交换
            if (i<j) {
                t = arr[j];
                arr[j] = arr[i];
                arr[i] = t;
            }

        }

        //最后效果： 比基准小的在右边 比基准大的在左边
        //但是两边具体顺序还需要再排
        //因此对两边递归

        //最后将基准为与i和j相等位置的数字交换
        arr[low] = arr[i];
        arr[i] = temp;
        //递归调用左半数组
        quickSort(arr, low, j-1);
        //递归调用右半数组
        quickSort(arr, j+1, high);
    }
}
