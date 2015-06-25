import java.util.*;

public class GoTime {
	public static void main( String args[] ) {
		Scanner in = new Scanner( System.in );

		int width = in.nextInt();
		int height = in.nextInt();
		char player = in.next().charAt( 0 );
		char opponent = player == 'b' ? 'w' : 'b';

		in.nextLine();
		ArrayList< String > grid = new ArrayList< String >();		
		for ( int i = 0; i < height; i += 1 ) {
			grid.add( in.nextLine() );
		}

		ArrayList< Group > groups = new ArrayList< Group >();
		for ( int i = 0; i < height; i += 1 ) {
			while ( true ) {
				String row = grid.get(i);
				int y = i;
				int x = row.indexOf( opponent );
				if ( x == -1 ) break;

				Group g = new Group( opponent, x, y, grid );
				groups.add( g );
			}
		}

		int maxRemove = 0;
		Cell position = null;
		for ( Group g : groups ) {
			if ( g.numLibs() != 1 ) continue;
			if ( g.numCells() < maxRemove ) continue;

			maxRemove = g.numCells();
			position = g.getLib();
		}

		if ( maxRemove == 0 )
			System.out.println( "No constructive move" );
		else
			System.out.println( "(" + position.getX() + ", " + position.getY() + ")" );

/*		for ( String row : grid ) {
			System.out.println( row );
		}

		for ( Group g : groups ) {
			g.printStats();
		}*/
	}
}
