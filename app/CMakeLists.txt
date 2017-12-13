#指定 cmake 的最小版本，确保能使用某些新特性构建项目
cmake_minimum_required(VERSION 3.4.1)

# 输出详细信息
set(CMAKE_VERBOSE_MAKEFILE on)

# 设置库目录
set(LIBRARY_DIRS "${CMAKE_SOURCE_DIR}/src/main/jniLibs")

# 包含头文件目录
include_directories(src/main/cpp/include)


# 添加 opencv 的动态库
add_library( # Sets the name of the library.
             libopencv_java3

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             IMPORTED )
# 指定库路径
set_target_properties( # Specifies the target library.
                       libopencv_java3

                       # Specifies the parameter you want to define.
                       PROPERTIES IMPORTED_LOCATION

                       # Provides the path to the library you want to import.
                       "${LIBRARY_DIRS}/${ANDROID_ABI}/libopencv_java3.so" )


# 设置源文件目录
aux_source_directory(src/main/cpp/source SOURCE_DIR)

# Specifies a library name, specifies whether the library is STATIC or
# SHARED, and provides relative paths to the source code. You can
# define multiple libraries by adding multiple add.library() commands,
# and CMake builds them for you. When you build your app, Gradle
# automatically packages shared libraries with your APK.
add_library( # Specifies the name of the library.
             native-lib

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             ${SOURCE_DIR}
           )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.
# 添加 log 库
find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.
# 链接库
target_link_libraries( # Specifies the target library.
                       native-lib

                       libopencv_java3
                       ${log-lib}
                     )