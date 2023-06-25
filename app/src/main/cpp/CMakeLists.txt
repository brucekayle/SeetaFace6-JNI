cmake_minimum_required(VERSION 3.18.1)

project("face_detector")

set(CMAKE_C_FLAGS_DEBUG "${CMAKE_C_FLAGS_DEBUG} -s")
set(CMAKE_CXX_FLAGS_DEBUG "${CMAKE_CXX_FLAGS_DEBUG} -s")

if(${CMAKE_ANDROID_ARCH_ABI} STREQUAL "armeabi-v7a")
    add_compile_options(-mfloat-abi=softfp)
endif()

add_compile_options(
        -mfpu=neon-vfpv4
        -funsafe-math-optimizations
        -ftree-vectorize
        -ffast-math
)

include_directories(${CMAKE_SOURCE_DIR}/OpenRoleZoo/include)
include_directories(${CMAKE_SOURCE_DIR}/TenniS/include)
include_directories(${CMAKE_SOURCE_DIR}/SeetaAuthorize/include)
include_directories(${CMAKE_SOURCE_DIR}/FaceDetector/include)
include_directories(${CMAKE_SOURCE_DIR}/FaceRecognizer/include)
include_directories(${CMAKE_SOURCE_DIR}/Landmarker/include)
include_directories(${CMAKE_SOURCE_DIR}/PoseEstimator6/include)
include_directories(${CMAKE_SOURCE_DIR}/QualityAssessor3/include)

add_library(ORZ_static STATIC IMPORTED)
set_target_properties(ORZ_static PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/OpenRoleZoo/lib/${CMAKE_ANDROID_ARCH_ABI}/libORZ_static.a)

add_library(tennis SHARED IMPORTED)
set_target_properties(tennis PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/TenniS/lib/${CMAKE_ANDROID_ARCH_ABI}/libtennis.so)

add_library(SeetaAuthorize SHARED IMPORTED)
set_target_properties(SeetaAuthorize PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/SeetaAuthorize/lib/${CMAKE_ANDROID_ARCH_ABI}/libSeetaAuthorize.so)

add_library(SeetaFaceDetector600 SHARED IMPORTED)
set_target_properties(SeetaFaceDetector600 PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/FaceDetector/lib/${CMAKE_ANDROID_ARCH_ABI}/libSeetaFaceDetector600.so)

add_library(SeetaFaceRecognizer610 SHARED IMPORTED)
set_target_properties(SeetaFaceRecognizer610 PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/FaceRecognizer/lib/${CMAKE_ANDROID_ARCH_ABI}/libSeetaFaceRecognizer610.so)

add_library(SeetaFaceLandmarker600 SHARED IMPORTED)
set_target_properties(SeetaFaceLandmarker600 PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/Landmarker/lib/${CMAKE_ANDROID_ARCH_ABI}/libSeetaFaceLandmarker600.so)

add_library(SeetaPoseEstimation600 SHARED IMPORTED)
set_target_properties(SeetaPoseEstimation600 PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/PoseEstimator6/lib/${CMAKE_ANDROID_ARCH_ABI}/libSeetaPoseEstimation600.so)

add_library(SeetaQualityAssessor300 SHARED IMPORTED)
set_target_properties(SeetaQualityAssessor300 PROPERTIES IMPORTED_LOCATION ${CMAKE_SOURCE_DIR}/QualityAssessor3/lib/${CMAKE_ANDROID_ARCH_ABI}/libSeetaQualityAssessor300.so)

add_library(
        SeetaFaceDetector600_java
        SHARED
        JNIFaceDetector.cpp)

target_link_libraries(
        SeetaFaceDetector600_java
        tennis
        SeetaAuthorize
        ORZ_static
        SeetaFaceDetector600
        log
)

add_library(
        SeetaFaceRecognizer610_java
        SHARED
        JNIFaceDatabase.cpp
        JNIFaceRecognizer.cpp)

target_link_libraries(
        SeetaFaceRecognizer610_java
        tennis
        SeetaAuthorize
        ORZ_static
        SeetaFaceRecognizer610
        log
)

add_library(
        SeetaFaceLandmarker600_java
        SHARED
        JNIFaceLandmarker.cpp)

target_link_libraries(
        SeetaFaceLandmarker600_java
        tennis
        SeetaAuthorize
        ORZ_static
        SeetaFaceLandmarker600
        log
)

add_library(
        SeetaQualityAssessor300_java
        SHARED
        JNIQualityOfBrightness.cpp
        JNIQualityOfClarity.cpp
        JNIQualityOfIntegrity.cpp
        JNIQualityOfPose.cpp
        JNIQualityOfPoseEx.cpp)

target_link_libraries(
        SeetaQualityAssessor300_java
        tennis
        SeetaAuthorize
        ORZ_static
        SeetaPoseEstimation600
        SeetaQualityAssessor300
        log
)