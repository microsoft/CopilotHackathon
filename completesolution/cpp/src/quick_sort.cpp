// FILE: src/quick_sort.cpp
// Quick sort
// Quick sort is a divide-and-conquer algorithm. It works by selecting a 'pivot' element from the array and 
// partitioning the other elements into two sub-arrays according to whether they are less than or greater 
// than the pivot. The sub-arrays are then sorted recursively. This can be done in-place, requiring small
// additional amounts of memory to perform the sorting.
//
// Quick sort works as follows:
// 1. Pick an element from the array, this element is called the 'pivot'.
// 2. Partitioning: reorder the array so that all elements with values less than the pivot come before the pivot, while all elements with values greater than the pivot come after it (equal values can go either way). After this partitioning, the pivot is in its final position. This is called the partition operation.
// 3. Recursively apply the above steps to the sub-array of elements with smaller values and separately to the sub-array of elements with greater values.
//
// Time complexity: O(n log n) - best and average case, O(n^2) - worst case
// Space complexity: O(log n)
//

#include <vector>
#include "sorting_algorithms.h"

void quickSort(std::vector<int>& arr) {
    quickSort(arr, 0, arr.size() - 1);
}

void quickSort(std::vector<int>& arr, int low, int high) {
    if (low < high) {
        int pivotIndex = partition(arr, low, high);
        quickSort(arr, low, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, high);
    }
}

int partition(std::vector<int>& arr, int low, int high) {
    int pivot = arr[high];
    int i = low - 1;
    for (int j = low; j < high; ++j) {
        if (arr[j] < pivot) {
            ++i;
            std::swap(arr[i], arr[j]);
        }
    }
    std::swap(arr[i + 1], arr[high]);
    return i + 1;
}