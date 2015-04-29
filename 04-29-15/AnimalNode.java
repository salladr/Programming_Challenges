import java.io.Serializable;

public class AnimalNode implements Serializable {

	String value;
	AnimalNode yes;
	AnimalNode no;

	public AnimalNode( String v ) {
		value = v;
		yes = null;
		no = null;
	}
}
