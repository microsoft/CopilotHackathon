add_subdirectory(converters)
add_executable(main main.cpp)
target_link_libraries(main PRIVATE converters)

set_target_properties(
    main
    PROPERTIES
    RUNTIME_OUTPUT_DIRECTORY
    "${CMAKE_BINARY_DIR}"
)
