#ifndef CURL_SEND_H
#define CURL_SEND_H

#include <stdio.h>
#include <curl/curl.h>

#define PORTNUM 8080
#define DB_URL "ec2-15-164-111-138.ap-northeast-2.compute.amazonaws.com:8080/api/v3/datasave"
#define CHECK_URL "abcde"
#define CHECK_MSG "check"
#define TO_JSON "Content-Type: application/json"

int curlInit(void);
CURLcode curlSend(int concentration);
int curlEnd(void);
CURLcode curlCheckBoundary(void);

#endif
