#include "elevator.h"
using namespace std;

Elevator::Elevator( int id, int capacity, int speed, int currentFloor ) :
	id( id ), capacity( capacity ), speed( speed ), currentFloor( currentFloor ), movingUp( true ) {}

void Elevator::addFloors( vector< Floor* > floors ) {
	this->floors = floors;
}

bool Elevator::isEmpty() {
	return goingUp.empty() && goingDown.empty();
}

void Elevator::dropOff() {
	list< Passenger* >::iterator up = goingUp.begin();
	while ( up != goingUp.end() ) {
		if ( ( *up )->getDestination() > ( currentFloor / 10 ) ) break;

		if ( ( *up )->getDestination() == ( currentFloor / 10 ) ) {
			delete *up;
			up = goingUp.erase( up );
		} else
			++up;
	}

	list< Passenger* >::iterator down = goingDown.begin();
	while ( down != goingDown.end() ) {
		if ( ( *down )->getDestination() < ( currentFloor / 10 ) ) break;

		if ( ( *down )->getDestination() == ( currentFloor / 10 ) ) {
			delete *down;
			down = goingDown.erase( down );
		} else
			++down;
	}
}

void Elevator::addGoingUp( Passenger *p ) {
	for ( list< Passenger* >::iterator it = goingUp.begin(); it != goingUp.end(); ++it ) {
		if ( ( *it )->getDestination() > p->getDestination() ) {
			goingUp.insert( it, p );
			return;
		}
	}

	goingUp.push_back( p );
}

void Elevator::addGoingDown( Passenger *p ) {
	for ( list< Passenger* >::iterator it = goingDown.begin(); it != goingDown.end(); ++it ) {
		if ( ( *it )->getDestination() < p->getDestination() ) {
			goingDown.insert( it, p );
			return;
		}
	}

	goingDown.push_back( p );
}

void Elevator::pickUp( int time ) {
	int numPick = capacity - ( goingUp.size() + goingDown.size() );
	list< Passenger* > newPassengers = floors[ currentFloor / 10 ]->pickUp( time, numPick );

	for ( list< Passenger* >::iterator it = newPassengers.begin(); it != newPassengers.end(); ++it ) {
		if ( ( *it )->getDestination() > ( *it )->getSource() )
			addGoingUp( *it );
		else
			addGoingDown( *it );
	}
}

void Elevator::update( int time ) {
	if ( movingUp ) currentFloor += speed;
	if ( !movingUp || time == 0 ) currentFloor -= speed;

	if ( currentFloor % 10 != 0 ) return;

	dropOff();
	pickUp( time );

	if ( goingUp.size() == 0 ) movingUp = false;
	if ( goingDown.size() == 0 ) movingUp = true;
	if ( currentFloor / 10 == floors.size() - 1 ) movingUp = false;
	if ( currentFloor == 0 ) movingUp = true;
}
