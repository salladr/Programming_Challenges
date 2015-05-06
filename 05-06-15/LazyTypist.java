import java.util.Scanner;

public class LazyTypist {
	public static void main( String[] args ) {
		LazyTypist lazy = new LazyTypist();

		Position leftHand = new Position();
		Position rightHand = new Position();

		Scanner in = new Scanner( System.in );
		String text;

		Keyboard keyboard = new Keyboard();

		text = in.nextLine();
		int totalEffort = 0;
		for ( int i = 0; i < text.length(); i += 1 ) {
			char l = text.charAt(i);
			int leftEffort, rightEffort;
			if ( l == ' ' ) {
				Position leftSpace = keyboard.spacePos( leftHand );
				Position rightSpace = keyboard.spacePos( rightHand );

				if ( leftHand.x == -1 ) {
					lazy.pressLetter( leftHand, leftSpace, "left", 0 );
					leftHand = leftSpace;
					continue;
				} else if ( rightHand.x == -1 ) {
					lazy.pressLetter( rightHand, rightSpace, "right", 0 );
					rightHand = rightSpace;
					continue;
				}

				leftEffort = Math.abs( leftHand.x - leftSpace.x ) + Math.abs( leftHand.y - leftSpace.y );
				rightEffort = Math.abs( rightHand.x - rightSpace.x ) + Math.abs( rightHand.y - rightSpace.y );

				if ( leftEffort <= rightEffort ) {
					lazy.pressLetter( leftHand, leftSpace, "left", leftEffort );
					leftHand = leftSpace;
					totalEffort += leftEffort;
				} else {
					lazy.pressLetter( rightHand, rightSpace, "right", rightEffort );
					rightHand = rightSpace;
					totalEffort += rightEffort;
				}

			} else if ( Character.isUpperCase(l) ) {
				Position nextPos = keyboard.letterPos( Character.toLowerCase(l) );
				Position leftShift = keyboard.shiftPos( leftHand );
				Position rightShift = keyboard.shiftPos( rightHand );
		
				if ( leftHand.x == -1 ) {
					lazy.pressLetter( leftHand, leftShift, "left" , 0 );
					lazy.pressLetter( rightHand, nextPos, "right", 0 );
					leftHand = leftShift;
					rightHand = nextPos;
					continue;
				} else if ( rightHand.x == -1 ) {
					lazy.pressLetter( rightHand, rightShift, "right", 0 );
					lazy.pressLetter( leftHand, nextPos, "left", 0 );
					rightHand = rightShift;
					leftHand = nextPos;
					continue;
				}

				leftEffort = Math.abs( leftHand.x - leftShift.x ) + Math.abs( leftHand.y - leftShift.y );
				rightEffort = Math.abs( rightHand.x  - rightShift.x ) + Math.abs( rightHand.y - rightShift.y );

				if ( leftEffort <= rightEffort ) {
					rightEffort = Math.abs( rightHand.x - nextPos.x ) + Math.abs( rightHand.y - nextPos.y );
					lazy.pressLetter( leftHand, leftShift, "left", leftEffort );
					lazy.pressLetter( rightHand, nextPos, "right", rightEffort );
					leftHand = leftShift;
					rightHand = nextPos;
				} else {
					leftEffort = Math.abs( leftHand.x - nextPos.x ) + Math.abs( leftHand.y - nextPos.y );
					lazy.pressLetter( rightHand, rightShift, "right", rightEffort );
					lazy.pressLetter( leftHand, nextPos, "left", leftEffort );
					rightHand = rightShift;
					leftHand = nextPos;
				}
				totalEffort += leftEffort + rightEffort;

			} else {
				Position nextPos = keyboard.letterPos(l);
				if ( leftHand.x == -1 ) {
					lazy.pressLetter( leftHand, nextPos, "left", 0 );
					leftHand = nextPos;
					continue;
				} else if ( rightHand.x == -1 ) {
					lazy.pressLetter( rightHand, nextPos, "right", 0 );
					rightHand = nextPos;
					continue;
				}

				leftEffort = Math.abs( leftHand.x - nextPos.x ) + Math.abs( leftHand.y - nextPos.y );
				rightEffort = Math.abs( rightHand.x - nextPos.x ) + Math.abs( rightHand.y - nextPos.y );

				if ( leftEffort <= rightEffort ) {
					lazy.pressLetter( leftHand, nextPos, "left", leftEffort );
					leftHand = nextPos;
					totalEffort += leftEffort;
				} else {
					lazy.pressLetter( rightHand, nextPos, "right", rightEffort );
					rightHand = nextPos;
					totalEffort += rightEffort;
				}
			}
		}
		System.out.println( "Total effort: " + totalEffort );
	}

	public void pressLetter( Position hand, Position key, String s, int effort ) {
		System.out.print( key.letter );
		if ( hand.x == -1 ) {
			System.out.println( ": Use " + s + " hand" );
		} else if ( effort == 0 ) {
			System.out.println( ": Use " + s + " hand again" );
		} else {
			System.out.print( ": Move " + s + " hand from " + hand.letter );
			System.out.println( " (effort: " + effort + ")" );
		}
	}
}
