package com.rcp.practice.zabara.jface;

import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

import com.rcp.practice.zabara.composite.attachments.MainComposite;

/**
 * Using for simplification routine operation. consist of static methods and constants.
 * 
 * @author SZabara
 *
 */
public class Utils {

    /**
     * Object of this class we won't create
     */
    private Utils() {
        super();
    }

    public static final String FILE_PATH = "database.txt";

    /**
     * Writes person's data to fields of the right side
     * 
     * @param mainComposite right side
     * @param selectionPerson selected Person
     */
    public static void putPersonData(MainComposite mainComposite, Person selectionPerson) {
        if (selectionPerson != null && !mainComposite.getDeleteButton().getSelection()) {
            mainComposite.getNameTextField().setText(selectionPerson.getName());
            mainComposite.getGroupTextField().setText(String.valueOf(selectionPerson.getGroup()));
            mainComposite.getSwtCheckdone().setSelection(selectionPerson.isSwtDone());
        }
    }

    /**
     * Clear all fields in the right side
     */
    public static void putEmptyPersonData(MainComposite mainComposite) {
        mainComposite.getNameTextField().setText("");
        mainComposite.getGroupTextField().setText("");
        mainComposite.getSwtCheckdone().setSelection(false);
    }

    /**
     * Discards changes which did without save in the composite.
     * 
     * @param mainComposite composite with changes
     * @param currentPerson person with data that without changes
     */
    public static void removeChangesPersonData(MainComposite mainComposite, Person currentPerson) {
        mainComposite.getNameTextField().setText(currentPerson.getName());
        mainComposite.getGroupTextField().setText(String.valueOf(currentPerson.getGroup()));
        mainComposite.getSwtCheckdone().setSelection(currentPerson.isSwtDone());
    }

    /**
     * Updates person`s data
     * 
     * @param availablePerson person that must be changed.
     * @param name new name for person
     * @param group new gruop's number for person
     * @param swtDone
     */
    public static void updatePersonData(Person availablePerson, String name, int group, boolean swtDone) {
        availablePerson.setName(name);
        availablePerson.setGroup(group);
        availablePerson.setSwtDone(swtDone);
        ;
    }

    /**
     * 
     * Validates Person object
     * 
     * @param name can not be empty or equals null
     * @param group can not equals zero
     * @return if all conditions was kept, return true
     */
    public static boolean isValidData(String name, int group) {

        if (name == null || name.equals("") || group <= 0 || group > 99) {
            return false;
        }
        return true;
    }

    /**
     * Validates name by lenght
     * 
     * @param str String for validate
     * @return validation result
     */
    public static boolean isName(String str) {

        return str.length() < 100;
    }

    public static Image getImageByPath() {
        Image result = null;

        try {
            URL imageURL = new URL("platform:/plugin/com.rcp.practice.zabara/icons/ñapture.png");
            ImageDescriptor id = ImageDescriptor.createFromURL(imageURL);
            result = id.createImage();
            return result;
        } catch (Exception e) {
            e.printStackTrace();

        }

        return result;
    }
}
