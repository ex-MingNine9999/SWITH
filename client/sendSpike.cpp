#include <stdio.h>
#include <curl/curl.h>

int main(void)
{
	CURL *curl;
	CURLcode res;

	curl = curl_easy_init();

	struct curl_slist *list = NULL;

	if(curl) {
		curl_easy_setopt(curl, CURLOPT_URL, "http://www.example.com");
		curl_easy_setopt(curl, CURLOPT_POST, 1L);

		res = curl_easy_perform(curl);
		curl_easy_cleanup(curl); // curl_easy_init 과 세트
	}

	printf("\n%d\n", res);
	
	return 0;

}

