package org.brown.speechtocode.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.*;
import org.eclipse.ui.texteditor.*;
import org.eclipse.text.*;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;

public class SampleHandler extends AbstractHandler {

	private final IWorkbenchPage workbenchPage;
	private final ITextEditor editor;
	private final IDocument document;
	
	public SampleHandler() {
		this.workbenchPage = PlatformUI.getWorkbench()
										.getActiveWorkbenchWindow()
										.getActivePage();
		IEditorPart part = this.workbenchPage.getActiveEditor();

		this.editor = (ITextEditor)part;
		IDocumentProvider dp = editor.getDocumentProvider();
		this.document = dp.getDocument(editor.getEditorInput());
	}
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
	   
		insertText("Hello world\n", 0);
		moveCursorToLineNumber(2);
		return null;
	}
	
	private void insertText(String text, int offset) {
		
		try {
			this.document.replace(offset, 0, "hello world" + "\n");
		} catch(BadLocationException e) {
			// shut up
		}
		
		return;
	}
	
	private void moveCursorToLineNumber(int lineNumber) {
		
		int lineOffset;
		
		try {
			lineOffset = this.document.getLineLength(lineNumber);
			this.editor.selectAndReveal(lineOffset, 0);
		} catch (BadLocationException e) {
			// calm down
		}
		
	}
}
