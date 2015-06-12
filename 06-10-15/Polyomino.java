import java.util.*;

public class Polyomino {
	ArrayList< Cell > cells;

	Polyomino( ) {
		cells = new ArrayList< Cell >();
		cells.add( new Cell( 0, 0 ) );
	}

	Polyomino( ArrayList<Cell> newCells ) {
		cells = cloneCells( newCells );
//		System.out.print( "Before anything: " );
//		print();

		// find all the versions of the polyomino
		ArrayList< ArrayList< Cell > > versions = new ArrayList< ArrayList< Cell > >();
		for ( int i = 0; i < 4; i += 1 ) {
			normalize();
//			System.out.print( "After Normalize: " );
//			print();
			versions.add( cloneCells( cells ) );
			reflect();
			normalize();
//			System.out.print( "After Normalize: " );
//			print();
			versions.add( cloneCells( cells ) );
			rotate();
		}

		// choose the minimum version to be the polynomial
		ArrayList< String > strings = new ArrayList< String >();
		for ( ArrayList< Cell > version : versions ) {
			StringBuilder sb = new StringBuilder();
			for ( Cell c : version ) {
				sb.append( c.toString() );
			}
			strings.add( sb.toString() );
		}

//		System.out.print( "versions string: " );
//		for ( String s : strings ) {
//			System.out.println( s );
//		}

		int minIndex = strings.indexOf( Collections.min( strings ) );
//		System.out.println( "min string: " + Collections.min( strings ) + " at: " + minIndex );
		cells = cloneCells( versions.get( minIndex ) );
	}

	private void normalize() {
		int minX = cells.get(0).getX();
		int minY = cells.get(0).getY();

		for ( Cell c : cells ) {
			if ( c.getX() < minX ) minX = c.getX();
			if ( c.getY() < minY ) minY = c.getY();
		}

		for ( Cell c : cells ) {
			c.normalize( minX, minY );
		}

		Collections.sort( cells, Cell.CellComparator );
	}

	private void reflect() {
		for ( Cell c : cells ) {
			c.reflect();
		}
	}

	private void rotate() {
		for ( Cell c : cells ) {
			c.rotate();
		}
	}

	public static ArrayList< Cell > cloneCells( ArrayList< Cell > list ) {
		ArrayList< Cell > clone = new ArrayList< Cell >( list.size() );
		for ( Cell c : list ) {
			clone.add( new Cell( c ) );
		}
		return clone;
	}

	public ArrayList< Cell > getCells() {
		return cloneCells( cells );
	}

	public ArrayList< Cell > findCells() {
		// find the minimum and maximum x and y values
		int minX = cells.get(0).getX();
		int minY = cells.get(0).getY();
		int maxX = cells.get(0).getX();
		int maxY = cells.get(0).getY();

		for ( Cell c : cells ) {
			if ( c.getX() < minX ) 
				minX = c.getX();
			else if ( c.getX() > maxX )
				maxX = c.getX();
			if ( c.getY() < minY )
				minY = c.getY();
			else if ( c.getY() > maxY )
				maxY = c.getY();
		}

		minX -= 1;
		minY -= 1;
		maxX += 1;
		maxY += 1;

		// check all the cells in the grid around the polyomino
		ArrayList< Cell > possibleCells = new ArrayList< Cell >();

		for ( int x = minX; x <= maxX; x += 1 ) {
			for ( int y = minY; y <= maxY; y += 1 ) {
				Cell testCell = new Cell( x, y );

				// check if the cell is connected to the polyomino
				if ( cells.contains( testCell ) ) continue;
				for ( Cell c : cells ) {
					if ( ( Math.abs( c.getX() - testCell.getX() ) +
					       Math.abs( c.getY() - testCell.getY() ) ) == 1 )
						possibleCells.add( testCell );
				}
			}
		}

/*		System.out.print( "Possible Cells: " );
		StringBuilder sb = new StringBuilder();
		for ( Cell c : possibleCells ) {
			sb.append( c.toString() );
		}
		System.out.println( sb.toString() ); */

		return possibleCells;
	}

	public void print() {
		int maxX = cells.get(0).getX();
		int maxY = cells.get(0).getY();

		for ( Cell c : cells ) {
			if ( c.getX() > maxX ) maxX = c.getX();
			if ( c.getY() > maxY ) maxY = c.getY();
		}

		for ( int y = 0; y <= maxY; y += 1 ) {
			for ( int x = 0; x <= maxX; x += 1 ) {
				Cell temp = new Cell( x, y );
				if ( cells.contains( temp ) )
					System.out.print( "#" );
				else
					System.out.print( " " );
			}
			System.out.println();
		}

		StringBuilder sb = new StringBuilder();
		for ( Cell c : cells ) {
			sb.append( c.toString() );
		}
		System.out.println( sb.toString() );
	}

	@Override
	public int hashCode() {
		return cells.get( 0 ).getX();
	}

	@Override
	public boolean equals( Object obj ) {
		if ( !( obj instanceof Polyomino ) ) return false;
		if ( obj == this ) return true;

		Polyomino rhs = ( Polyomino ) obj;
		if ( cells.equals( rhs.cells ) ) return true;
		return false;
	}
}
