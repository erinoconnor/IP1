/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IP1New;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

public class Patient {
//    Declaring a new patient object
static String username1;
static String pass1;
static String name1;
static String name2;
static String dob1;
    Patient patient = new Patient();
//    Generating a random username to the user using first two letters of first and last name and four random numbers

    public static String makeusername(String forename, String surname) {
        Random rand = new Random();
        String usernme;
        String firstnamechar;
        String secondnamechar;
        String randomNumber = String.format("%04d", (Object) rand.nextInt(1001));
        firstnamechar = forename.substring(0, 2);
        secondnamechar = surname.substring(0, 2);

        usernme = firstnamechar + secondnamechar + randomNumber;

        String userid = usernme.toLowerCase();

        return userid;
    }
//Writes the patients information to text files named after their userid
    public static void write(String userid, String psswd, String forename, String surname, LocalDate dob) throws IOException {
        FileWriter fw = new FileWriter("src/UserData/"+ userid + ".txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        pw.println(userid + "," + psswd + "," + forename + "," + surname + "," + dob);

        pw.flush();
        pw.close();

    
    }
    
    
    private static Scanner x;
    /*Looks for a textfile in userdata named after the usersid. IF the userid is found it reads in their info,
    which is seperated by commas,into different variables and found is set to true and returned to the controller.
    
    */
     public static boolean verifyLogin(String username, String password) throws FileNotFoundException {
        String tempUsername = "";
        String tempPassword = "";
        String firstname = "";
        String secondname = "";
        String birthdate = "";

        boolean found = false;

        try {
            x = new Scanner(new File("src/UserData/"+ username+ ".txt"));
            x.useDelimiter("[,\n]");
            while (x.hasNext() && !found) {

                tempUsername = x.next();
                tempPassword = x.next();
                firstname = x.next();
                secondname = x.next();
                birthdate = x.next();
                
                if (tempUsername.trim().equals(username.trim()) && tempPassword.trim().equals(password.trim())) {
                    found = true;
                    
                    
                    name1=firstname.substring(0, 1).toUpperCase() + firstname.substring(1);
                    name2=secondname.substring(0, 1).toUpperCase() + secondname.substring(1);
                    dob1=birthdate;
                     
                    return found;
                    
                }
                else{
                    found = false;
                    return found;
                }
            }
            x.close();
        } catch (Exception e) {
            System.out.println("Can't find details");
            return false;
        }
        return false;
    }
 public static boolean verifySearch(String usernames) throws FileNotFoundException {
        String tempUsername = "";
        String tempPassword = "";
        String firstname = "";
        String secondname = "";
        String birthdate = "";

        boolean found = false;

        try {
            x = new Scanner(new File("src/UserData/"+ usernames+ ".txt"));
            x.useDelimiter("[,\n]");
            while (x.hasNext() && !found) {

                tempUsername = x.next();
                tempPassword = x.next();
                firstname = x.next();
                secondname = x.next();
                birthdate = x.next();
                
                if (tempUsername.trim().equals(usernames.trim())) {
                    found = true;
                    name1=firstname.substring(0, 1).toUpperCase() + firstname.substring(1);;
                    name2=secondname.substring(0, 1).toUpperCase() + secondname.substring(1);;
                    dob1=birthdate;
                     
                    return found;
                    
                }
                else{
                    found = false;
                    return found;
                }
            }
            x.close();
        } catch (Exception e) {
          
            return false;
        }
        return false;
    }
}
