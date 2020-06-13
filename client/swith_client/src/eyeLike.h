#ifndef EYELIKE_H
#define EYELIKE_H

#include <opencv2/objdetect/objdetect.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>

/* Attempt at supporting openCV version 4.0.1 or higher */
#if CV_MAJOR_VERSION >= 4
#define CV_WINDOW_NORMAL                cv::WINDOW_NORMAL
#define CV_BGR2YCrCb                    cv::COLOR_BGR2YCrCb
#define CV_HAAR_SCALE_IMAGE             cv::CASCADE_SCALE_IMAGE
#define CV_HAAR_FIND_BIGGEST_OBJECT     cv::CASCADE_FIND_BIGGEST_OBJECT
#endif

struct eyeLocation {
	cv::Rect leftEye;
	cv::Point leftCenter;
	cv::Rect rightEye;
	cv::Point rightCenter;
};

CvCapture* eyeLikeInit(void);
eyeLocation eyeLikeModule(CvCapture *capture);
void eyeLikeEnd(void);
eyeLocation findEyes(cv::Mat frame_gray, cv::Rect face);
cv::Mat findSkin (cv::Mat &frame);
eyeLocation detectAndDisplay( cv::Mat frame );

#endif
