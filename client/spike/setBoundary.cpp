#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

int leftEye_X, leftEye_Y, rightEye_X, rightEye_Y;
int leftMax_X, leftMax_Y, leftMin_X, leftMin_Y;
int rightMax_X, rightMax_Y, rightMin_X, rightMin_Y;

int main(void)
{
	FILE *setFp;

	if (access("setting", F_OK) == 0) {
		setFp = fopen("setting", "r");
	}
	else {

	}

	fscanf(setFp, "%d%d%d%d", &


	return 0;
}
