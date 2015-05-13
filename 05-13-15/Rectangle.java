public class Rectangle {
	int colour;
	int x, y;
	int width, height;

	public Rectangle( int C, int X, int Y, int W, int H ) {
		colour = C;
		x = X;
		y = Y;
		width = W;
		height = H;
	}

	public boolean onCell( int cellX, int cellY ) {
		if ( cellX >= x && cellX < ( x + width ) &&
		     cellY >= y && cellY < ( y + height ) ) return true;
		return false;
	}
}
