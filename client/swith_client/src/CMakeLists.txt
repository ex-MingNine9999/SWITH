#find_library(MGL mgl PATHS /usr/local/lib)

ADD_EXECUTABLE( swith_client main.cpp swith.h findEyeCenter.cpp findEyeCenter.h findEyeCorner.cpp findEyeCorner.h constants.h helpers.cpp helpers.h logEyeTracking.h logEyeTracking.cpp eyeLike.h eyeLike.cpp curlSend.h curlSend.cpp )
#ADD_COMPILE_OPTIONS( -lcurl )
TARGET_LINK_LIBRARIES( swith_client ${OpenCV_LIBS} )
#target_link_libraries( eyeLike ${MGL})
