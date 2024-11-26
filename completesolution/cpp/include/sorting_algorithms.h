// FILE: include/sorting_algorithms.h

#ifndef SORTING_ALGORITHMS_H
#define SORTING_ALGORITHMS_H

#include <vector>

void bubbleSort(std::vector<int>& arr);
void selectionSort(std::vector<int>& arr);
void insertionSort(std::vector<int>& arr);
void mergeSort(std::vector<int>& arr);
void quickSort(std::vector<int>& arr);
void quickSort(std::vector<int>& arr, int low, int high);
int partition(std::vector<int>& arr, int low, int high);

#endif // SORTING_ALGORITHMS_H