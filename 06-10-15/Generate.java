import java.util.*;

public class Generate {
	public static void main( String args[] ) {
		Generate g = new Generate();

		Scanner in = new Scanner( System.in );
		int order = in.nextInt();
		Set< Polyomino > polyominoes = g.getPolyominoes( order );
		for ( Polyomino p : polyominoes ) {
			p.print();
		}
	}

	public Set< Polyomino > getPolyominoes( int order ) {
		Set< Polyomino > polyominoes = new HashSet< Polyomino >();

		if ( order == 1 ) {
			polyominoes.add( new Polyomino() );
		} else {
			Set< Polyomino > lessOnePolys = getPolyominoes( order - 1 );
			for ( Polyomino p : lessOnePolys ) {
				ArrayList< Cell > possibleCells = p.findCells();
				for ( Cell c : possibleCells ) {
					ArrayList< Cell > cells = p.getCells();
					cells.add( c );
					Polyomino checkPoly = new Polyomino( cells );
					polyominoes.add( checkPoly );
				}
			}
		}

		return polyominoes;
	}
}
