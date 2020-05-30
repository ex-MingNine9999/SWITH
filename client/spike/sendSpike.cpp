#include <stdio.h>
#include <curl/curl.h>
#include <string>

int main(void)
{
	CURL *curl;
	CURLcode res;
	int a = 100;

	curl = curl_easy_init();

	curl_slist *list = NULL;

	list = curl_slist_append(list, "Content-Type: application/json");
	if(curl) {
		curl_easy_setopt(curl, CURLOPT_URL, "ec2-15-164-111-138.ap-northeast-2.compute.amazonaws.com:8080/api/v3/datasave");
		curl_easy_setopt(curl, CURLOPT_HTTPHEADER, list);
		curl_easy_setopt(curl, CURLOPT_POSTFIELDSIZE, 3L);
		curl_easy_setopt(curl, CURLOPT_POSTFIELDS, std::to_string(a).c_str());

		res = curl_easy_perform(curl);
		curl_easy_cleanup(curl); 
	}

	printf("\n%s\n", std::to_string(a).c_str());
	
	return 0;

}

