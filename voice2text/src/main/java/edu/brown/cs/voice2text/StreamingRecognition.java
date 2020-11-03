package edu.brown.cs.voice2text;


import static edu.brown.cs.voice2text.RecognitionConfiguration.*;

import com.google.api.gax.rpc.ClientStream;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.StreamingRecognitionConfig;
import com.google.cloud.speech.v1.StreamingRecognizeRequest;
import com.google.cloud.speech.v1.StreamingRecognizeRequest.Builder;
import com.google.protobuf.ByteString;

public class StreamingRecognition {
	private SpeechClient client;
	private ClientStream<StreamingRecognizeRequest> clientStream;
	private RecognitionConfig recognitionConfig;
	private StreamingRecognitionConfig streamingRecognitionConfig;
	private Builder request;

	public StreamingRecognition(ResponseObserverClass responseObserver) throws Exception {
		client = SpeechClient.create();
		// start/restart clientStream
        clientStream =
                client.streamingRecognizeCallable().splitCall(responseObserver);
        recognitionConfig =
                RecognitionConfig.newBuilder()
                    .setEncoding(encoding)
                    .setLanguageCode(languageCode)
                    .setModel(model)
                    .setSampleRateHertz(sampleRateHertz)
                    .build();
        streamingRecognitionConfig =
                StreamingRecognitionConfig.newBuilder()
                	.setConfig(recognitionConfig)
                	.setInterimResults(interimResults)
                	.build();
        // send config to server
        request =
            StreamingRecognizeRequest.newBuilder();
	}
	
	public void sendRequest(StreamingRecognizeRequest request) {
		clientStream.send(request);
	}

	public StreamingRecognizeRequest setAudioContent(byte[] data) {
		return request
	        .setAudioContent(ByteString.copyFrom(data))
	        .build();
	}

	public StreamingRecognizeRequest setStreamingConfig() {
		return request
                .setStreamingConfig(streamingRecognitionConfig)
                .build();
	}

	public void closeSend() {
		clientStream.closeSend();
	}
}
