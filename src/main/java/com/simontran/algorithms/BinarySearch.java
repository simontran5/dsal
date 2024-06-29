package com.simontran.algorithms;

public class BinarySearch {
    public static int iterativeBinarySearch(int[] array, int target) {
        int low = 0;
        int high = array.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    public static int recursiveBinarySearch(int[] array, int low, int high, int target) {
        if (low <= high) {
            int mid = low + (high - low) / 2;

            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                return recursiveBinarySearch(array, mid + 1, high, target);
            } else {
                return recursiveBinarySearch(array, low, mid - 1, target);
            }
        }

        return -1;
    }
}