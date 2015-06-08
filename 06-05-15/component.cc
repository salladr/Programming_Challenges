#include <vector>
#include <cstring>
#include <iostream>
#include <string>
using namespace std;

struct Component {
	int superHeight, subHeight;
	vector< string > view;
	Component( string comp );
	int getHeight() { return view.size(); }
	string parse( int &i, string comp);
	int frac( string comp, int i );
	int sqrt( string comp, int i );
};

string Component::parse( int &i, string comp ) {
	string val = "";
	int numBraces = 1;
	char token = comp[i];
	while ( numBraces != 0 ) {
		val += token;
		i += 1;
		token = comp[i];
		if ( token == '{' ) numBraces += 1;
		if ( token == '}' ) numBraces -= 1;
	}
	return val;
}

int Component::sqrt( string comp, int i ) {
	string val = parse( i, comp );
	Component sq( val );

	string base = view[ superHeight ];
	if ( sq.superHeight > superHeight ) superHeight = sq.superHeight;

	while ( view.size() - 1 - subHeight < superHeight ) {
		view.insert( view.begin(), "" );
	}

	if ( sq.subHeight > subHeight ) subHeight = sq.subHeight;
	while ( view.size() - 1 - superHeight < subHeight ) {
		view.push_back( "" );
	}

	for ( int j = 0; j < sq.getHeight(); j += 1 ) {
		for ( int k = 0; k < base.size() - view[j].size() + k; k += 1 ) {
			view[j] += " ";
		}
		if ( j == sq.superHeight ) {
			view[j] += "V";
			for ( int l = 1; l < sq.getHeight(); l += 1 ) {
				view[j] += " ";
			}
		} else if ( j < sq.superHeight ) {
			for ( int l = 1; l < sq.getHeight() - j; l += 1 ) {
				view[j] += " ";
			}
			view[j] += "/";
			for ( int l = 0; l < j; l += 1 ) {
				view[j] += " ";
			}
		}
		view[j] += sq.view[j];
	}

	superHeight += 1;
	view.insert( view.begin(), "" );

	for ( int j = 0; j < base.size() + sq.getHeight(); j += 1 ) {
		view[0] += " ";
	}

	for ( int j = 0; j < sq.view[ sq.superHeight ].size(); j += 1 ) {
		view[0] += "_";
	}

	return i;
}

int Component::frac( string comp, int i ) {
	string val = parse( i, comp );
	Component numerator( val );

	i += 2;
	val = parse( i, comp );
	Component denominator( val );

	int maxLen = max( numerator.view[ numerator.superHeight ].size(), denominator.view[ denominator.superHeight ].size() );
	string base = view[ superHeight ];
	if ( numerator.getHeight() > superHeight ) superHeight = numerator.getHeight();

	while ( view.size() - 1 - subHeight < superHeight ) {
		view.insert( view.begin(), "" );
	}
 
	int center = ( maxLen - numerator.view[ numerator.superHeight ].size() ) / 2;
	for ( int j = 0; j < numerator.getHeight(); j += 1 ) {
		for ( int k = 0; k < base.size() - view[ superHeight - numerator.getHeight() + j].size() + k + center; k += 1 ) {
			view[ superHeight - numerator.getHeight() + j ] += " ";
		}
		view[ superHeight - numerator.getHeight() + j] += numerator.view[j];
	}

	if ( denominator.getHeight() > subHeight ) subHeight = denominator.getHeight();

	while ( view.size() - 1 - superHeight < subHeight ) {
		view.push_back( "" );
	}

	center = ( maxLen - denominator.view[ denominator.superHeight ].size() ) / 2;
	for ( int j = denominator.view.size() ; j > 0; j -= 1 ) {
		for ( int k = 0; k < view[ superHeight ].size() - view[ superHeight + j ].size() + k + center; k += 1 ) {
			view[ superHeight + j ] += " ";
		}
		view[ superHeight + j ] += denominator.view[ j - 1 ];
	}


	for ( int j = 0; j < maxLen; j += 1 ) {
		view[ superHeight ] += "-";
	}

	return i;
}

Component::Component( string comp ) : superHeight( 0 ), subHeight( 0 ) {
	view.push_back( "" );

	char token;
	int numBraces;
	string val;
	for ( int i = 0; i < comp.size(); i += 1 ) {
		token = comp[i];
		switch( token ) {
		  case '\\':
			if ( comp[ i + 1 ] == 'f' ) i = frac( comp, i + 6 );
			if ( comp[ i + 1 ] == 's' ) i = sqrt( comp, i + 6 );
			if ( comp[ i + 1 ] == 'r' ) i = sqrt( comp, i + 6 );
			break;

		  case '_':
		  {
			i += 2;
			val = parse( i, comp );

			Component sub( val );
			if ( sub.getHeight() > subHeight ) subHeight = sub.getHeight();
			
			while ( view.size() - 1 - superHeight < subHeight ) {
				view.push_back( "" );
			}

			for ( int j = sub.view.size() ; j > 0; j -= 1 ) {
				for ( int k = 0; k < view[ superHeight ].size() - view[ superHeight + j ].size() + k; k += 1 ) {
					view[ superHeight + j ] += " ";
				}
				view[ superHeight + j ] += sub.view[ j - 1 ];
			}

			if ( comp[ i + 1 ] != '^' ) {
				for ( int j = 0; j < sub.view[0].size(); j += 1 ) {
					view[ superHeight ] += " ";
				}
			}
		  }
			break;

		  case '^':
		  {
			i += 2;
			val = parse( i, comp );

			Component exp( val );
			string base = view[ superHeight ];
			if ( exp.getHeight() > superHeight ) superHeight = exp.getHeight();
	
			while ( view.size() - 1 - subHeight < superHeight ) {
				view.insert( view.begin(), "" );
			}

			for ( int j = 0; j < exp.view.size(); j += 1 ) {
				for ( int k = 0; k < base.size() - view[j].size() + k; k += 1 ) {
					view[j] += " ";
				}
				view[j] += exp.view[j];
			}

			for ( int j = 0; j < exp.view[0].size(); j += 1 ) {
				view[ superHeight ] += " ";
			}
		  }
			break;

		  default:
			val = "";
			while ( token != '\\' && token != '^' && token != '_' ) {
				val += token;
				i += 1;
				if ( i == comp.size() ) break;
				token = comp[i];
			}
			view[ superHeight ] += val;
			i -= 1;
		}
	}
}

int main() {
	string equation;
	getline( cin, equation );

	Component eq( equation );
	for ( int i = 0; i < eq.view.size(); i += 1 ) {
		cout << eq.view[i] << endl;
	}
}
