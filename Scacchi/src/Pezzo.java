public final class Pezzo{

	final static char RE = 'R';
	final static char DONNA = 'D';
	final static char ALFIERE = 'A';
	final static char CAVALLO = 'C';
	final static char TORRE = 'T';
	final static char PEDONE = 'P';
	
	private static final int bitvector = 0xA800D;

	private final char tipo, colore;

	public Pezzo (char t, char c) throws NotValidTypeException{
		if(!tipoValido(t))
			throw new NotValidTypeException(c + " non è tra i tipi [R, D, A, C, T, P]"
					+" validi per un pezzo");
		this.tipo = t;
		this.colore = c;
	}
	
	private static boolean tipoValido(char t) {
		int i = t - 'A';
		return i>=0 && ((bitvector >>> i) & 1) == 1;
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
	
	public final static class NotValidTypeException extends Exception{
		private static final long serialVersionUID = 0xBADACAD0;
		private NotValidTypeException(String message) {
			super(message);
		}
	}

}