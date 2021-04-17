package Uebungen.u01;

import java.util.Arrays;

public class u01 {

    // Aufgabe 1
    public static void findPairWithMinSumBruteForce(int[] arr)  //  a) O(n^2)
    {
        if(arr.length<2)
            return;
        // Suppose 1st two element has minimum sum
        int minimumSum=arr[0]+arr[1];
        int pair1stIndex=0;
        int pair2ndIndex=1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i+1; j < arr.length; j++) {
                int tempSum=arr[i]+arr[j];
                if(Math.abs(tempSum) < Math.abs(minimumSum))
                {
                    pair1stIndex=i;
                    pair2ndIndex=j;
                    minimumSum=tempSum;
                }
            }
        }
        System.out.println(" The pair whose sum is closest to zero : "+arr[pair1stIndex]+" "+ arr[pair2ndIndex]);
    }

    public static void findPairWithMinSum(int[] arr) {  // b) O(log(n))

        // Sort the array, you can use any sorting algorithm to sort it
        Arrays.sort(arr);
        int sum=0;
        int minimumSum = Integer.MAX_VALUE;
        int n=arr.length;
        if(n<0)
            return;
        // left and right index variables
        int l = 0, r = n-1;

        // variables to keep track of the left and right index pair for minimumSum
        int minLeft = l, minRight = n-1;

        while(l < r)
        {
            sum = arr[l] + arr[r];

            /*If abs(sum) is less than min sum, we need to update sum and pair */
            if(Math.abs(sum) < Math.abs(minimumSum))
            {
                minimumSum = sum;
                minLeft = l;
                minRight = r;
            }
            if(sum < 0)
                l++;
            else
                r--;
        }

        System.out.println(" The pair whose sum is minimun : "+arr[minLeft]+" "+ arr[minRight]);
    }

    // c)   Lösung 1: O(n)
    //      Lösung 2: O(log(n))
    //      Lösung 3: O(log(n))


    // Aufgabe 2

    // a)   Aufruf 1: l=0; r=8
    //      Aufruf 2: l=0; r=3
    //      Aufruf 3: l=2; r=3
    //      Aufruf 4: l=3; r=3
    //      -> return -1;

    public static void main(String[] args) {
        // Aufgabe 1
        int[] arr ={1,3,-5,7,8,20,-40,6};
        findPairWithMinSumBruteForce(arr);
        findPairWithMinSum(arr);

        // Aufgabe 2
        BinarySearch ob = new BinarySearch();
        int[] arr2 = { 1, 2, 4, 6, 7, 11, 14, 18, 21, 25, 28, 30, 32, 35, 37};
        int n = arr2.length;
        int x = 25;
        int result = ob.binarySearch(arr2, 0, n - 1, x);
        if (result == -1)
            System.out.println(" Element not present");
        else
            System.out.println(" Element found at index " + result);
    }

    static class BinarySearch {
        // Returns index of x if it is present in arr[l..
        // r], else return -1
        int binarySearch(int arr[], int l, int r, int x) {
            if (r >= l) {
                //int mid = l + (r - l) / 2; // orginal

                // b)
                int zaehler = x - arr[l];
                int nenner = arr[r] - arr[l];
                int mid = (int) (zaehler / (nenner / (double) (r - l))) + l;

                // If the element is present at the
                // middle itself
                if (arr[mid] == x)
                    return mid;

                // If element is smaller than mid, then
                // it can only be present in left subarray
                if (arr[mid] > x)
                    return binarySearch(arr, l, mid - 1, x);

                // Else the element can only be present
                // in right subarray
                return binarySearch(arr, mid + 1, r, x);
            }

            // We reach here when element is not present
            // in array
            return -1;
        }
    }
}
