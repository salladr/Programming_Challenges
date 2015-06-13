#ifndef __ELEVATOR_H__
#define __ELEVATOR_H__
#include "floor.h"
#include "passenger.h"
#include <vector>
#include <list>

class Elevator {
	int id;
	int capacity;
	int speed;
	int currentFloor;
	bool movingUp;
	std::vector< Floor* > floors;

	std::list< Passenger* > goingUp;
	std::list< Passenger* > goingDown;
	int goingTo;

  public:
	Elevator( int id, int capacity, int speed, int currentFloor );
	void addFloors( std::vector< Floor* > floors );
	bool isEmpty();
	void dropOff();
	void addGoingUp( Passenger *p );
	void addGoingDown( Passenger *p );
	void pickUp( int time );
	void update( int time );
};

#endif
