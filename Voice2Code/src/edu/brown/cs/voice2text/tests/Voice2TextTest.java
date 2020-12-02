package edu.brown.cs.voice2text.tests;

import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import edu.brown.cs.voice2text.Voice2Text;

public class Voice2TextTest {
	@Test
	public void Test() {
		Voice2Text v2t = Mockito.mock(Voice2Text.class);
		v2t.start();
		v2t.stop();
		verify(v2t, times(1)).start();
	}
}
