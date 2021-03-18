package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.javarush.task.task32.task3209.MenuHelper.*;

public class View extends JFrame implements ActionListener {
    private Controller controller;

    private  JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();

    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ss = e.getActionCommand();
        if(ss.equals("Новый")){
            controller.createNewDocument();
        }
        if(ss.equals("Открыть")){
            controller.openDocument();
        }
        if(ss.equals("Сохранить")){
            controller.saveDocument();
        }
        if(ss.equals("Сохранить как...")){
            controller.saveDocumentAs();
        }if(ss.equals("Выход")){
            controller.exit();
        }
        if(ss.equals("О программе")){
            showAbout();
        }


    }
    public void exit(){
        controller.exit();
    }

    public void init(){

        initGui();
        addWindowListener(new FrameListener(this));
      //  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);


    }

    public View() {
        try {
            //UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public void initMenuBar(){
        JMenuBar jMenuBar = new JMenuBar();

        initFileMenu(this, jMenuBar);
        initEditMenu(this,jMenuBar);
        initStyleMenu(this,jMenuBar);
        initAlignMenu(this,jMenuBar);
        initColorMenu(this,jMenuBar);
        initFontMenu(this,jMenuBar);
        initHelpMenu(this,jMenuBar);
        getContentPane().add(jMenuBar, BorderLayout.NORTH);//gggggggg


    }

    public void initEditor(){

         htmlTextPane.setContentType("text/html");//klhi
        JScrollPane jScrollPaneHTML = new JScrollPane(htmlTextPane);//ggfgg
        tabbedPane.addTab("HTML",jScrollPaneHTML);//hhh
        JScrollPane jScrollPanePlain = new JScrollPane(plainTextPane);//jjj
        tabbedPane.addTab("Текст", jScrollPanePlain);//kkkkkk
        tabbedPane.setPreferredSize(new Dimension(400,200));//hhhhhhh
        tabbedPane.addChangeListener(new TabbedPaneChangeListener(this));//hhhhhhh
        getContentPane().add(tabbedPane,BorderLayout.CENTER);//gggggggg

        //gkjgkg

    }

    public void initGui(){
        initMenuBar();
        initEditor();
        pack();
    }

    public void selectedTabChanged(){
        if(tabbedPane.getSelectedIndex()==0){
            controller.setPlainText(plainTextPane.getText());
        }
       else {
            plainTextPane.setText(controller.getPlainText());

        }
        resetUndo();
    }
    public void undo(){
        try {

        undoManager.undo();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public void redo(){
        try {

        undoManager.redo();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public boolean canUndo() {
        return undoManager.canUndo();
    }   public boolean canRedo()  {
        return undoManager.canRedo();
    }

    public void resetUndo(){
        undoManager.discardAllEdits();
    }

    public boolean isHtmlTabSelected(){
        if(tabbedPane.getSelectedIndex()==0)
            return true;
        else return false;
    }

    public void selectHtmlTab(){

        tabbedPane.setSelectedIndex(0);
        this.resetUndo();
    }

    public void update(){
        htmlTextPane.setDocument(controller.getDocument());
    }

    public void showAbout(){
        JOptionPane.showMessageDialog(tabbedPane.getSelectedComponent(), "Версия 1.0", "О программме", JOptionPane.INFORMATION_MESSAGE);
    }


}
