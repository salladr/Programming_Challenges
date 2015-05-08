#include <iostream>
#include <vector>
#include <cstdlib>
using namespace std;

int main() {
	string str;
	int len, discrepency, maxDiscrepency;
	int maxStart, maxEnd, maxStep;
	char first;

	while( cin >> str ) {
		len = str.length();
		first = str[0];
		maxDiscrepency = 0;

		for ( int step = 1; len / step > maxDiscrepency; step += 1 ) {
			for ( int start = 0; start < len; start += 1 ) {
				discrepency = 0;
				for ( int end = start; end < len; end += step ) {
					if ( str[end] == first ) discrepency += 1;
					else discrepency -= 1;

					if ( abs( discrepency ) > maxDiscrepency ) {
						maxDiscrepency = abs( discrepency );
						maxStart = start;
						maxEnd = end + 1;
						maxStep = step;
					}
				}
			}
		}
		cout << maxDiscrepency << "	s[" << maxStart << ":" << maxEnd << ":" << maxStep << "]" << endl;
	}
}
