public class Position {

	int x, y;
	String letter;

	public Position() {
		x = -1;
		y = -1;
	}

	public Position( int initX, int initY, String s ) {
		x = initX;
		y = initY;
		letter = s;
	}
}
