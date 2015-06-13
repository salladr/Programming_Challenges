#include "passenger.h"
using namespace std;

Passenger::Passenger( int id, int time, int source, int destination ) :
	id( id ), time( time ), source( source ), destination( destination ) {}

int Passenger::getTime() {
	return time;
}

int Passenger::getSource() {
	return source;
}

int Passenger::getDestination() {
	return destination;
}
