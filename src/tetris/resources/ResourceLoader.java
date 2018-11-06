/**
 * Classe ResourceLoader
 * 
 * Classe responsabile del caricamento e dell'imagazinamento delle risorse,
 * tale classe � un singleton.
 */
package tetris.resources;

// Java imports
import java.util.ArrayList;
import java.util.Hashtable;

//Same Package imports
import tetris.resources.Resource;

/**
 * @author Franco
 *
 */
public class ResourceLoader {
	
	/**
	 * Istanza unica della classe resourceloader
	 */
	private static ResourceLoader INSTANCE;
	
	/**
	 * HashTable nella quale sono contenute le risorse caricate, accessibile
	 * con il metodo getResource.
	 */
	public Hashtable<String, Resource> resources = new Hashtable<>();
	
	/**
	 * Lista che contine informazioni sulla risorsa da caricare.
	 */
	private ArrayList<String[]> paths = new ArrayList<>();
	
	/**
	 * Variabile booleana per evitar che le risorse vengano caricate due volte.
	 */
	private boolean loaded = false;
	
	/**
	 * Costruttore privato del signleton
	 */
	private ResourceLoader() {
		
		// Comando do usare in queste righe: paths.add(new String[]{"<Nome>", "<Percorso di una risorsa>", "<audio,img>", "<imgW>", "<imgH>"});
		// Ripetere per ogni risorsa da caricare
		this.paths.add(new String[]{"myImage", "./assets/img/sprites.png", "img", "224", "32"}); 								// RIGA ESEMPIO
		this.paths.add(new String[]{"myAudio", "./assets/audio/Tetris.mp3", "audio", "32", "32"}); 								// RIGA ESEMPIO
		this.paths.add(new String[]{"mySpriteSheet", "./assets/img/sprites.png", "spriteSheet", "32", "32", "7", "1", "7"}); 	// RIGA ESEMPIO
	}
	
	/**
	 * Inizializzatore per l'unica istanza di resource loader che puo 
	 * essere creata.
	 * 
	 * @return l'unica istanza di resourceLoader
	 */
	public static ResourceLoader getInstance(){
		if (INSTANCE == null) {
			INSTANCE = new ResourceLoader();
		}
		return INSTANCE;
	}
	
	/**
	 * Quando questo metodo viene chiamato inizia il caricamento delle risorse.
	 * 
	 * @throws Exception
	 */
	public void loadResources() throws Exception {
		if(!loaded) {
			Resource temp;
			for (String[] path : paths) {
				switch (path[2]) {
					case "img":
						temp = new ImageResource(Integer.parseInt(path[3]), Integer.parseInt(path[4]), path[1]);
						resources.put(path[0], temp);
						break;
					case "audio":
						temp = new AudioResource(path[1]);
						resources.put(path[0], temp);
						break;
					case "spriteSheet":
						temp = new SpriteSheetResource(path[1], Integer.parseInt(path[3]), Integer.parseInt(path[4]), Integer.parseInt(path[5]), Integer.parseInt(path[6]), Integer.parseInt(path[7]));
						resources.put(path[0], temp);
						break;
					default:
						throw new Exception("File type is not Handled: " + path[2]);
				}
			}
			
			// Previene un secondo caricamento di risorse.
			loaded = true;
		}
	}
	
	/**
	 * Getter per la risorsa indicizzata con il nome passato. E' neccessario usare il casting,
	 * con (AudioResource) o (ImageResource) per accedere a tutti i metodi che resource non
	 * possiede.
	 * 
	 * @param nome della risorsa da ottenere.
	 * @return la risorsa indicizzata con il nome passato.
	 */
	public Resource getResource(String name) {
		return resources.get(name);
	}
}