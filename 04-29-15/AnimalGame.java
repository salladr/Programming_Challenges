import java.util.Scanner;
import java.io.*;

public class AnimalGame {
	public static void main( String[] args ) {
		AnimalGame game = new AnimalGame();

		AnimalNode root = game.loadTree();
		AnimalNode node = root;

		Scanner in = new Scanner( System.in );
		String answer;

		System.out.print( "Welcome to Animal Guess. Please think of an Animal and press enter to proceed " );
		answer = in.nextLine();
		System.out.println();

		while ( node.yes != null  ) {
			System.out.print( node.value );
			System.out.print( " --> " );
			answer = in.nextLine();
			if ( Character.toLowerCase( answer.charAt(0) ) == 'y' ) {
				node = node.yes;
			} else {
				node = node.no;
			}
		}

		System.out.print( "I think your animal is a " + node.value + ". Was I right? --> " );
		answer = in.nextLine();
		System.out.println();
		if ( Character.toLowerCase( answer.charAt(0) ) == 'y' ) {
			System.out.println( "It is okay to feel bad you did not stump me. I am a computer. :)" );
			System.out.println( "Thanks for playing!" );
		} else {
			System.out.println( "Oh well. please help me learn." );
			System.out.print( "What is the name of your animal? --> " );
			String animal = in.nextLine();
			System.out.print( "What is a unique question that answers yes for " + animal + "? --> " );
			String question = in.nextLine();
			System.out.println();
			System.out.println( "Thank you for teaching me." );

			node.no = new AnimalNode( node.value );
			node.yes = new AnimalNode( animal );
			node.value = question;
		}

		game.saveTree( root );
	}

	public AnimalNode loadTree() {
		AnimalNode ret;

		try {
			FileInputStream fin = new FileInputStream( "AnimalTree.ser" );
			ObjectInputStream ois = new ObjectInputStream( fin );
			ret = ( AnimalNode ) ois.readObject();
			ois.close();
			return ret;

		} catch ( EOFException eof ) {
			ret = new AnimalNode( "Is your animal a mammal?" );
			ret.yes = new AnimalNode( "whale" );
			ret.no = new AnimalNode( "snake" );
			return ret;

		} catch ( Exception ex ) {
			ex.printStackTrace();
			return null;
		}
	}

	public void saveTree( AnimalNode node ) {
		try {
			FileOutputStream fout = new FileOutputStream( "AnimalTree.ser" );
			ObjectOutputStream oos = new ObjectOutputStream( fout );
			oos.writeObject( node );
			oos.close();

		} catch ( Exception ex ) {
			ex.printStackTrace();
		}
	}
}
