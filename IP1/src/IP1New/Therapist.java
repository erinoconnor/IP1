/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IP1New;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author stani
 */
public class Therapist {

    static String username;
    static String pass;
    static String name1;
    static String name2;
    static String therapyType;

    Therapist therapist = new Therapist();
    private static Scanner x;

    public static boolean verifyLoginT(String therapistId, String psswd) throws FileNotFoundException {
        String tempId = "";
        String tempPsswd = "";
        String tempFirstname = "";
        String tempSecondname = "";
        String tempType = "";

        boolean found = false;

        try {
            x = new Scanner(new File("src/TherapistData/" + therapistId + ".txt"));
            x.useDelimiter("[,\n]");
            while (x.hasNext() && !found) {

                tempId = x.next();
                tempPsswd = x.next();
                tempFirstname = x.next();
                tempSecondname = x.next();
                tempType = x.next();

                if (tempId.trim().equals(therapistId.trim()) && tempPsswd.trim().equals(psswd.trim())) {
                    found = true;
                    name1 = tempFirstname;
                    name2 = tempSecondname;
                    therapyType = tempType;
                    return found;
                } else {
                    found = false;
                    return found;
                }
            }
            x.close();
        } catch (Exception e) {
            System.out.println("Error, cannot login.");
            return false;
        }
        return false;
    }

}
