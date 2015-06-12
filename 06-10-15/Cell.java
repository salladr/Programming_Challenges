import java.util.Comparator;

public class Cell {
	int x;
	int y;

	Cell( int x, int y ) {
		this.x = x;
		this.y = y;
	}

	Cell( Cell c ) {
		x = c.getX();
		y = c.getY();
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void normalize( int minX, int minY ) {
		x = x - minX;
		y = y - minY;
	}

	public void reflect() {
		x = x * -1;
	}

	public void rotate() {
		int temp = x;
		x = y;
		y = temp * -1;
	}

	public static Comparator< Cell > CellComparator = new Comparator< Cell >() {
		public int compare( Cell c1, Cell c2 ) {
			int xResult = c1.getX() - c2.getX();
			if ( xResult == 0 ) return c1.getY() - c2.getY();
			return xResult;
		}
	};

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

	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
