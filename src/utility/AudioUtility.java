package utility;

import javafx.scene.media.AudioClip;

public class AudioUtility {
	public static AudioClip[] multi = new AudioClip[4];
	static {
		try {
			multi[0] = new AudioClip(ClassLoader.getSystemResource("sound/Fireblast_x1.wav").toString());
			multi[1] = new AudioClip(ClassLoader.getSystemResource("sound/Fireblast_x2.wav").toString());
			multi[2] = new AudioClip(ClassLoader.getSystemResource("sound/Fireblast_x3.wav").toString());
			multi[3] = new AudioClip(ClassLoader.getSystemResource("sound/Fireblast_x4.wav").toString());
		} catch (Exception e) {
		}
	}

	public static void playMulti(int multi) {
		AudioUtility.multi[multi - 1].play();
	}
}
