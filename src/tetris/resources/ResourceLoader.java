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

		// Caricamento Audio
		this.paths.add(new String[]{"myAudio", "./assets/audio/Tetris.wav", "audio", "32", "32"}); 								
		
		// Caricamento immagini
		this.paths.add(new String[]{"myWallpaper", "./assets/img/Game_Wallpaper.png","img","700","700"});
		this.paths.add(new String[]{"myImage", "./assets/img/Base_Elements.png", "img", "224", "32"});
		this.paths.add(new String[]{"MenuWallpaper", "./assets/img/Menu_Wallpaper.png","img","700","700"});
		this.paths.add(new String[]{"OptionsWallpaper", "./assets/img/Options_Wallpaper.png","img","700","700"});
		this.paths.add(new String[]{"LeaderboardWallpaper", "./assets/img/Leaderboard_Wallpaper.png","img","700","700"});
		this.paths.add(new String[]{"0", "./assets/img/tetramino_0.png","img","64","64"});
		this.paths.add(new String[]{"1", "./assets/img/tetramino_1.png","img","96","64"});
		this.paths.add(new String[]{"2", "./assets/img/tetramino_2.png","img","96","64"});
		this.paths.add(new String[]{"3", "./assets/img/tetramino_3.png","img","96","64"});
		this.paths.add(new String[]{"4", "./assets/img/tetramino_4.png","img","96","64"});
		this.paths.add(new String[]{"5", "./assets/img/tetramino_5.png","img","96","64"});
		this.paths.add(new String[]{"6", "./assets/img/tetramino_6.png","img","128","64"});
		
		// Caricamento spriteSheet
		this.paths.add(new String[]{"mySpriteSheet", "./assets/img/Base_Elements.png", "spriteSheet", "32", "32", "7", "1", "7"}); 	
		this.paths.add(new String[]{"myButton", "./assets/img/Button_menu.png", "spriteSheet", "150", "50", "2", "1", "2"});
		this.paths.add(new String[]{"myButton2", "./assets/img/Button_punteggi.png", "spriteSheet", "150", "50", "2", "1", "2"});
		this.paths.add(new String[]{"myButton3", "./assets/img/Button_gioca.png", "spriteSheet", "300", "75", "2", "1", "2"});
		this.paths.add(new String[]{"myButton4", "./assets/img/Button_impostazioni.png", "spriteSheet", "300", "75", "2", "1", "2"});
		this.paths.add(new String[]{"myButton5", "./assets/img/Menu_Button_punteggi.png", "spriteSheet", "300", "75", "2", "1", "2"});
		this.paths.add(new String[]{"myButton6", "./assets/img/difficolta_button.png", "spriteSheet", "300", "75", "2", "1", "2"});
		this.paths.add(new String[]{"myButton7", "./assets/img/volume_button.png", "spriteSheet", "300", "75", "2", "1", "2"});
		this.paths.add(new String[]{"myButton8", "./assets/img/regole_button.png", "spriteSheet", "300", "75", "2", "1", "2"});
		this.paths.add(new String[]{"myButton9", "./assets/img/options_menu_button.png", "spriteSheet", "300", "75", "2", "1", "2"});
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
