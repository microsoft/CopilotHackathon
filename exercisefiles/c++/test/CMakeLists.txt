set(target run-tests)

add_executable(
  ${target} 
  temperature_test.cpp
)

include(FetchContent)
FetchContent_Declare(
  googletest
  URL https://github.com/google/googletest/archive/03597a01ee50ed33e9dfd640b249b4be3799d395.zip
)
# For Windows: Prevent overriding the parent project's compiler/linker settings
set(gtest_force_shared_crt ON CACHE BOOL "" FORCE)
FetchContent_MakeAvailable(googletest)

target_link_libraries(${target} GTest::gtest_main)

target_include_directories(
  ${target}
  PUBLIC "${CMAKE_SOURCE_DIR}/src/converters"
)

target_link_libraries(${target} converters)

add_test(NAME ${target} COMMAND ${target})

include(GoogleTest)
gtest_discover_tests(${target})

set_target_properties(
    ${target}
    PROPERTIES
    RUNTIME_OUTPUT_DIRECTORY
    "${CMAKE_BINARY_DIR}"
)
