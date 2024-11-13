#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <chrono>
#include <algorithm>
#include "sorting_algorithms.h"

int main() {
    std::ifstream inputFile("data/integers.txt");
    if (!inputFile) {
        std::cerr << "Error opening file." << std::endl;
        return 1;
    }

    std::vector<int> integers;
    std::string line;
    while (std::getline(inputFile, line)) {
        if (!line.empty()) {
            integers.push_back(std::stoi(line));
        }
    }

    inputFile.close();

    if (integers.empty()) {
        std::cerr << "The file is empty. No integers to sort." << std::endl;
        return 1;
    }

    std::vector<int> copy;
    std::vector<std::pair<std::string, double>> durations;

    // Bubble Sort
    copy = integers;
    auto start = std::chrono::high_resolution_clock::now();
    bubbleSort(copy);
    auto end = std::chrono::high_resolution_clock::now();
    std::chrono::duration<double> duration = end - start;
    durations.push_back({"Bubble Sort", duration.count()});
    std::cout << "Bubble Sort: " << duration.count() << " seconds" << std::endl;

    // Selection Sort
    copy = integers;
    start = std::chrono::high_resolution_clock::now();
    selectionSort(copy);
    end = std::chrono::high_resolution_clock::now();
    duration = end - start;
    durations.push_back({"Selection Sort", duration.count()});
    std::cout << "Selection Sort: " << duration.count() << " seconds" << std::endl;

    // Insertion Sort
    copy = integers;
    start = std::chrono::high_resolution_clock::now();
    insertionSort(copy);
    end = std::chrono::high_resolution_clock::now();
    duration = end - start;
    durations.push_back({"Insertion Sort", duration.count()});
    std::cout << "Insertion Sort: " << duration.count() << " seconds" << std::endl;

    // Merge Sort
    copy = integers;
    start = std::chrono::high_resolution_clock::now();
    mergeSort(copy);
    end = std::chrono::high_resolution_clock::now();
    duration = end - start;
    durations.push_back({"Merge Sort", duration.count()});
    std::cout << "Merge Sort: " << duration.count() << " seconds" << std::endl;

    // Quick Sort
    copy = integers;
    start = std::chrono::high_resolution_clock::now();
    quickSort(copy);
    end = std::chrono::high_resolution_clock::now();
    duration = end - start;
    durations.push_back({"Quick Sort", duration.count()});
    std::cout << "Quick Sort: " << duration.count() << " seconds" << std::endl;

    // Sort durations to find the fastest algorithm
    std::sort(durations.begin(), durations.end(), [](const std::pair<std::string, double>& a, const std::pair<std::string, double>& b) {
        return a.second < b.second;
    });

    std::cout << "\nAlgorithms sorted by execution time:" << std::endl;
    for (const auto& d : durations) {
        std::cout << d.first << ": " << d.second << " seconds" << std::endl;
    }

    return 0;
}