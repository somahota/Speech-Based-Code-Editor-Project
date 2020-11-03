package edu.brown.cs.voice2text;

import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.StreamController;
import com.google.cloud.speech.v1.StreamingRecognizeResponse;

public class ResponseObserverClass implements ResponseObserver<StreamingRecognizeResponse>{


	@Override
	public void onStart(StreamController controller) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void onResponse(StreamingRecognizeResponse response) {
		// TODO Auto-generated method stub
		System.out.println("Transcript: " + ((StreamingRecognizeResponse) response).getResults(0).getAlternatives(0).getTranscript());
		System.out.println("Confidence: " + ((StreamingRecognizeResponse) response).getResults(0).getAlternatives(0).getConfidence());
		System.out.println("Final: " + ((StreamingRecognizeResponse) response).getResults(0).getIsFinal());
		
	}


	@Override
	public void onError(Throwable t) {
		// TODO Auto-generated method stub
		System.out.println(t);
		
	}


	@Override
	public void onComplete() {
		// TODO Auto-generated method stub
		
	}

}
