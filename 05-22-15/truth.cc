#include <iostream>
#include <iomanip>
using namespace std;

template< typename T >
void printTruth( T t, string s ) {
	cout << left << setw(20) << s;

	if ( t ) cout << setw(20) << "True" << endl;
	else cout << setw(20) << "False" << endl;
}

int main() {
	cout << left << setw(20) << "Expression" << setw(20) << "Bool" << endl;
	printTruth( "Hello World!", "Hello World!" );
	printTruth( "", "" );
	printTruth( '0', "0" );
	printTruth( 1, "1" );
	printTruth( 0, "0" );
	printTruth( 0.0, "0.0" );
	int b[0] = {};
	printTruth( b, "[]" );
	int a[3] = { 1, 2, 3 };
	printTruth( a, "[1, 2, 3]" );
	printTruth( NULL, "nullptr" );
	printTruth( &a, "ptr" );
	printTruth( true, "True" );
	printTruth( false, "False" );
}
