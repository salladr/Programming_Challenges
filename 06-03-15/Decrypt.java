import java.util.*;

public class Decrypt {
	private String omicronV( String encoded ) {
		StringBuilder decoded = new StringBuilder();
		for ( char c : encoded.toCharArray() ) {
			decoded.append( ( char ) ( c ^  16 ) );
		}
		return decoded.toString();
	}

	private String hoth( String encoded ) {
		StringBuilder decoded = new StringBuilder();
		for ( char c : encoded.toCharArray() ) {
			decoded.append( ( char ) ( c - 10 ) );
		}
		return decoded.toString();
	}

	private String ryzaIV( String encoded ) {
		StringBuilder decoded = new StringBuilder();
		for ( char c : encoded.toCharArray() ) {
			decoded.append( ( char ) ( c + 1 ) );
		}
		return decoded.toString();
	}

	private String htrae( String encoded ) {
		StringBuilder decoded = new StringBuilder( encoded ).reverse();
		return decoded.toString();
	}

	// In order to determine which message is correct, count the number of english letters and spaces in each message
	// the message with the most will most likely be the correct answer
	private int countCharacters( String message ) {
		int count = 0;
		for ( char c : message.toCharArray() ) {
			if ( "abcdefghijklmnopqrstuvwxyz ".contains( Character.toString( Character.toLowerCase(c) ) ) ) count += 1;
		}
		return count;
	}

	public static void main( String[] args ) {
		Decrypt d = new Decrypt();
		Scanner in = new Scanner( System.in );
		String input;

		while ( in.hasNextLine() ) {
			input = in.nextLine().replaceAll( "\"", "" );
			Scanner values = new Scanner( input );
			StringBuilder encoded = new StringBuilder();
			while ( values.hasNext() ) {
				encoded.append( ( char ) values.nextInt() );
			}

			// Decode the message in each language, whichever returns the highest value from countCharacters
			// is the correct decryption.
			int maxCount = 0;
			int count;
			String decoded;
			String message = "";
			String planet = "";

			decoded = d.omicronV( encoded.toString() );
			count = d.countCharacters( decoded );
			if ( count > maxCount ) {
				maxCount = count;
				message = decoded;
				planet = "Omicron V";
			}

			decoded = d.hoth( encoded.toString() );
			count = d.countCharacters( decoded );
			if ( count > maxCount ) {
				maxCount = count;
				message = decoded;
				planet = "Hoth";
			}

			decoded = d.ryzaIV( encoded.toString() );
			count = d.countCharacters( decoded );
			if ( count > maxCount ) {
				maxCount = count;
				message = decoded;
				planet = "Ryza IV";
			}

			decoded = d.htrae( encoded.toString() );
			count = d.countCharacters( decoded );
			if ( count > maxCount ) {
				maxCount = count;
				message = decoded;
				planet = "Htrae";
			}

			System.out.println( planet + ": " + message );
		}
	}
}
