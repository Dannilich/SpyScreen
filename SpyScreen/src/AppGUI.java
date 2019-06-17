import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class AppGUI {

    static JFrame getFrame(){
        JFrame frame=new JFrame(){};
        frame.setTitle("Взлом пентагона");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setBounds( Toolkit.getDefaultToolkit().getScreenSize().width /2 -375/2,
                         Toolkit.getDefaultToolkit().getScreenSize().height/2 -420/2,
                          375,420);
        return frame;}



    static JFrame frame=getFrame();
    static JPanel panel=new JPanel();
    static AutoScreanForGUI t1 =new AutoScreanForGUI();
    static JTextArea textArea= new JTextArea("Vzlom Pentagona 1.0",20,30);

    static class MyAction extends AbstractAction      {
        private boolean isInProcess =false;

        public void MyAction(){
    putValue(AbstractAction.SHORT_DESCRIPTION,"Уха-ха-ха-ха");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isInProcess){
                isInProcess =false;
                    t1.isON =false;

                textArea.append("\n------------------------------------------------");
                textArea.append("\nСкачивание завершено!");

            }else{
                isInProcess = true;
                textArea.append("\n------------------------------------------------");
                textArea.append("\nНачало взлома...\n");
                try {
                    wait(1000);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                textArea.append("\nвзлом завершён!");
                textArea.append("\n------------------------------------------------");
                textArea.append("\nНачинаем скачку файлов");
                textArea.append("\n------------------------------------------------");

                t1.start();

            }
    }

    public static void main(String[] args)throws Exception {

        GridBagLayout gridBagLayout=new GridBagLayout();
      panel.setLayout(gridBagLayout);

      GridBagConstraints constraints1=new GridBagConstraints();
      constraints1.weightx=0;
      constraints1.weighty=0;
      constraints1.gridx = 0;
      constraints1.gridy = 0;

      constraints1.gridheight=2;
      constraints1.gridwidth =GridBagConstraints.REMAINDER;



      JButton button =new JButton(new MyAction());
      button.setText("Вкл / Выкл");
      panel.add(button,constraints1);

        GridBagConstraints constraints2=new GridBagConstraints();
        constraints2.weightx=0;
        constraints2.weighty=5;
        constraints2.gridx = 0;
        constraints2.gridy = 0;

        constraints2.gridheight=20;
        constraints2.gridwidth =10;

      textArea.setBackground(Color.BLACK);
      textArea.setForeground(Color.GREEN);

      textArea.append("\n------------------------------------------------");
      textArea.append("\nPress button to begin");
      textArea.setLineWrap(true);
      JScrollPane scroll = new JScrollPane(textArea);
      panel.add(scroll,constraints2);



        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
        System.setOut(printStream);
        System.setErr(printStream);


        frame.add(panel);
        frame.revalidate();
    }
}}
class CustomOutputStream extends OutputStream {
    private JTextArea textArea;

    public CustomOutputStream(JTextArea textArea) {
        this.textArea = textArea;

    }

    @Override
    public void write(int b) throws IOException {
        System.setOut(new java.io.PrintStream(System.out, true, "UTF-8"));
        // redirects data to the text area
        textArea.append(String.valueOf((char)b));
        // scrolls the text area to the end of data
        textArea.setCaretPosition(textArea.getDocument().getLength());
        // keeps the textArea up to date
        textArea.update(textArea.getGraphics());
    }
}