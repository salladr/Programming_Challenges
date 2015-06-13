#include <iostream>
#include <vector>
#include "elevator.h"
#include "floor.h"
#include "passenger.h"
using namespace std;

int main() {
	// read in elevators
	vector< Elevator > elevators;
	int numElevators;
	cin >> numElevators;

	char c;
	int id, capacity, speed, start;
	for ( int i = 0; i < numElevators; i += 1 ) {
		cin >> c;
		cin >> id;
		cin >> capacity;
		cin >> c;
		cin >> speed;
		cin >> start;

		Elevator e( id, capacity, speed, start - 1 );
		elevators.push_back( e );
	}

	// read in passengers
	vector< Floor* > floors( 10, NULL );
	int requests;
	cin >> requests;

	int time, source, destination;
	int maxFloor = 0;
	for ( int i = 0; i < requests; i += 1 ) {
		cin >> c;
		cin >> id;
		cin >> time;
		cin >> source;
		cin >> destination;

		if ( source > floors.size() ) floors.resize( source, NULL );
		if ( floors[ source - 1 ] == NULL ) floors[ source - 1 ] = new Floor( source - 1 );
		if ( destination > maxFloor ) maxFloor = destination;
		if ( source > maxFloor ) maxFloor = source;

		Passenger *p = new Passenger( id, time, source - 1, destination - 1 );
		floors[ source - 1 ]->addPassenger( p );
	}

	if ( maxFloor > floors.size() ) floors.resize( maxFloor, NULL );
	for ( unsigned int i = 0; i < floors.size(); i += 1 ) {
		if ( floors[i] == NULL ) floors[i] = new Floor(i);
	}

	for ( int i = 0; i < numElevators; i += 1 ) {
		elevators[i].addFloors( floors );
	}

	// run simulation
	bool done = false;
	time = 0;
	while ( true ) {
		for ( int i = 0; i < numElevators; i += 1 ) {
			elevators[i].update( time );
		}

		done = true;
		for ( unsigned int i = 0; i < floors.size(); i += 1 ) {
			done = done && floors[i]->isEmpty();
		}
		for ( int i = 0; i < numElevators; i += 1 ) {
			done = done && elevators[i].isEmpty();
		}
		if ( done ) break;

		time += 1;
	}

	cout << "Time elapsed: " << time << endl;

	// cleanup floors
	for ( unsigned int i = 0; i < floors.size(); i += 1 ) {
		delete floors[i];
	}
}
