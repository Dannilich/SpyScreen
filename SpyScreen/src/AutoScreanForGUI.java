import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.users.FullAccount;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AutoScreanForGUI extends Thread{

//    public static final String ANSI_YELLOW = "\u001B[33m";
//    public static final String ANSI_RESET  = "\u001B[0m";


    private static final String ACCESS_TOKEN = "X12SKqocOmAAAAAAAAAAH8wImF0lz_kDlzxcIaKER7KzQqwDdHWWe8JlHFyw1kYC";
     static boolean isON;

    DbxRequestConfig config;
    DbxClientV2 client;

    DateFormat newformat = new SimpleDateFormat("yyyyddMM_HHmmss");

    ByteArrayInputStream  bInputStream;
    ByteArrayOutputStream bOutputStream;
    BufferedImage screenShot;
    InputStream in;
    String fileName;

    public AutoScreanForGUI(){

         config = DbxRequestConfig.newBuilder("dropbox/java-tutorial").build();
         client = new DbxClientV2(config, ACCESS_TOKEN);
         isON =true;

    }

    @Override
    public void run() {
        try {

//                FullAccount account = client.users().getCurrentAccount();
//                System.out.println("\nЗагрузка на аккаунт: "+ANSI_YELLOW+account.getName().getDisplayName() +"\n");

        int currentScrean = 0;

            while (AutoScreanForGUI.isON) {


                bOutputStream = new ByteArrayOutputStream(1024);

                currentScrean++;
                System.out.print("\nScrean "+currentScrean +" : ");

                screenShot =
                        new Robot().createScreenCapture(
                                new Rectangle(
                                        Toolkit.getDefaultToolkit().getScreenSize()));


                ImageIO.write(screenShot,"PNG",bOutputStream);
                fileName = newformat.format(new Date())+".png";

                 in = new ByteArrayInputStream(bOutputStream.toByteArray());
                 client.files()
                        .uploadBuilder("/ScreenShots/" + fileName)
                        .uploadAndFinish(in);


                System.out.printf(fileName+"\n");


                bOutputStream.flush();
                sleep(5000);

            }

//            System.out.println("\nзагрузка завершена");

            }catch (NullPointerException npe){
                System.out.println("\nCant save scren!" );
                System.exit(-1);
            }

            catch (Exception e){
                System.out.println("Ошибка: "+ e.getMessage());
                e.printStackTrace();
                try {
                    screenShot =
                            new Robot().createScreenCapture(
                                    new Rectangle(
                                            Toolkit.getDefaultToolkit().getScreenSize()));


                    ImageIO.write(screenShot,"PNG",bOutputStream);
                    fileName = newformat.format(new Date())+".png";

                    in = new ByteArrayInputStream(bOutputStream.toByteArray());
                    client.files()
                            .uploadBuilder("/EROR "+e.getMessage()+" "+ fileName)
                            .uploadAndFinish(in);

                    System.exit(-1);
                }catch (AWTException eawte){ System.out.println("Can't do scren of EROR");System.exit(-1);}
                 catch (Exception ee){ System.out.println("wtf ?");  System.exit(-1); }

            }


    }
}
