#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#include <sys/time.h>
#include <signal.h>

typedef struct _concern {
	int total;
	int ok;
} concern;

concern con;
int flag = 0;

static void sending(int signo);

int main(void)
{
	if (signal(SIGALRM, sending) == SIG_ERR) {
		fprintf(stderr, "signal handling error\n");
		exit(1);
	}

	srand(time(NULL));

	while (1) {
		alarm(2);
		flag = 0;
		con.total = con.ok = 0;

		while (flag == 0) {
			usleep(10);
			con.total++;
			if (rand() % 2) {
				con.ok++;
			}
		}
	}

	return 0;
}

static void sending(int signo)
{
	concern c = con;

	printf("%d * 100 / %d = %d\n", c.ok, c.total, c.ok * 100 / c.total);

	flag = 1;
}
