import java.util.*;

public class PilesofPaper {
	public static void main( String[] args ) {
		Scanner in = new Scanner( System.in );

		ArrayList< Rectangle > rectangles = new ArrayList< Rectangle >();
		int colour, x, y, width, height;
		Map< Integer, Integer > areas = new TreeMap< Integer, Integer >();

		int maxWidth = in.nextInt();
		int maxHeight = in.nextInt();
		rectangles.add( new Rectangle( 0, 0, 0, maxWidth, maxHeight ) );
		areas.put( 0, 0 );

		while ( in.hasNext() ) {
			colour = in.nextInt();
			x = in.nextInt();
			y = in.nextInt();
			width = in.nextInt();
			height = in.nextInt();
			rectangles.add( new Rectangle( colour, x, y, width, height ) );

			if ( !areas.containsKey( colour ) ) {
				areas.put( colour, 0 );
			}
		}

		for ( int i = 0; i < maxWidth; i += 1 ) {
			for ( int j = 0; j < maxHeight; j += 1 ) {
				for ( int k = rectangles.size() - 1; k >= 0; k -= 1 ) {
					if ( rectangles.get(k).onCell( i, j ) ) {
						areas.put( rectangles.get(k).colour, areas.get( rectangles.get(k).colour ) + 1 );
						break;
					}
				}
			}
		}

		for ( Integer key : areas.keySet() ) {
			System.out.println( key + "	" + areas.get( key ) );
		}
	}
}
