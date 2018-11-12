/**
 * Classe Risorsa Audio
 * 
 * Classe che si occupa di tenere in memoria una risorsa audio.
 */
package tetris.resources;

// Java Imports
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

//Same Package imports
import tetris.resources.Resource;


/**
 * @author Franco
 *
 */
public class AudioResource extends Resource {
	/**
	 * MediaPlayer dell'audio, ovvero la classe che permetterà di
	 * far partire il suono
	 */
	private MediaPlayer mediaPlayer;
	
	/**
	 * File audio caricato.
	 */
	private final Media sound;
	
	/**
	 * Rate corrente
	 */
	private double currentRate = 1.0;
	
	/**
	 * Inizialliza la risorsa e la carica nella variabile sound.
	 * 
	 * @param path
	 */
	public AudioResource(String path) {
		super(path);
		
		// Caricamento dell'audio
		sound = new Media(file.toURI().toString());
		
		// Creazione del mediaPlayer
		mediaPlayer = new MediaPlayer(sound);
	}

	/**
	 * Getter per il suono al di fuori di resource loader.
	 * 
	 * @return the sound
	 */
	public Media getSound() {
		return sound;
	}
	
	/**
	 * Metodo per far partire la riporduzione dell'audio.
	 */
	public void play() {
		mediaPlayer.play();
	}
	
	/**
	 * Metodo per interrompere la riproduzione dell'audio.
	 */
	public void stop() {
		mediaPlayer.stop();
	}
	
	/**
	 * Metodo per inserire un loop, alla fine dell'audio viene
	 * "lanciato" un evento, e l'osservatore fà partire la funzione
	 * ogni volta che osserva l'evento lanciato.
	 */
	public void loop() {
		mediaPlayer.setOnEndOfMedia(new Runnable() {
	        @Override
	        public void run() {
	        	// Reimposta il momento di suono a zero
	        	mediaPlayer.seek(Duration.ZERO);
	        	// Fà ripartire l'audio
	        	mediaPlayer.play();
	        }
	    }); 
	}
	
	/**
	 * Funzione per interrompere il loop di un audio,
	 * potrebbe non servire -> rimuovere in tale caso.
	 */
	public void stopLoop() {
		mediaPlayer.setOnEndOfMedia(new Runnable() {
	        @Override
	        public void run() {
	        	// Assegna un runnable vuoto.
	        }
	    }); 
	}
	
	/**
	 * Rende il brano più rapido
	 */
	public void setFeedRate(double d) {
		if(d>8) {
			d=8;
		}
		currentRate = d;
		mediaPlayer.setRate(d);
	}
	
	public double getCurrentrate() {
		return currentRate;
	}
}
