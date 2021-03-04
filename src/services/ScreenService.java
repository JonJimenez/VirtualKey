package services;

import screens.*;

public class ScreenService {

    public static WelcomeScreen WelcomeScreen = new WelcomeScreen();
    public static FileOptionScreen FileOptionsScreen = new FileOptionScreen();



    public static Screen CurrentScreen = WelcomeScreen;

    public static Screen getCurrentScreen() {
        return CurrentScreen;
    }

    public static void setCurrentScreen(Screen currentScreen) {
        CurrentScreen = currentScreen;
    }
}
