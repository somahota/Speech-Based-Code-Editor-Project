package edu.brown.cs.voice2text;


import static edu.brown.cs.voice2text.RecognitionConfiguration.*;

import com.google.api.gax.rpc.ClientStream;
import com.google.cloud.speech.v1p1beta1.RecognitionConfig;
import com.google.cloud.speech.v1p1beta1.SpeechClient;
import com.google.cloud.speech.v1p1beta1.StreamingRecognitionConfig;
import com.google.cloud.speech.v1p1beta1.StreamingRecognizeRequest;
import com.google.protobuf.ByteString;

public class StreamingRecognition {
	private SpeechClient client;
	private ClientStream<StreamingRecognizeRequest> clientStream;
	private RecognitionConfig recognitionConfig;
	private StreamingRecognitionConfig streamingRecognitionConfig;

	public StreamingRecognition(ResponseObserverClass responseObserver) throws Exception {
		client = SpeechClient.create();
	}
	
	public void startClientStream(ResponseObserverClass responseObserver) {

		// start/restart clientStream
        clientStream =
                client.streamingRecognizeCallable().splitCall(responseObserver);
        
	}
	
	public void setRecognitionConfig() {
        recognitionConfig =
                RecognitionConfig.newBuilder()
                    .setEncoding(encoding)
                    .setLanguageCode(languageCode)
                    .setModel(model)
                    .setSampleRateHertz(sampleRateHertz)
                    .build();
	}
	
	public void setStreamingRecognition() {
        streamingRecognitionConfig =
                StreamingRecognitionConfig.newBuilder()
                	.setConfig(recognitionConfig)
                	.setInterimResults(interimResults)
                	.build();
	}
	
	public void sendRequest(StreamingRecognizeRequest request) {
		clientStream.send(request);
	}

	public StreamingRecognizeRequest setAudioContent(byte[] data) {
		return StreamingRecognizeRequest.newBuilder()
	        .setAudioContent(ByteString.copyFrom(data))
	        .build();
	}

	public StreamingRecognizeRequest setStreamingConfig() {
		return StreamingRecognizeRequest.newBuilder()
                .setStreamingConfig(streamingRecognitionConfig)
                .build();
	}

	public void closeSend() {
		clientStream.closeSend();
	}
}
