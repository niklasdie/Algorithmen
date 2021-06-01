package Uebungen.u09;

import java.util.Arrays;

public class TwoSum {

    public static int[] getTwoSum(int[] arr, int sum) {
        int[] orginal = arr.clone();
        System.out.println(Arrays.toString(orginal));
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));
        int[] numbers = rekursivGetTwoSum(arr, sum);
        if (numbers != null) {
            System.out.println(Arrays.toString(numbers));
            int[] res = new int[2];
            for (int i = 0; i < orginal.length; i++)
                if (orginal[i] == numbers[0] || orginal[i] == numbers[1])
                    if (res[0] == 0)
                        res[0] = i;
                    else
                        res[1] = i;
            return res;
        }
        return null;
    }

    private static int[] rekursivGetTwoSum(int[] arr, int sum) {
        if (arr[arr.length / 2] >= sum)
            return rekursivGetTwoSum(Arrays.copyOfRange(arr, 0, arr.length / 2), sum);
        else
            for (int i = 0; i < arr.length; i++)
                for (int j = 0; j < arr.length; j++)
                    if (i != j && arr[i] + arr[j] == sum && arr[i] != sum && arr[j] != sum)
                        return new int[]{arr[i], arr[j]};
        return null;
    }

    public static void main(String[] args) {
        int[] arr1 = {6, 3, 7, 3, 2, 9, 7, 1, 4};
        System.out.println(Arrays.toString(getTwoSum(arr1, 5)));
    }
}
