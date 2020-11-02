package edu.brown.cs.voice2text;

import static edu.brown.cs.voice2text.RecognitionConfiguration.*;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.sound.sampled.AudioInputStream;

import com.google.api.gax.rpc.ClientStream;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.StreamingRecognitionConfig;
import com.google.cloud.speech.v1.StreamingRecognizeRequest;
import com.google.protobuf.ByteString;

public class Voice2Text implements Runnable{
	private SpeechClient client;
	private ResponseObserverClass responseObserver;
	private ClientStream<StreamingRecognizeRequest> clientStream;
	private AudioInputStream audio;
	private Thread thread;
	private final AtomicBoolean running = new AtomicBoolean(false);
	private Microphone microphone;
	
	public Voice2Text() throws Exception{
		client = SpeechClient.create();
        responseObserver = new ResponseObserverClass();

        microphone = new Microphone();
        
        microphone.checkMicrophone();
	}
	
	public void start() {
		System.out.println("V2T Start");
		thread = new Thread(this);
		thread.start();
	}
	public void stop() {
		System.out.println("V2T Stop");
		running.set(false);
	}

	@Override
	public void run() {
		running.set(true);
		
		// start/restart clientStream
        clientStream =
                client.streamingRecognizeCallable().splitCall(responseObserver);
        
        RecognitionConfig recognitionConfig =
                RecognitionConfig.newBuilder()
                    .setEncoding(encoding)
                    .setLanguageCode(languageCode)
                    .setModel(model)
                    .setSampleRateHertz(sampleRateHertz)
                    .build();
        
        StreamingRecognitionConfig streamingRecognitionConfig =
            StreamingRecognitionConfig.newBuilder()
            	.setConfig(recognitionConfig)
            	.setInterimResults(interimResults)
            	.build();

        // send config to server
        StreamingRecognizeRequest request =
            StreamingRecognizeRequest.newBuilder()
                .setStreamingConfig(streamingRecognitionConfig)
                .build();
        clientStream.send(request);
		
        microphone.open();
        microphone.start();
        System.out.println("Mic and CloudStream initialized");
        // Audio Input Stream
        audio = microphone.getAudio();
        
        while (running.get()) {
            byte[] data = new byte[6400];
            try {
				audio.read(data);
                request =
                        StreamingRecognizeRequest.newBuilder()
                            .setAudioContent(ByteString.copyFrom(data))
                            .build();
                clientStream.send(request);
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        
        // close clientStream
        clientStream.closeSend();
        microphone.close();
	}
}
