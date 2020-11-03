package edu.brown.cs.voice2text;

/**
 * Example Usage of Voice2Text
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        Voice2Text v2t1 = new Voice2Text();
		Thread thread1 = new Thread(v2t1);
		thread1.start();
		v2t1.start();
        Thread.sleep(20000); // 20s
        v2t1.stop();
        Voice2Text v2t2 = new Voice2Text();
		Thread thread2 = new Thread(v2t2);
		thread2.start();
        Thread.sleep(10000); // 10s
        Voice2Text v2t3 = new Voice2Text();
		Thread thread3 = new Thread(v2t3);
		thread3.start();
        v2t2.start();
        Thread.sleep(20000); // 20s
        v2t2.stop();
    }
}
