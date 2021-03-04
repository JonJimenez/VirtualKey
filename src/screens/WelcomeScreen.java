package screens;

import services.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WelcomeScreen implements Screen {

    private String welcomeText = "Welcome to VirtualKey!";
    private String developerText = "Developer: Jonathan Jimenez";
    private ArrayList<String> options = new ArrayList<>();


    public WelcomeScreen() {

        System.out.println(welcomeText);
        System.out.println(developerText);
        System.out.println("\n");
        options.add("1. Show Files");
        options.add("2. Show File Options Menu");
        options.add("3. Quit");
    }
    @Override
    public void Show()
    {
    	FileService.getInstance().sortFiles();
        for (String s : options)  {
            System.out.println(s);
        }

    }

    @Override
    public void GetUserInput()
    {
    	this.Show();
        int selectedOption;
        while ((selectedOption = this.getOption()) != 3) {
            this.NavigateOption(selectedOption);
        }
    }

    @Override
    public void NavigateOption(int option)
    {
        switch(option) {

            case 1: // Show Files
            	FileService.getInstance().printAllFiles();
                ScreenService.setCurrentScreen(ScreenService.WelcomeScreen);
                ScreenService.getCurrentScreen().GetUserInput();
                break;
            case 2: // Show Submenu
                ScreenService.setCurrentScreen(ScreenService.FileOptionsScreen);
                ScreenService.getCurrentScreen().GetUserInput();
                break;
            case 3:
            	System.exit(0);
            default:
                System.out.println("Invalid Option");
                break;

        }

    }

    public void SortFiles() {
    	FileService.getInstance().sortFiles();
    }
    public void ShowFiles() {
    	this.SortFiles();
    	FileService.getInstance().printAllFiles();

    }
    private int getOption()
    {
        @SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);

        int returnOption = 0;
        try {
            returnOption = in.nextInt();
        }
        catch (InputMismatchException ex) {

        }
        return returnOption;

    }

}