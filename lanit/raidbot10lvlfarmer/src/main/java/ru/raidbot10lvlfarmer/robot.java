package ru.raidbot10lvlfarmer;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import ru.raidbot10lvlfarmer.DB.Entity.BotFollower;
import ru.raidbot10lvlfarmer.DB.Service.BotFollowerService;

import javax.tools.Tool;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

/**
 * todo Document type robot
 */
@Component
public class robot {
    private BotFollowerService botFollowerService;

    @Autowired
    public robot(BotFollowerService botFollowerService) {
        this.botFollowerService = botFollowerService;
    }

    @Bean
    public Robot getNewRobot() throws AWTException {
        Robot robot = new Robot();
        return robot;
    }

    @Bean
    public void farm() throws AWTException {
        Faker faker = new Faker();
        String name = faker.name().username();
        Double randNum = Math.random() * 100 + 1;
        String password = faker.name().username() + "2345PassWoRd";

        String email = faker.name().username() + "@gmail.com";

        botFollowerService.addFollower(new BotFollower(name, password, email));




        Robot robot = getNewRobot();

        String CE = "C:\\Program Files\\Cheat Engine 7.2\\Cheat Engine.exe";
        String PP = "C:\\Users\\Sgt15\\AppData\\Local\\Plarium\\PlariumPlay\\PlariumPlay.exe";
        String RSL = "C:\\BotFarmer\\RSL.lnk";
        String YaBr = "C:\\Users\\Sgt15\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";
        String RmCache = "C:\\BotFarmer\\rm_Cache.bat";
        String TaksKillBr = "C:\\BotFarmer\\taskKillBr.bat";
        String TaksKillPP = "C:\\BotFarmer\\taskKillPP.bat";
        String TaksKillCMS = "C:\\BotFarmer\\taskKillCMS.bat";
        String Trainer = "C:\\BotFarmer\\raidSpeedhack.EXE";
        String ScriptRaidTutorial = "C:\\BotFarmer\\Tutorial.cms";
        String ScriptRaid10lvlfarm = "C:\\BotFarmer\\10lvlfarm.cms";



        File PPExe =new File(PP);
        File CeExe =new File(CE);
        File YaBrExe =new File(YaBr);
        File RmCacheBat =new File(RmCache);
        File RSLlnk =new File(RSL);
        File TaksKillBrBat =new File(TaksKillBr);
        File TaksKillPPBat =new File(TaksKillPP);
        File TaksKillCMSBat =new File(TaksKillCMS);
        File TrainerExe =new File(Trainer);
        File ScriptRaidTutorialCMS =new File(ScriptRaidTutorial);
        File ScriptRaid10lvlfarmCMS =new File(ScriptRaid10lvlfarm);

        try {
            Desktop.getDesktop().open(TaksKillCMSBat);
            Desktop.getDesktop().open(TaksKillPPBat);
            Thread.sleep(5000);
            Desktop.getDesktop().open(RmCacheBat);
            Thread.sleep(5000);

            Desktop.getDesktop().open(YaBrExe);
            Thread.sleep(15000);

            //fullscreen browser
            robot.keyPress(KeyEvent.VK_WINDOWS);
            robot.keyPress(KeyEvent.VK_UP);
            robot.keyRelease(KeyEvent.VK_WINDOWS);
            robot.keyRelease(KeyEvent.VK_UP);
            Thread.sleep(1000);
            //open incognito mode
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_SHIFT);
            robot.keyPress(KeyEvent.VK_N);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_SHIFT);
            robot.keyRelease(KeyEvent.VK_N);
            Thread.sleep(4000);
            robot.mouseMove(530,50);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            Thread.sleep(1000);
            //Paste link
            String link = "https://clik.cc/sRLF0";
            StringSelection stringSelection = new StringSelection(link);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, stringSelection);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(20000);

            //paste username
            robot.mouseMove(250,320);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            Thread.sleep(2000);
            StringSelection stringSelectionName = new StringSelection(name);
            Clipboard clipboardName = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelectionName, stringSelectionName);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(2000);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(5000);

            //paste password
            robot.mouseMove(250,340);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            Thread.sleep(1000);
            StringSelection stringSelectionPassword = new StringSelection(password);
            Clipboard clipboardPassword = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelectionPassword, stringSelectionPassword);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(1000);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(5000);

            //paste email
            robot.mouseMove(250,340);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            Thread.sleep(1000);
            StringSelection stringSelectionEmail = new StringSelection(email);
            Clipboard clipboardEmail = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelectionEmail, stringSelectionEmail);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(1000);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(2000);
            //accept rules
            robot.mouseMove(180,380);
            Thread.sleep(500);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            Thread.sleep(1000);

            //register
            robot.mouseMove(270,430);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            Thread.sleep(1000);

            //download
            robot.mouseMove(270,430);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            Thread.sleep(1000);
            Desktop.getDesktop().open(TaksKillBrBat);
            Thread.sleep(7000);

            Desktop.getDesktop().open(PPExe);

            Thread.sleep(30000);



            //fullscreen
            robot.keyPress(KeyEvent.VK_WINDOWS);
            robot.keyPress(KeyEvent.VK_UP);
            robot.keyRelease(KeyEvent.VK_WINDOWS);
            robot.keyRelease(KeyEvent.VK_UP);
            Thread.sleep(2000);

            //login
            robot.mouseMove(150,922);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            Thread.sleep(5000);

            //enter email
            robot.mouseMove(600,430);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            clipboard.setContents(stringSelectionEmail, stringSelectionEmail);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(2000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(3000);

            //enter password
            robot.mouseMove(600,470);
            Thread.sleep(1000);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            clipboard.setContents(stringSelectionPassword, stringSelectionPassword);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(1000);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(2000);
            //press login
            robot.mouseMove(770,540);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);

            Thread.sleep(4000);
    //      Get it
            robot.mouseMove(1150,450);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);
            Thread.sleep(4000);

            //Play
            robot.mouseMove(1150,390);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);

            Thread.sleep(10000);

            robot.mouseMove(715,21);
            robot.mousePress(InputEvent.BUTTON1_MASK);
            robot.mouseMove(715,0);
            robot.mouseRelease(InputEvent.BUTTON1_MASK);



            Desktop.getDesktop().open(ScriptRaidTutorialCMS);
            Thread.sleep(5000);
            robot.keyPress(KeyEvent.VK_ALT);
            robot.keyPress(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_A);
            robot.keyRelease(KeyEvent.VK_ALT);

            Thread.sleep(600000);

            Thread.sleep(5000);

            Desktop.getDesktop().open(ScriptRaid10lvlfarmCMS);
        }
        catch (IOException | InterruptedException e1)
        {
            e1.printStackTrace();
        }


    }

}
