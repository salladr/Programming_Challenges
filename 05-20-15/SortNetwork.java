import java.util.*;

public class SortNetwork {
	public static void main( String[] args ) {
		SortNetwork sortNetwork = new SortNetwork();
		Scanner in = new Scanner( System.in );

		int numWires = in.nextInt();
		int numComps = in.nextInt();

		ArrayList< Comparator > comparators = new ArrayList< Comparator >();
		int top, bottom;
		for ( int i = 0; i < numComps; i += 1 ) {
			top = in.nextInt();
			bottom = in.nextInt();
			comparators.add( new Comparator( top, bottom ) );
		}

		String input;
		String padding = "%" + numWires + "s";
		for ( int i = 1; i < Math.pow( 2, numWires ) - 1; i += 1 ) {
			input = String.format( padding, Integer.toBinaryString(i) ).replace( ' ', '0' );
			input = sortNetwork.sort( input.toCharArray(), comparators );
			if ( !sortNetwork.sorted( input ) ) {
				System.out.println( "Invalid network" );
				return;
			}
		}
		System.out.println( "Valid network" );
	}

	public String sort( char[] input, ArrayList< Comparator > comparators ) {
		for ( Comparator c : comparators ) {
			int top = input[ c.top ];
			int bottom = input[ c.bottom ];
			input[ c.top ] = ( char ) ( top & bottom );
			input[ c.bottom ] = ( char ) ( top | bottom );
		}
		return new String( input );
	}

	public Boolean sorted( String input ) {
		for ( int i = 0; i < input.length() - 1; i += 1 ) {
			if ( input.charAt(i) > input.charAt( i + 1 ) ) return false;
		}
		return true;
	}
}
