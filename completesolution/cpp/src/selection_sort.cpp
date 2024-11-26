// FILE: src/selection_sort.cpp

// Selection sort
// Selection sort is an in-place comparison sorting algorithm. It has an O(n^2) time complexity, which makes it inefficient on large lists, and generally performs worse than the similar insertion sort. Selection sort is noted for its simplicity and has performance advantages over more complicated algorithms in certain situations, particularly where auxiliary memory is limited.
//
// Selection sort works as follows:
// 1. Find the smallest element in the array and swap it with the element in the first position.
// 2. Find the second smallest element in the array and swap it with the element in the second position.
// 3. Repeat this process until the entire array is sorted.
// Time complexity: O(n^2)
// Space complexity: O(1)
//

#include <vector>
#include "sorting_algorithms.h"

void selectionSort(std::vector<int>& arr) {
    int n = arr.size();
    for (int i = 0; i < n - 1; ++i) {
        int minIndex = i;
        for (int j = i + 1; j < n; ++j) {
            if (arr[j] < arr[minIndex]) {
                minIndex = j;
            }
        }
        std::swap(arr[i], arr[minIndex]);
    }
}