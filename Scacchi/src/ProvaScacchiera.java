import fond.io.*;
import java.util.Arrays;

public class ProvaScacchiera{
	public static void main(String[] args){
		InputWindow in = new InputWindow();
		OutputWindow out = new OutputWindow("Testing Scacchiera");

		Scacchiera myScacchiera = new Scacchiera();

		int scelta;

		while ((scelta=in.readInt("1) AGGIUNGI PEZZO\n0) termina"))!= 0){

			if ( scelta == 1 ){
				char tipo =  in.readChar("inserisci il tipo");
				char colore =  in.readChar("Inserisci il colore");

				Pezzo p = new Pezzo(tipo, colore);
				int i = in.readInt("inserisci la riga");
				int j = in.readInt("inserisci la colonna");

				boolean isInserito = myScacchiera.aggiungiPezzo (i,j,p);
				if(isInserito)
					out.writeln("Pezzo " + p + " inserito in posizione ("+i+","+j+")");
				else
					out.writeln("Posizionamento fallito");
			}
		}
		
		out.writeln("La scacchiera popolata:");
		out.writeln(myScacchiera);
		
		while ((scelta=in.readInt("1) CONTROLLA MINACCIATO\n0) termina"))!= 0){

			if ( scelta == 1 ){
				int i = in.readInt("inserisci la riga");
				int j = in.readInt("inserisci la colonna");
				char colore =  in.readChar("Inserisci il colore");
				boolean minacciato = myScacchiera.minacciatoDaCavallo(i, j, colore);
				if(minacciato)
					out.writeln("Pezzo in ("+i+","+j+") minacciato da cavallo "+colore);
				else
					out.writeln("Pezzo in ("+i+","+j+") al sicuro da cavallo "+colore);
			}
		}
		
		char c = in.readChar("Inserisci il colore");
		out.writeln("Ecco la lista dei pezzi minacciati da cavalli di colore " + c + ":");
		out.writeln(Arrays.toString(myScacchiera.minacciatiDaCavallo(c)));
	}
}