import java.util.*;

public class Group {
	char colour;
	List< Cell > cells;
	Set< Cell > liberties;

	Group( char colour, int x, int y, ArrayList< String > grid ) {
		this.colour = colour;

		cells = new LinkedList< Cell >();
		cells.add( new Cell( x, y ) );
		String row = grid.get( y ).substring( 0, x ) + '-' + grid.get( y ).substring( x + 1 );
		grid.set( y, row );

		liberties = new HashSet< Cell >();

		
		for ( ListIterator< Cell > it = cells.listIterator(); it.hasNext(); ) {
			Cell currentCell = it.next();
			int currentX = currentCell.getX();
			int currentY = currentCell.getY();

			// check the cell to the left
			if ( currentX != 0 ) {
				if ( grid.get( currentY ).charAt( currentX - 1 ) == colour ) {
					it.add( new Cell( currentX - 1, currentY ) );
					it.previous();

					String newRow = grid.get( currentY ).substring( 0, currentX - 1 ) + '-' +
							grid.get( currentY ).substring( currentX );

					grid.set( currentY, newRow );

				} else if ( grid.get( currentY ).charAt( currentX - 1 ) == ' ' ) {
					liberties.add( new Cell( currentX - 1, currentY ) );
				}
			}

			// check the cell to the right
			if ( currentX != grid.get( currentY ).length() - 1 ) {
				if ( grid.get( currentY ).charAt( currentX + 1 ) == colour ) {
					it.add( new Cell( currentX + 1, currentY ) );
					it.previous();

					String newRow = grid.get( currentY ).substring( 0, currentX + 1 ) + '-' +
							grid.get( currentY ).substring( currentX + 2 );

					grid.set( currentY, newRow );

				} else if ( grid.get( currentY ).charAt( currentX + 1 ) == ' ' ) {
					liberties.add( new Cell( currentX + 1, currentY ) );
				}
			}

			// check the cell above
			if ( currentY != 0 ) {
				if ( grid.get( currentY - 1 ).charAt( currentX ) == colour ) {
					it.add( new Cell( currentX, currentY - 1 ) );
					it.previous();

					String newRow = grid.get( currentY - 1 ).substring( 0, currentX ) + '-' +
							grid.get( currentY - 1 ).substring( currentX + 1 );

					grid.set( currentY - 1, newRow );

				} else if ( grid.get( currentY - 1 ).charAt( currentX ) == ' ' ) {
					liberties.add( new Cell( currentX, currentY - 1 ) );
				}
			}

			// check the cell below
			if ( currentY != grid.size() - 1 ) {
				if ( grid.get( currentY + 1 ).charAt( currentX ) == colour ) {
					it.add( new Cell( currentX, currentY + 1 ) );
					it.previous();

					String newRow = grid.get( currentY + 1 ).substring( 0, currentX ) + '-' +
							grid.get( currentY + 1 ).substring( currentX + 1 );

					grid.set( currentY + 1, newRow );

				} else if ( grid.get( currentY + 1 ).charAt( currentX ) == ' ' ) {
					liberties.add( new Cell( currentX, currentY + 1 ) );
				}
			}
		}
	}

	public int numLibs() {
		return liberties.size();
	}

	public int numCells() {
		return cells.size();
	}

	public Cell getLib() {
		Iterator< Cell > it = liberties.iterator();
		return it.next();
	}

	public void printStats() {
		System.out.print( "Cells: " );
		for ( Cell c : cells ) {
			System.out.print( "(" + c.getX() + ", " + c.getY() + "), " );
		}
		System.out.println();

		System.out.print( "Libs: " );
		for ( Cell c : liberties ) {
			System.out.print( "(" + c.getX() + ", " + c.getY() + "), " );
		}
		System.out.println();
	}
}
