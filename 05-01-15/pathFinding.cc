#include <iostream>
#include <vector>
#include <string>
#include <cstdlib>
#include <stdexcept>
using namespace std;

enum Direction { NORTH, EAST, SOUTH, WEST };

void findPath( int startY, int startX, vector< vector< bool > > &maze, string path, Direction dir ) {
	try {
		int currentX = startX;
		int currentY = startY;
		int move;
		string num = "";

		for ( int i = 0; i < path.length(); i += 1 ) {
			if ( isdigit( path[i] ) ) {
				while ( isdigit( path[i] ) ) {
					num += path[i];
					i += 1;
				}
				move = atoi( num.c_str() );
				num = "";

				for ( int i = 0; i < move; i += 1 ) {
					if ( dir == NORTH ) currentY -= 1;
					else if ( dir == EAST ) currentX += 1;
					else if ( dir == SOUTH ) currentY += 1;
					else if ( dir == WEST ) currentX -= 1;

					if ( !maze.at( currentY ).at( currentX ) ) return;
				}
			}
			if ( path[i] == 'r' ) dir = static_cast< Direction >( ( dir + 1 ) % 4 );
			if ( path[i] == 'l' ) dir = static_cast< Direction >( ( dir + 3 ) % 4 );
		}

		cout << "From (" << startX << ", " << startY
			 << ") to (" << currentX << ", " << currentY << ")" << endl;

	} catch ( const out_of_range oor ) {
		return;
	}
}

int main() {
	vector< vector<bool> > maze;
	int numLines;
	string path, input;

	cin >> numLines;
	cin.ignore( 10000, '\n' );

	for ( int i = 0; i < numLines; i += 1 ) {
		vector< bool > line;
		getline( cin, input );
		for ( int j = 0; j < input.length(); j += 1 ) {
			if ( isspace( input[j] ) ) line.push_back( true );
			else line.push_back( false );
		}
		maze.push_back( line );
	}

	cin >> path;

	for ( int i = 0; i < numLines; i += 1 ) {
		for ( int j = 0; j < maze[i].size(); j += 1 ) {
			if ( maze[i][j] ) {
				findPath( i, j, maze, path, NORTH );
				findPath( i, j, maze, path, EAST );
				findPath( i, j, maze, path, SOUTH );
				findPath( i, j, maze, path, WEST );
			}
		}
	}
}
