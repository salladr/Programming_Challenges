import java.util.Scanner;
import java.io.*;

public class AnimalGame {
	public static void main( String[] args ) {
		AnimalGame game = new AnimalGame();

		AnimalNode root = game.loadTree();
		AnimalNode node = root;

		Scanner in = new Scanner( System.in );
		String answer;

		while ( true ) {
			System.out.println( "Welcome to Animal Guess. Please think of an Animal and type 'y' to proceed -->" );
			answer = in.nextLine();
			System.out.println( node.value );

			game.saveTree( root );
			break;
		}
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
