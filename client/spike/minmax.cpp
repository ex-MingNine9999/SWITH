#include <iostream>
#include <algorithm>

using namespace std;

int main(void)
{
	int MIN = 1234567890;
	int a;
	for (int i = 0; i < 10; i++) {
		cin >> a;
		MIN = mini(MIN, a);
	}

	cout << MAX;

	return 0;
}
