package edu.brown.cs.voice2text;


import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.sound.sampled.AudioInputStream;
import com.google.cloud.speech.v1.StreamingRecognizeRequest;

public class Voice2Text implements Runnable{
	private StreamingRecognition streamingRecognition;
	private ResponseObserverClass responseObserver;
	private AudioInputStream audio;
	private final AtomicBoolean running = new AtomicBoolean(false);
	private Microphone microphone;
	
	public Voice2Text() throws Exception{
        responseObserver = new ResponseObserverClass();
        microphone = new Microphone();
        microphone.checkMicrophone();
	}
	
	public void start() {
		System.out.println("V2T Start");
		running.set(true);
		Thread t = new Thread(this);
		t.start();
	}
	public void stop() {
		System.out.println("V2T Stop");
		running.set(false);
	}

	@Override
	public void run() {
		// StreamingRecognition() can not be share 
		// a new one is initialized at every start().
		try {
			streamingRecognition = new StreamingRecognition(responseObserver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		StreamingRecognizeRequest request = streamingRecognition.setStreamingConfig();

        streamingRecognition.sendRequest(request);
		
        microphone.open();
        microphone.start();
        System.out.println("Mic and CloudStream initialized");
        // Audio Input Stream
        audio = microphone.getAudio();
        
        while (running.get()) {
            byte[] data = new byte[6400];
            try {
				audio.read(data);
				request = streamingRecognition.setAudioContent(data);
                streamingRecognition.sendRequest(request);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
        // close clientStream
        streamingRecognition.closeSend();
        microphone.close();
	}
}
