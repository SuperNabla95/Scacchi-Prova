
public class Pezzo{

	final static char RE = 'R';
	final static char DONNA = 'D';
	final static char ALFIERE = 'A';
	final static char CAVALLO = 'C';
	final static char TORRE = 'T';
	final static char PEDONE = 'P';

	final private char tipo;
	final private char colore;

	public Pezzo ( char t, char c ){
		this.tipo = t;
		this.colore = c;
	}

	public char getTipo(){
		return this.tipo;
	}
	public char getColore(){
		return this.colore;
	}

	@Override
	public String toString(){
		return "(" + this.tipo + "," + this.colore + ")";
	}

}