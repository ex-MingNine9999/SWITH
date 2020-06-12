#include "curlSend.h"
#include <string>

CURL *curl = NULL;

int curlInit(void)
{
	curl = curl_easy_init();

	if (curl) {
		curl_slist *list = NULL;
		list = curl_slist_append(list, TO_JSON);
		curl_easy_setopt(curl, CURLOPT_URL, DB_URL);
		curl_easy_setopt(curl, CURLOPT_HTTPHEADER, list);
		return 1;
	}

	return -1;
}

CURLcode curlSend(int concentration)
{
	CURLcode res = CURLE_FAILED_INIT;

	if (curl) {
		curl_easy_setopt(curl, CURLOPT_POSTFIELDS, std::to_string(concentration).c_str());

		res = curl_easy_perform(curl);
	}

	return res;
}

int curlEnd(void)
{
	if (curl) {
		curl_easy_cleanup(curl);
		return 1;
	}
	return -1;
}

CURLcode curlCheckBoundary(void)
{
	CURL *c;
	CURLcode res = CURLE_FAILED_INIT;

	c = curl_easy_init();
	if (c) {
		curl_slist *list = curl_slist_append(list, TO_JSON);
		curl_easy_setopt(curl, CURLOPT_URL, CHECK_URL);
		curl_easy_setopt(curl, CURLOPT_HTTPHEADER, list);
		curl_easy_setopt(curl, CURLOPT_POSTFIELDS, CHECK_MSG);

		res = curl_easy_perform(c);
	}

	return res;
}
