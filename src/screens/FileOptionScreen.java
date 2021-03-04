package screens;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import services.FileService;
import services.ScreenService;


public class FileOptionScreen implements Screen {

    private ArrayList<String> options = new ArrayList<>();

    public FileOptionScreen() {
    	options.add("The only file extensions accepted are .txt|.doc|.csv|.pdf");
        options.add("1. Add a File");
        options.add("2. Delete A File");
        options.add("3. Search A FIle");
        options.add("4. Return to Main Menu");
        options.add("5. Quit");

    }

    @Override
    public void Show()
    {
        System.out.println("\n");

        for (String s : options)  {
            System.out.println(s);
        }

    }

    @Override
    public void GetUserInput()
    {
        int selectedOption;
        this.Show();
        while ((selectedOption = this.getOption()) != 5) {
            this.NavigateOption(selectedOption);
        }
    }

    @Override
    public void NavigateOption(int option)
    {
        switch(option) {

            case 1: // Add File
                this.AddFile();
                ScreenService.setCurrentScreen(ScreenService.FileOptionsScreen);
                ScreenService.getCurrentScreen().GetUserInput();
                break;
            case 2: 
            	this.DeleteFile();
            	 ScreenService.setCurrentScreen(ScreenService.FileOptionsScreen);
                 ScreenService.getCurrentScreen().GetUserInput();
            	break;
            case 3: 
            	this.SearchFile();
            	 ScreenService.setCurrentScreen(ScreenService.FileOptionsScreen);
                 ScreenService.getCurrentScreen().GetUserInput();
            	break;
            case 4:
            	  ScreenService.setCurrentScreen(ScreenService.WelcomeScreen);
                  ScreenService.getCurrentScreen().GetUserInput();
                  break;
            case 5:
            	System.exit(0);
            default:
                System.out.println("Invalid Option");
                break;

        }

    }
    public void SearchFile() {
    	System.out.println("Please Enter the Filename:");
    	String input = getInputString();
    	
    	Boolean filefound = FileService.getInstance().searchFile(input);
    	if(filefound==true) {
    		System.out.println("File was found");
    	}
    	else {
    		System.out.println("File was not found");
    	}
    }
    public void DeleteFile() {
    	System.out.println("Please Enter the Filename:");
    	String input = getInputString();
    	FileService.getInstance().deleteFile(input);
    	FileService.getInstance().update();
    }

    public void AddFile()  {
        System.out.println("Please Enter the Filename:");

        String fileName = this.getInputString();
        try {
        	FileService.getInstance().addFile(fileName);
        }
        catch(Exception e){
        	System.out.println("File was not createdTry a different file name");
        }
        
    }

    private String getInputString()
    {

        @SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
        return(in.nextLine());

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