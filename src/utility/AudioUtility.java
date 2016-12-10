package utility;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioUtility {
	public static MediaPlayer[] multi = new MediaPlayer[3];
	static {
		try {
			multi[0] = new MediaPlayer(new Media(ClassLoader.getSystemResource("sound/Fireblast_x1.mp3").toString()));
			multi[1] = new MediaPlayer(new Media(ClassLoader.getSystemResource("sound/Fireblast_x2.mp3").toString()));
			multi[2] = new MediaPlayer(new Media(ClassLoader.getSystemResource("sound/Fireblast_x3.mp3").toString()));
			multi[3] = new MediaPlayer(new Media(ClassLoader.getSystemResource("sound/Fireblast_x4.mp3").toString()));
		} catch (Exception e) {
		}
	}

	public static void playMulti(int multi) {
		//AudioUtility.multi[multi - 1].play();
		new MediaPlayer(new Media(ClassLoader.getSystemResource("sound/Fireblast_x"+multi+".mp3").toString())).play();
	}
}
