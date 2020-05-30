#ifndef EYELIKE_H
#define EYELIKE_H

#include <opencv2/objdetect/objdetect.hpp>
#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>

struct eyeLocation {
	cv::Rect leftEye;
	cv::Point leftCenter;
	cv::Rect rightEye;
	cv::Point rightCenter;
};

CvCapture* eyeLikeInit(void);
eyeLocation eyeLikeModule(CvCapture *capture);
eyeLocation findEyes(cv::Mat frame_gray, cv::Rect face);
cv::Mat findSkin (cv::Mat &frame);
eyeLocation detectAndDisplay( cv::Mat frame );
void eyeLikeEnd(void);

#endif
