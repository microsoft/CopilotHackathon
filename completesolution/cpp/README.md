# C++ Sorting Algorithms Exercise

## Introduction

This exercise involves implementing five different algorithms to sort integer values read from a file. Additionally, test cases are provided to ensure the correctness of each sorting algorithm.

## Algorithms Implemented

1. **Bubble Sort**
2. **Selection Sort**
3. **Insertion Sort**
4. **Merge Sort**
5. **Quick Sort**

## Instructions

### 1. Create Testing Material

Generate a file that contains 200,000 random integer values. 

```sh
mkdir -p data
for i in {1..200000}; do echo $RANDOM >> data/integers.txt; done
```

If you need a wider range of random numbers, you can use the following command to generate random numbers between 0 and 1,000,000:

```sh
mkdir -p data
for i in {1..200000}; do echo $((RANDOM % 1000000)) >> data/integers.txt; done
```

### 2. Reading Integers from a File

The integers are read from a file named `integers.txt` located in the `data` directory. Each line in the file contains a single integer.

### 3. Sorting Algorithms

Each sorting algorithm is implemented in its respective source file under the `src` directory. The function signatures are declared in the `sorting_algorithms.h` header file located in the `include` directory.

### 4. Running the Sorting Algorithms - Create a Main Application

The `main.cpp` file demonstrates how to use each sorting algorithm to sort the integers read from the file. The sorted integers are then printed to the console, but only the first 10 values, then three dots, and the last 10 values. Test cases will check that the whole list is in order.

- Read values from the file
- Sort the values with all algorithms
- Measure the time taken by each algorithm
- Print the running times and the order of the algorithms, indicating which is the most efficient

### 5. Test Cases

Test cases are written in the `test_sorting_algorithms.cpp` file located in the `test` directory. These tests ensure that each sorting algorithm correctly sorts the integers.

### 6. Building the Project

A `CMakeLists.txt` file is provided to build the project using CMake. To build the project, follow these steps:

```sh
mkdir build
cd build
cmake ..
make
```

