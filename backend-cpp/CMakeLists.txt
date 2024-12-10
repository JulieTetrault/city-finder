cmake_minimum_required(VERSION 3.21 FATAL_ERROR)

if(POLICY CMP0167)
    cmake_policy(SET CMP0167 NEW)
endif()

function(coveo_is_arm64 outputVariable)
    execute_process(COMMAND uname -m COMMAND tr -d '\n' OUTPUT_VARIABLE architecture)
    string(TOLOWER "${architecture}" architectureLower)
    set(${outputVariable} "false" PARENT_SCOPE)
    if(architectureLower MATCHES "arm64|aarch64")
        set(${outputVariable} "true" PARENT_SCOPE)
    endif()
endfunction()

coveo_is_arm64(is_arm_64)
if(is_arm_64 MATCHES "true")
    if(NOT DEFINED ENV{VCPKG_FORCE_SYSTEM_BINARIES})
        set(ENV{VCPKG_FORCE_SYSTEM_BINARIES} "1")
    endif()
endif()

if(DEFINED ENV{VCPKG_ROOT})
    set(CMAKE_TOOLCHAIN_FILE $ENV{VCPKG_ROOT}/scripts/buildsystems/vcpkg.cmake CACHE STRING "Vcpkg toolchain file")
else()
    set(helpMsg)
    set(helpMsg "This project uses 'microsoft/vcpkg'.")
    set(helpMsg "${helpMsg} Please 'git clone https://github.com/microsoft/vcpkg'.")
    set(helpMsg "${helpMsg} Once this is done, set the environment variable 'VCPKG_ROOT' to the path of the cloned repository.")
    message(FATAL_ERROR "${helpMsg}")
endif()


project(CoveoReviewChallengeBackendCPP VERSION 1.0 LANGUAGES CXX)

set(CMAKE_CXX_STANDARD 20)

add_library(BackendCppLib
    ${CMAKE_CURRENT_LIST_DIR}/include/app.h

    ${CMAKE_CURRENT_LIST_DIR}/src/CsvParser.h
    ${CMAKE_CURRENT_LIST_DIR}/src/model/City.h

    ${CMAKE_CURRENT_LIST_DIR}/src/app.cpp
)
target_include_directories(BackendCppLib PUBLIC include)
find_package(cpprestsdk CONFIG REQUIRED)
target_link_libraries(BackendCppLib PUBLIC cpprestsdk::cpprest)
if ((CMAKE_CXX_COMPILER_ID MATCHES "Clang") OR (CMAKE_CXX_COMPILER_ID MATCHES "GNU"))
    # Removes annoying warnings
    target_compile_options(BackendCppLib PRIVATE -Wno-null-dereference -Wno-tautological-undefined-compare)
endif()

add_executable(BackendCpp ${CMAKE_CURRENT_LIST_DIR}/src/main.cpp)
target_link_libraries(BackendCpp PRIVATE BackendCppLib)


# Testing
enable_testing()

add_executable(tests ${CMAKE_CURRENT_LIST_DIR}/tests/test_app.cpp)
find_package(GTest REQUIRED)
target_link_libraries(tests PRIVATE BackendCppLib GTest::gtest_main cpprestsdk::cpprest)

gtest_discover_tests(tests)

configure_file(${CMAKE_CURRENT_LIST_DIR}/../backend/src/main/resources/data/cities_canada-usa.tsv ${CMAKE_BINARY_DIR}/cities_canada-usa.tsv COPYONLY)
