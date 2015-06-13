#ifndef __FLOOR_H__
#define __FLOOR_H__
#include "passenger.h"
#include <list>

class Floor {
	int id;
	std::list< Passenger* > passengers;

  public:
	Floor( int id );
	void addPassenger( Passenger *p );
	std::list< Passenger* > pickUp( int time, int numPick );
	bool isEmpty();
};

#endif
