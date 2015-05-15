#include <iostream>
#include <vector>
#include <cmath>
#include <algorithm>
using namespace std;

struct Treat {
	double x, y;
	double distCenter;

	Treat( double x, double y, double distCenter ) : x( x ), y( y ), distCenter( distCenter ) {}
	Treat() {}
};

double distance( double x1, double y1, double x2, double y2 ) {
	return sqrt( pow( ( x1 - x2 ), 2.0 ) + pow( ( y1 - y2 ), 2.0 ) );
}

int main() {
	int numTreats;
	cin >> numTreats;

	vector< Treat > treats;
	double x, y, distCenter;
	double minDistance = 1;
	int position;
	for ( int i = 0; i < numTreats; i += 1 ) {
		cin >> x;
		cin >> y;
		distCenter = distance( x, y, 0.5, 0.5 );
		if ( distCenter < minDistance ) {
			minDistance = distCenter;
			position = i;
		}
		treats.push_back( Treat( x, y, distCenter ) );
	}

	double totalDistance = 0.0;
	Treat t;
	while ( treats.size() > 0 ) {
		swap( treats[position], treats[ treats.size() - 1 ] );
		t = treats.back();
		treats.pop_back();
		totalDistance += t.distCenter;
		minDistance = 1;
		for ( int i = 0; i < treats.size(); i += 1 ) {
			distCenter = distance( treats[i].x, treats[i].y, t.x, t.y );
			treats[i].distCenter = distCenter;
			if ( distCenter < minDistance ) {
				minDistance = distCenter;
				position = i;
			}
		}
	}
	cout << totalDistance << endl;
}
