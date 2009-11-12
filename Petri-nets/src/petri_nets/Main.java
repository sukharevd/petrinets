package petri_nets;

import java.util.ArrayList;

import view.AppFrame;

import data.Data;
import data.Element;

/**
 * Entry point of application.
 * 
 * @author <a href="mailto:sukharevd@gmail.com">Sukharev Dmitriy</a>
 * @author <a href="mailto:ydzyuban@gmail.com">Dzyuban Yuriy</a>
 * @author <a href="mailto:vixentael@gmail.com">Voitova Anastasiia</a>
 * @author <a href="mailto:lave14@mail.ru">Kustch Kristina</a>
 * 
 */
public final class Main {

    /**
     * Data of the application.
     */
    private static Data data = new Data(new ArrayList<Element>());

    /**
     * Frame of the application.
     */
    private static AppFrame appFrame = new AppFrame(data);

    /**
     * Starts GUI shell.
     * 
     * @param args
     *            the args of cmd line
     */
    public static synchronized void main(final String[] args) {
        appFrame.createAndShowGUI();
    }

}