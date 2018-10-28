/**
 * Classe Risorsa Audio
 * 
 * Classe che si occupa di tenere in memoria una risorsa audio.
 */
package tetris.resourceLoader;

// Java Imports
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * @author Franco
 *
 */
public class AudioResource extends Resource {
	private MediaPlayer mediaPlayer;
	
	private final Media sound;
	
	public AudioResource(String path) {
		super(path);
		sound = new Media(file.toURI().toString());
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
	
}
