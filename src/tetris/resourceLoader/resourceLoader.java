/**
 * Classe ResourceLoader
 * 
 * Classe responsabile del caricamento e dell'imagazinamento delle risorse,
 * tale classe è un singleton.
 */
package tetris.resourceLoader;

// Java imports
import java.util.List;
import java.util.Hashtable;

/**
 * @author Franco
 *
 */
public class resourceLoader {
	
	/**
	 * Istanza unica della classe resourceloader
	 */
	private static resourceLoader INSTANCE;
	
	/**
	 * HashTable nella quale sono contenute le risorse caricate, accessibile
	 * con il metodo getResource.
	 */
	private Hashtable<String, Resource> resources = new Hashtable<>();
	
	/**
	 * Lista che contine informazioni sulla risorsa da caricare.
	 */
	private List<String[]> paths;
	
	/**
	 * Variabile booleana per evitar che le risorse vengano caricate due volte.
	 */
	private boolean loaded = false;
	
	/**
	 * Costruttore privato del signleton
	 */
	private resourceLoader() {
		// Ripetere per ogni risorsa da caricare
		paths.add(new String[]{"<Nome>", "<Percorso di una risorsa>", "<audio,img>", "<imgW>", "<imgH>"});
	}
	
	/**
	 * Inizializzatore per l'unica istanza di resource loader che puo 
	 * essere creata.
	 * 
	 * @return l'unica istanza di resourceLoader
	 */
	public resourceLoader getInstance(){
		if (INSTANCE == null) {
			INSTANCE = new resourceLoader();
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
					default:
						throw new Exception("File type is not Handled: " + path[2]);
				}
			}
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
