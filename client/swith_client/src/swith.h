#ifndef SWITH_H
#define SWITH_H

#define ALARM_SEND sending
#define ALARM_TIMER 3

struct eyeBoundary{
    int left_minX;
    int left_minY;
    
    int right_minX;
    int right_minY;
    
    int left_maxX;
    int left_maxY;
    
    int right_maxX;
    int right_maxY;
};

struct concen {
	unsigned int total;
	unsigned int ok;
	int flag;
};

int max(int a, int b)
{
	return a > b ? a : b;
}

int min(int a, int b)
{
	return a < b ? a : b;
}

eyeBoundary updateBoundary(eyeBoundary bound, eyeLocation eyes);
eyeBoundary setBoundary(CvCapture *capture);
int eyeChecking(eyeBoundary bound, eyeLocation eyes);
static void sending(int signo);

#endif
