cmake_minimum_required(VERSION 3.10)

project(SortingAlgorithms)

set(CMAKE_CXX_STANDARD 11)

include_directories(include)

add_executable(main src/main.cpp src/bubble_sort.cpp src/selection_sort.cpp src/insertion_sort.cpp src/merge_sort.cpp src/quick_sort.cpp)
add_executable(test_sorting_algorithms test/test_sorting_algorithms.cpp src/bubble_sort.cpp src/selection_sort.cpp src/insertion_sort.cpp src/merge_sort.cpp src/quick_sort.cpp)