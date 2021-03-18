package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {
    private View view;
    private HTMLDocument document;

    public HTMLDocument getDocument() {
        return document;
    }

    public View getView() {
        return view;
    }

    private File currentFile;


    public Controller(View view) {
        this.view = view;
    }

    public void init() {
       createNewDocument();
    }

    public void exit() {
        System.exit(0);
    }

    public static void main(String[] args) {
        View view1 = new View();
        Controller controller = new Controller(view1);
        view1.setController(controller);
        view1.init();
        controller.init();
    }

    public void resetDocument() {
        UndoListener listener = view.getUndoListener();

        if (document != null) {
            document.removeUndoableEditListener(listener);
        }

        document = (HTMLDocument) new HTMLEditorKit().createDefaultDocument();
        document.addUndoableEditListener(listener);
        view.update();
    }

    public void setPlainText(String text) {
        try {
            HTMLEditorKit ww = new HTMLEditorKit();

            resetDocument();
            StringReader reader = new StringReader(text);
            ww.read(reader, document, 0);

        } catch (Exception e) {
            ExceptionHandler.log(e);
        }

    }

    public String getPlainText(){
        StringWriter stringWriter = new StringWriter();
        try {
            new HTMLEditorKit().write(stringWriter, document, 0,  document.getLength());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
        return stringWriter.toString();

    }

    public void createNewDocument(){
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
       view.resetUndo();
       currentFile=null;
       init();

    }

    public void openDocument(){
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());

        if (fileChooser.showOpenDialog(view) == JFileChooser.OPEN_DIALOG) {
            currentFile = fileChooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            try (FileReader reader = new FileReader(currentFile)) {
                new HTMLEditorKit().read(reader, document, 0);
                view.resetUndo();
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
    }

    public void saveDocument(){
        view.selectHtmlTab();
        if (currentFile != null)
        {
            try {
                view.setTitle(currentFile.getName());
                FileWriter writer = new FileWriter(currentFile);
                new HTMLEditorKit().write(writer, document, 0, document.getLength());
                writer.close();
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }
        else saveDocumentAs();

    }

    public void saveDocumentAs(){
        view.selectHtmlTab();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new HTMLFileFilter());
        if (fileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());
            try (FileWriter writer = new FileWriter(currentFile)) {
                new HTMLEditorKit().write(writer, document, 0, document.getLength());
            } catch (Exception e) {
                ExceptionHandler.log(e);
            }
        }

    }

}
