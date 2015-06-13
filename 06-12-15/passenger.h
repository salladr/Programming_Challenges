#ifndef __PASSENGER_H__
#define __PASSENGER_H__

class Passenger {
	int id;
	int time;
	int source;
	int destination;

  public:
	Passenger( int id, int time, int source, int destination );
	int getTime();
	int getSource();
	int getDestination();
};

#endif
