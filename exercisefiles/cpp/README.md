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

### 1. Create testing material to a File

Using Copilot, generate a file that contains 50k random integer values. Try to ask help how to generate that from
command line. You can also try to do the same just with C++ code and run that program. Write the values to file `data/integers.txt`

The folder should be in the same folder where you are running the application.

### 2. Reading Integers from a File

The integers are read from a file named `integers.txt` located in the `data` directory. Each line in the file contains a single integer.

### 3. Sorting Algorithms

Each sorting algorithm is implemented in its respective source file under the `src` directory. The function signatures are declared in the `sorting_algorithms.h` header file located in the `include` directory.

### 4. Running the Sorting Algorithms - Create a main application

The `main.cpp` file demonstrates how to use each sorting algorithm to sort the integers read from the file. The sorted integers are then printed to the console, but only the 10 first values, then three dots, and the 10 last values. Test cases will check that the whole list
is in order.

- Read values from file
- Order the same values with all algorithms
- Take time how much running each algorithm runs
- Print the running times and the order of the algoritms, which is the most efficient

### 5. Test Cases

Test cases are written in the `test_sorting_algorithms.cpp` file located in the `test` directory. These tests ensure that each sorting algorithm correctly sorts the integers.

Try command **/tests** and try without and see the difference.

### 6. Building and Running the Project

Create a `CMakeLists.txt` file with help of Copilot. To build the project, follow these steps:

```sh
mkdir build
cd build
cmake ..
make
```
With 50k values in the integers.txt running the algorithms takes less than 10s per algorithm (with quite efficient developer laptops). If you
want to see bigger differences in the running times, add more values to the file.

Run the project:

```sh
./main
```

### 7. Running the Tests

After building the project, run the tests using the following command:

```sh
./test_sorting_algorithms
```

### 8. Other Copilot features to test

- Run /help in the chat
- Paint one algorithm, press cmd+i / ctrl+i and write "/explain this code" to the inline chat
- Ask Copilot to add more comments to the code files 
- Make documentation for the application (README file)

### 9. More to add

- Add also heapsort