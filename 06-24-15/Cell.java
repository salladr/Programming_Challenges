public class Cell {
	int x;
	int y;

	Cell( int x, int y ) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public int hashCode() {
		return x + y;
	}

	@Override
	public boolean equals( Object obj ) {
		if ( !( obj instanceof Cell ) ) return false;
		if ( obj == this ) return true;

		Cell rhs = ( Cell ) obj;
		if ( x == rhs.x && y == rhs.y ) return true;
		return false;
	}
}
