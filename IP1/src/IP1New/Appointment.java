/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IP1New;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Random;

/**
 *
 * @author erino
 */
//Writes appointment info to different files based on the users id 
public class Appointment {

    public static String therapist;
    public static String date;
    public static String appointid;
    public static String status;
    public static String time;

    public static String makeappointid() {
        Random rand = new Random();
        String tempappointid = "";
        String randomNumber = String.format("%04d", (Object) rand.nextInt(1001));
        String firsttwo = therapist.substring(0, 2);

        tempappointid = firsttwo + randomNumber;

        appointid = tempappointid;

        return appointid;

    }

    public static void writeappointments(LocalDate appointmentdate) throws IOException {

        makeappointid();
        new File("src/AppointmentData/" + Patient.username1).mkdirs();
        FileWriter fw = new FileWriter("src/AppointmentData/" + Patient.username1 + "/" + appointid + ".txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        status = "Pending";

        pw.println(appointid + " " + Patient.username1 + " " + therapist + " " + appointmentdate + " " + time + " " + "Status:" + status + ",");

        pw.flush();
        pw.close();

        new File("src/TherapistAppointmentData/" + therapist).mkdirs();
        FileWriter f = new FileWriter("src/TherapistAppointmentData/" + therapist + "/" + appointid + ".txt");
        BufferedWriter b = new BufferedWriter(f);
        PrintWriter p = new PrintWriter(b);
        p.println(appointid + " " + Patient.username1 + " " + therapist + " " + appointmentdate + " " + time + " " + "Status:" + status + ",");

        p.flush();
        p.close();

    }

}
