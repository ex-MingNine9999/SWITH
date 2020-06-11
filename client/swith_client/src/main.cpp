#include <cstdio>
#include <string>

#include "findEyeCorner.h"
#include "findEyeCenter.h"
#include "constants.h"
#include "helpers.h"
#include "logEyeTracking.h"
#include "eyeLike.h"
#include "curlSend.h"

int main(void)
{
	printf("hello world!\n");

	CvCapture *capture;
	eyeLocation eyes;

	while (1) {
		capture = eyeLikeInit();
		eyes = eyeLikeModule(capture);

		printf("%d %d \n", eyes.leftCenter.x, eyes.leftCenter.y);
	}

	eyeLikeEnd();

	return 0;
}
