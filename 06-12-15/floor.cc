#include "floor.h"
using namespace std;

Floor::Floor( int id ) : id( id ) {}

void Floor::addPassenger( Passenger *p ) {
	for ( list< Passenger* >::iterator it = passengers.begin(); it != passengers.end(); ++it ) {
		if ( ( *it )->getTime() > p->getTime() ) {
			passengers.insert( it, p );
			return;
		}
	}

	passengers.push_back( p );
}

list< Passenger* > Floor::pickUp( int time, int numPick ) {
	list< Passenger* > send;

	list< Passenger* >::iterator it = passengers.begin(); 
	while( it != passengers.end() ) {
		if ( ( *it )->getTime() > time ) break;
		if ( send.size() == numPick )  break;
		send.push_back( *it );
		it = passengers.erase( it );
	}

	return send;
}

bool Floor::isEmpty() {
	return passengers.empty();
}
