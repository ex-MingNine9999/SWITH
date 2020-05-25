#include "socket.h"

int connect_server(void)
{
	/*
	struct sockaddr_in serv_addr;
	struct hostent *host;
	int sock;

	host = gethostbyname(SERVER_DOMAIN);
	sock = socket(PF_INET, SOCK_STREAM, 0);
	memset(&serv_addr, 0, sizeof(sockaddr_in));
	serv_addr.sin_family = AF_INET;
	serv_addr.sin_addr.s_addr = inet_addr(inet_ntoa(*(struct in_addr*)*host->h_addr_list));
	serv_addr.sin_port = htons(SERVER_PORT);

	if (connect(sock, (struct sockaddr*)&serv_addr, sizeof(serv_addr)) == -1) {
		fprintf(stderr, "connect() error\n");
		exit(1);
	}

	return sock;
	*/
	return 0;
}

void test(void)
{
	char buf[BUFSIZ] = { "what the fuck\n" };
	int sock;

	sock = connect_server();

	write(sock, buf, 14);

	close(sock);

	return;
}
