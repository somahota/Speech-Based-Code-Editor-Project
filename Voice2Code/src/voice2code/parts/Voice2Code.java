package voice2code.parts;

import javax.annotation.PostConstruct;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import edu.brown.cs.voice2text.Voice2Text;

public class Voice2Code {
	private Button button;
	private boolean started;
	private Voice2Text v2t;
	private InsertHandler ih;

	@PostConstruct
	public void createPartControl(Composite parent) throws Exception {
		ih = new InsertHandler();
		
		
		System.out.println("Enter in Button postConstruct");
		v2t = new Voice2Text(ih);
		
		parent.setLayout(new FillLayout());
		
	    button = new Button(parent, SWT.PUSH);
	    
	    ImageData imageData = new ImageData("/home/jeremy/Documents/Brown/2020Fall/CS1951U/project/Speech-Based-Code-Editor-Project/Voice2Code/icons/microphone.png");
	    imageData = imageData.scaledTo(100, 100);
	    button.setImage(new Image(Display.getCurrent(), imageData));
	    
	    setButton2Start();
	    button.addListener(SWT.Selection, new Listener() {
	        public void handleEvent(Event e) {
	          switch (e.type) {
	          case SWT.Selection:
	            if (started) {
	            	setButton2Start();
	            	
	            	ih.insertText("hello world!", 0);
	            	v2t.stop();
	            }
	            else {
	            	setButton2Stop();
	            	
	            	ih.insertText("goodbye world!", 0);
	            	v2t.start();
	            }
	            break;
	          }
	        }
	      });
	}
	
	private void setButton2Start() {
		started = false;
		button.setBackground(new Color(Display.getCurrent(), 152, 251, 152, 100));
		button.setText("Press to Speak");
	}
	
	private void setButton2Stop() {
		started = true;
		button.setBackground(new Color(Display.getCurrent(), 255, 40, 0, 100));
		button.setText("Press to Stop");
	}
}
