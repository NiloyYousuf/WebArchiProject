package com.example.projectfrontend2_2;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class OpenLocalHtmlFileExample {
    public static void main(String[] args) {
        try {
            // Specify the path to your local HTML file
            String htmlFilePath = "C:\\Users\\yousu\\Downloads\\ProjectFrontend2_2-main\\src\\main\\resources\\com\\example\\projectfrontend2_2\\Helloview.html";

            // Create a File object from the HTML file path
            File htmlFile = new File(htmlFilePath);

            // Check if the Desktop class is supported (not available on all systems)
            if (Desktop.isDesktopSupported() && htmlFile.exists()) {
                // Get the Desktop instance
                Desktop desktop = Desktop.getDesktop();

                // Open the default web browser with the specified HTML file
                desktop.browse(htmlFile.toURI());
            } else {
                // If Desktop is not supported or the file doesn't exist, print a message
                System.out.println("Desktop is not supported or the file does not exist: " + htmlFilePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
