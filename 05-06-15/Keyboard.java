public class Keyboard {

	private char[][] keyboard;

	public Keyboard() {
		keyboard = new char[][] {
			{ 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p' },
			{ 'a', 's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', '-' },
			{ '^', 'z', 'x', 'c', 'v', 'b', 'n', 'm', '-', '^' },
			{ '-', '-', '-', '#', '#', '#', '#', '#', '-', '-' } };
	}

	public Position letterPos( char l ) {
		Position ret = new Position();
		int row;

		if ( "qwertyuiop".contains( l + "" ) ) row = 0;
		else if ( "asdfghjkl".contains( l + "" ) ) row = 1;
		else row = 2;

		for ( int i = 0; i < keyboard[row].length; i += 1 ) {
			if ( keyboard[row][i] == l ) {
				ret.x = row;
				ret.y = i;
				ret.letter = Character.toString( Character.toUpperCase(l) );
				break;
			}
		}

		return ret;
	}

	// returns the position of the shift '^' closest to the given position
	public Position shiftPos( Position p ) {
		Position shift1 = new Position( 2, 0, "Shift" );
		Position shift2 = new Position( 2, 9, "Shift" );

		if ( p.x == -1 ) return shift1;

		int effort1 = Math.abs( shift1.x - p.x ) + Math.abs( shift1.y - p.y );
		int effort2 = Math.abs( shift2.x - p.x ) + Math.abs( shift2.y - p.y );

		if ( effort1 < effort2 ) return shift1;
		return shift2;
	}

	// returns the position of the space '#' closest to the given position
	public Position spacePos( Position p ) {
		Position[] positions = new Position[5];
		int[] effort = new int[5];
		int min = 0;
		int index = 0;

		for ( int i = 0; i < 5; i += 1 ) {
			positions[i] = new Position( 3, i + 3, "Space" );
			if ( p.x == -1 ) return positions[i];
			effort[i] = Math.abs( positions[i].x - p.x ) + Math.abs( positions[i].y - p.y );
			if ( i == 0 ) min = effort[i];
			if ( effort[i] < min ) {
				min = effort[i];
				index = i;
			}
		}

		return positions[ index ];
	}
}
