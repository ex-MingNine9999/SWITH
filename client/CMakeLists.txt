PROJECT( SPIKE_CURLPP )
#FIND_PACKAGE( curlpp REQUIRED )
cmake_minimum_required(VERSION 2.8)

INCLUDE_DIRECTORIES("~/curlpp/include")
LINK_DIRECTORIES("~/curlpp")
LINK_LIBRARIES(libcurlpp.1.0.0.dylib)
LINK_LIBRARIES(libcurlpp.1.dylib)
LINK_LIBRARIES(libcurlpp.a)
LINK_LIBRARIES(libcurlpp.dylib)

set(CMAKE_CXX_STANDARD 11)
SET(CMAKE_CXX_FLAGS "-lcurl")

ADD_EXECUTABLE(a.out sendSpike.cpp)
