#ifndef PRINT_LOG_H
#define PRINT_LOG_H

#include <opencv2/highgui/highgui.hpp>
#include <opencv2/imgproc/imgproc.hpp>

struct rect {
	int u_y, d_y;
	int l_x, r_x;
};

void printRect(cv::Rect _rect);
void printPoint(cv::Point _point);
void logEye(cv::Rect leftEye, cv::Rect rightEye, cv::Point leftCenter, cv::Point rightCenter);


#endif
