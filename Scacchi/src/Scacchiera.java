import java.util.ArrayList;
import java.util.List;

public class Scacchiera{

	private static final int[][] offsetsRosaDiCavallo = new int[][]{
			new int[]{-2,-1},
			new int[]{-2,+1},
			new int[]{-1,+2},
			new int[]{+1,+2},
			new int[]{+2,+1},
			new int[]{+2,-1},
			new int[]{+1,-2},
			new int[]{-1,-2}
		};

	private final Pezzo[][] scacchiera;

	/* crea una scacchiera vuota, cioè senza pezzi. */
	public Scacchiera(){
		this.scacchiera = new Pezzo[8][8];
	}

	/* Posiziona il pezzo p in posizione (i,j). Se i e j non sono indici validi la scacchiera non viene
	modificata. Se la posizione (i,j) è già occupata da un altro pezzo la scacchiera non viene modificata.
	Se p è nullo la scacchiera non viene modificata. Il metodo restituisce true se il posizionamento è
	avvenuto con successo, false altrimenti */
	public boolean aggiungiPezzo ( int i, int j, Pezzo p ){
		if(!this.posizioneValida(i,j))
			return false;
		if(p==null)
			return false;
		if(this.scacchiera[i][j]!=null)
			return false;
		this.scacchiera[i][j] = p;
		return true;
	}

	/*restituisce true se la posizione (i,j) esiste nella scacchiera, false altrimenti*/
	private boolean posizioneValida(int i, int j){
		int dim = this.scacchiera.length;
		return i>=0 && j>=0 && i<dim && j<dim;
	}

	/* Restituisce true se il pezzo in posizione (i,j) è minacciato da un cavallo di colore c, false altrimenti.
	Un pezzo è minacciato da un cavallo se è di colore opposto a quello del cavallo e si trova in una delle
	celle su cui il cavallo può portarsi con una mossa. Se in posizione (i,j) non c'è un pezzo viene
	restituito false */
	public boolean minacciatoDaCavallo (int i, int j, char c){
		Pezzo pezzo_ij = this.scacchiera[i][j];
		if(pezzo_ij == null)
			return false;
		char colore_ij = pezzo_ij.getColore();
		for(int[] offset : offsetsRosaDiCavallo){
			int i2,j2;
			i2 = i + offset[0];
			j2 = j + offset[1];
			Character ch = this.coloreDelCavallo(i2,j2);
			if(ch!=null && ch==c && c!=colore_ij)
				return true;
		}
		return false;
	}

	/* restituisce il colore del cavallo in posizione (i,j).
	Se non ci sono cavalli in posizione (i,j) oppure se (i,j) non è una posizione valida: restituisce null */
	private Character coloreDelCavallo(int i, int j){
		if(!this.posizioneValida(i,j))
			return null;
		Pezzo pezzo_ij = this.scacchiera[i][j];
		if(pezzo_ij == null || pezzo_ij.getTipo() != Pezzo.CAVALLO)
			return null;
		return pezzo_ij.getColore();
	}

	/* Restituisce un array di pezzi che sono minacciati da almeno un cavallo di colore c. Se nessun
	pezzo è minacciato da un cavallo di colore c viene restituito un array di dimensione 0. */
	public Pezzo[] minacciatiDaCavallo (char c){
		List<Pezzo> listaMinacciati = new ArrayList<>();
		for (int i = 0; i < this.scacchiera.length; ++i){
			for (int j = 0; j < this.scacchiera[i].length; ++j){
				if(this.minacciatoDaCavallo(i,j,c))
					listaMinacciati.add(this.scacchiera[i][j]);
			}
		}
		return listaMinacciati.toArray(new Pezzo[]{});
	}

	/* Restituisce una rappresentazione testuale della scacchiera. Per ogni cella occupata va indicato il
	tipo e il colore del pezzo che la occupa. */
	@Override
	public String toString(){
		String nessunPezzo = "(_,_)";
		int dim = this.scacchiera.length;
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<dim;i++){
			for(int j=0; j<dim; j++){
				Pezzo pezzo_ij = this.scacchiera[i][j];
				if(pezzo_ij == null)
					sb.append(nessunPezzo);
				else
					sb.append(pezzo_ij);
				sb.append(" ");
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}