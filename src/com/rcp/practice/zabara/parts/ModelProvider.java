package com.rcp.practice.zabara.parts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Platform;
import org.osgi.framework.Bundle;

/**
 * 
 * The model provider basis on two things: the list of persons and text file
 * 
 * @author SZabara
 */
public enum ModelProvider {
    INSTANCE;

    private List<Person> persons;

    private ModelProvider() {
        persons = readPersonsWithBundle();
    }

    public List<Person> getPersons() {
        return persons;
    }

    private List<Person> readPersonsWithBundle() {

        List<Person> persons = new ArrayList<Person>();

        URL url = null;
        try {
            url = new URL("platform:/plugin/com.rcp.practice.zabara/database.txt");
        } catch (MalformedURLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }
        File file = null;
        try {
            file = new File(FileLocator.resolve(url).toURI());
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Failed to create the DATABASE file");
            }
        }
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            while (br.ready()) {

                String text = br.readLine();
                if (!text.equals("")) {
                    String[] parts = text.split(",");
                    try {
                        Person person = new Person(parts[0], Integer.parseInt(parts[1]),
                                Boolean.parseBoolean(parts[2]));
                        persons.add(person);
                    } catch (Exception e) {
                        System.out.println("Cant parse line to person correctly: " + text);
                    }
                }
            }
        } catch (FileNotFoundException exception) {
            System.err.println("Could not find the DATABASE file");
        } catch (IOException e) {
            System.err.println("Could not read the DATABASE file");
        }

        return persons;

    }
}
