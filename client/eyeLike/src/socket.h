#ifndef SOCKET_H
#define SOCKET_H

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#include <sys/types.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netinet/in.h>

#define SERVER_ADDR "172.30.1.23"
#define SERVER_DOMAIN "172.30.1.23:8080/ws/webcam"
#define SERVER_PORT 8080

int connect_server(void);
void test(void);

#endif
