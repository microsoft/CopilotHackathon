// FILE: test/test_sorting_algorithms.cpp

#include <iostream>
#include <vector>
#include <algorithm>
#include <cassert>
#include "sorting_algorithms.h"

void testSortingAlgorithm(void (*sortFunc)(std::vector<int>&), const std::string& algorithmName) {
    std::vector<int> original = {5, 3, 8, 4, 2, 7, 1, 10, 6, 9};
    std::vector<int> expected = original;
    std::sort(expected.begin(), expected.end());

    std::vector<int> testVector = original;
    sortFunc(testVector);

    assert(testVector == expected);
    std::cout << algorithmName << " passed." << std::endl;
}

int main() {
    testSortingAlgorithm(bubbleSort, "Bubble Sort");
    testSortingAlgorithm(selectionSort, "Selection Sort");
    testSortingAlgorithm(insertionSort, "Insertion Sort");
    testSortingAlgorithm(mergeSort, "Merge Sort");
    testSortingAlgorithm(quickSort, "Quick Sort");

    std::cout << "All tests passed." << std::endl;
    return 0;
}