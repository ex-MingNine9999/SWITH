#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <signal.h>
#include <sys/time.h>
#include <string>

#include "findEyeCorner.h"
#include "findEyeCenter.h"
#include "constants.h"
#include "helpers.h"
#include "logEyeTracking.h"
#include "eyeLike.h"
#include "curlSend.h"
#include "swith.h"

#define INF 1234567890

concen con;

int main(void)
{
    CvCapture *capture;
    eyeLocation eyes;

	if (signal(SIGALRM, ALARM_SEND) == SIG_ERR) { 
		fprintf(stderr, "signal handling error\n");
		exit(1);
	}

	if (curlInit() < 0) {
		fprintf(stderr, "curlInit error\n");
		exit(1);
	}

    capture = eyeLikeInit();
	
	const eyeBoundary bound = setBoundary(capture);
	
	while (1) {
		alarm(ALARM_TIMER);
		con.flag = 0;
		con.total = con.ok = 0;

		while (con.flag == 0) {
			eyes = eyeLikeModule(capture);

			con.total++;
			if (eyeChecking(bound, eyes)) {
				con.ok++;
			}
		}
	}
    
    eyeLikeEnd();
	curlEnd();

    return 0;
}

eyeBoundary updateBoundary(eyeBoundary bound, eyeLocation eyes)
{
	if (eyes.leftCenter.x == 0) {
		return bound;
	}

    bound.left_minX = min(bound.left_minX, eyes.leftCenter.x);
    bound.left_minY = min(bound.left_minY, eyes.leftCenter.y);
    
    bound.right_minX = min(bound.right_minX, eyes.rightCenter.x);
    bound.right_minY = min(bound.right_minY, eyes.rightCenter.y);
    
    bound.left_maxX = max(bound.left_maxX, eyes.leftCenter.x);
    bound.left_maxY = max(bound.left_maxY, eyes.leftCenter.y);
    
    bound.right_maxX = max(bound.right_maxX, eyes.rightCenter.x);
    bound.right_maxY = max(bound.right_maxY, eyes.rightCenter.y);
    
    return bound;
}

eyeBoundary setBoundary(CvCapture *capture)
{
	eyeLocation eyes;
    eyeBoundary bound = {INF, INF, INF, INF, -INF, -INF, -INF, -INF};
	int fd;

	if (access("bound.ini", F_OK) < 0) {
		printf("see the corner on the moniter\n");
		
		if ((fd = open("bound.ini", O_CREAT | O_WRONLY, 0666)) < 0) {
			fprintf(stderr, "bound write open error\n");
			exit(1);
		}

		for (int i = 0; i < 300; i++) {
			eyes = eyeLikeModule(capture);
			bound = updateBoundary(bound, eyes);
		}
		

		write(fd, &bound, sizeof(eyeBoundary));
		
		close(fd);
	}

	if ((fd = open("bound.ini", O_RDONLY)) < 0) {
		fprintf(stderr, "bound read open error\n");
		exit(1);
	}
	
	if ((read(fd, &bound, sizeof(eyeBoundary))) < sizeof(eyeBoundary)) {
			fprintf(stderr, "read error\n");
			exit(1);
	}

	close(fd);

	return bound;
}

int eyeChecking(eyeBoundary bound, eyeLocation eyes)
{
	if (eyes.leftEye.x >= bound.left_minX && eyes.leftEye.x <= bound.left_maxX &&
		eyes.leftEye.y >= bound.left_minY && eyes.leftEye.y <= bound.left_maxY &&
		eyes.rightEye.x >= bound.right_minX && eyes.rightEye.x <= bound.right_maxX && 
		eyes.rightEye.y >= bound.right_minY && eyes.rightEye.y <= bound.right_maxY) {
        return 1;
    }
	else{
        return 0;
    }
}

static void sending(int signo)
{
	const concen c = con;
	int concentration = (int)((c.ok * 1000) / (c.total * 9));

	concentration = concentration > 100 ? 100 : concentration;

	printf("%d\n", concentration);

	curlSend(concentration);
	con.flag = 1;
}
