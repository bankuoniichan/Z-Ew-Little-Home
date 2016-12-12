package utility;

import javafx.scene.media.AudioClip;

public class AudioUtility {
	public static AudioClip[] multi = new AudioClip[4];
	public static AudioClip[] end = new AudioClip[5];
	public static AudioClip[] playing = new AudioClip[5];
	public static AudioClip[] wait = new AudioClip[6];
	static {
		try {
			// multicast <--> merge
			multi[0] = new AudioClip(ClassLoader.getSystemResource("sound/ping.wav").toString());
			multi[1] = new AudioClip(ClassLoader.getSystemResource("sound/Fireblast_x2.wav").toString());
			multi[2] = new AudioClip(ClassLoader.getSystemResource("sound/Fireblast_x3.wav").toString());
			multi[3] = new AudioClip(ClassLoader.getSystemResource("sound/Fireblast_x4.wav").toString());
			
			// end <--> game end
			end[0] = new AudioClip(ClassLoader.getSystemResource("sound/end_1.wav").toString());
			end[1] = new AudioClip(ClassLoader.getSystemResource("sound/end_2.wav").toString());
			end[2] = new AudioClip(ClassLoader.getSystemResource("sound/end_3.wav").toString());
			end[3] = new AudioClip(ClassLoader.getSystemResource("sound/end_4.wav").toString());
			end[4] = new AudioClip(ClassLoader.getSystemResource("sound/end_5.wav").toString());
			
			// playing <--> !game end
			playing[0] = new AudioClip(ClassLoader.getSystemResource("sound/playing_1.wav").toString());
			playing[1] = new AudioClip(ClassLoader.getSystemResource("sound/playing_2.wav").toString());
			playing[2] = new AudioClip(ClassLoader.getSystemResource("sound/playing_3.wav").toString());
			playing[3] = new AudioClip(ClassLoader.getSystemResource("sound/playing_4.wav").toString());
			playing[4] = new AudioClip(ClassLoader.getSystemResource("sound/playing_5.wav").toString());
			
			// wait <--> game setting
			wait[0] = new AudioClip(ClassLoader.getSystemResource("sound/wait_1.wav").toString());
			wait[1] = new AudioClip(ClassLoader.getSystemResource("sound/wait_2.wav").toString());
			wait[2] = new AudioClip(ClassLoader.getSystemResource("sound/wait_3.wav").toString());
			wait[3] = new AudioClip(ClassLoader.getSystemResource("sound/wait_4.wav").toString());
			wait[4] = new AudioClip(ClassLoader.getSystemResource("sound/wait_5.wav").toString());
			wait[5] = new AudioClip(ClassLoader.getSystemResource("sound/wait_6.wav").toString());
		} catch (Exception e) {
		}
	}

	public static void playMulti(int value) {
		AudioUtility.multi[value - 1].play();
	}
	
	public static void playEnd(int value) {
		AudioUtility.end[value - 1].play();
	}
	
	public static void playPlaying(int value) {
		AudioUtility.playing[value - 1].play();
	}
	
	public static void playWait(int value) {
		AudioUtility.wait[value - 1].play();
	}
	
}