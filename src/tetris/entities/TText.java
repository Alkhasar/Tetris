/**
 * 
 */
package tetris.entities;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import tetris.resources.Resource;

/**
 * @author stefan
 *
 */
public class TText extends Sprite {
	
	private Text drawnText = new Text();
	
	/**
	 * @param x posizione iniziale del testo
	 * @param y posizione iniziale del testo
	 * @param Testo che verrà scritto
	 * @param z, dimensione del testo che verrà scritto 
	 */
	public TText(int x, int y, String text, int z) {
		super(x, y, null);
		
		drawnText.setText(text);		
		drawnText.setFont(new Font("Arial Black", z));
		drawnText.setX(x);
		drawnText.setY(y);
		drawnText.setStyle(
				"-fx-fill: #E6EF15;" + 
				"-fx-stroke: #003CE9;" + 
				"-fx-stroke-width: 1px;"
					);		
	}
	
	public void setnewText(String newText){
		drawnText.setText(newText);				
	};

	
	@Override
	public Resource getTexture(){
	return null;	
	};
	
	public Text getText(){
		return drawnText;	
		};
	
	/**
	 * Setter per la posizione x iniziale del pulsante
	 * 
	 * @param coordinata x
	 */
	@Override
	public void setX(int x) {
		this.x = x;
		drawnText.setLayoutX(x);
	}
	
	/**
	 * Setter per la posizione y iniziale del pulsante
	 * 
	 * @param coordinata y
	 */
	@Override
	public void setY(int y) {
		this.y = y;
		drawnText.setLayoutY(y);
	}

}
