package edu.brown.cs.voice2text;

import static edu.brown.cs.voice2text.AudioFormatConfiguration.*;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.DataLine.Info;
import javax.sound.sampled.LineUnavailableException;

public class Microphone {
	private AudioFormat audioFormat;
	private TargetDataLine targetDataLine;
	private DataLine.Info targetInfo;
	
	public Microphone() throws Exception {
        // SampleRate:16000Hz, SampleSizeInBits: 16, Number of channels: 1, Signed: true,
        // bigEndian: false
        // TODO some examples set bigEndian as true on Mac. strange
        audioFormat = new AudioFormat(sampleRate, sampleSizeInBits, numberOfChannels, signed, bigEndian);
        targetInfo =
                new Info(
                    TargetDataLine.class,
                    audioFormat);
        // Target data line captures the audio stream the microphone produces.
        targetDataLine = (TargetDataLine) AudioSystem.getLine(targetInfo);
		
	}
	
	public void checkMicrophone() {
		if (!AudioSystem.isLineSupported(targetInfo)) {
          System.out.println("Microphone not supported");
          System.exit(0);
        }
	}
	
	public void open() {
		try {
			targetDataLine.open(audioFormat);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void start() {
        targetDataLine.start();
	}

	public AudioInputStream getAudio() {
		return new AudioInputStream(targetDataLine);
	}
	
	public void close() {
        targetDataLine.close();
	}
}
