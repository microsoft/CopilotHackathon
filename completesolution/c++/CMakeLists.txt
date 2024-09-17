cmake_minimum_required(VERSION 3.14)

project(OctoConverter
        VERSION 1.0.0
        LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 20)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

enable_testing()

add_subdirectory(src)
add_subdirectory(test)
