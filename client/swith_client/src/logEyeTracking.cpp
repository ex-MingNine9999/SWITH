#include "logEyeTracking.h"

#include <cstdio>

void printRect(cv::Rect _rect)
{
	printf("left top X, Y : (%d, %d)\n", _rect.x, _rect.y);
	printf("height : %d\n", _rect.height);
	printf("width : %d\n", _rect.width);

	return;
}

void printPoint(cv::Point _point)
{
	printf("Point X, Y : (%d, %d)\n", _point.x, _point.y);

	return;
}

void logEye(cv::Rect leftEye, cv::Rect rightEye, cv::Point leftCenter, cv::Point rightCenter)
{
	printf("\n---LeftEyeRegion---\n");
	printRect(leftEye);
	printf("\n---LeftCenter---\n");
	printPoint(leftCenter);

	printf("\n---RightEyeRegion---\n");
	printRect(rightEye);
	printf("\n---RightCenter---\n");
	printPoint(rightCenter);

	return; 
}

rect findEyeBox(cv::Rect eye)
{
	rect ret;

	ret.u_y = eye.y;
	ret.d_y = eye.y + eye.height;
	ret.l_x = eye.x;
	ret.r_x = eye.x + eye.width;

	return ret;
}

rect checkLimit(cv::Rect eye, cv::Point center)
{
	rect ret;


	return ret;
}
