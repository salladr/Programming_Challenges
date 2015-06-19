#include <iostream>
#include <vector>
#include <algorithm>
#include <iomanip>
using namespace std;

vector< double > bruteForce( double capacity, vector< double > nuggets ) {
	double maxSum = 0;
	vector< double > take;

	do {
		double sum = 0;
		vector< double > temp;
		for ( int i = 0; i < nuggets.size(); i += 1 ) {
			if ( sum + nuggets[i] > capacity )
				break;

			sum += nuggets[i];
			temp.push_back( nuggets[i] );
		}

		if ( sum > maxSum ) {
			maxSum = sum;
			temp.push_back( sum );
			take = temp;
		}

	} while ( next_permutation( nuggets.begin(), nuggets.end() ) );

	return take;
}

int main() {
	// Read in the capacity, and the gold nuggets
	double backCapacity;
	cin >> backCapacity;

	int numNuggets;
	cin >> numNuggets;

	vector< double > nuggets;
	double nugget;
	for ( int i = 0; i < numNuggets; i += 1 ) {
		cin >> nugget;
		nuggets.push_back( nugget );
	}

	vector< double > result = bruteForce( backCapacity, nuggets );

	for ( int i = result.size() - 1; i >= 0; i -= 1 ) {
		cout << setprecision(7) << result[i] << endl;
	}
}
