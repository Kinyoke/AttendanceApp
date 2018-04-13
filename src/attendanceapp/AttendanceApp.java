/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *
 */
package attendanceapp;

import controllers.AttendanceController;

/**
 *
 * @author Faisal Burhan.
 * @version V1.0.0.7 April 08 | 2018.
 * 
 * The attendance application, is a console app that meant to simulate a scenario 
 * that implements the use of smart card for accessing classrooms and collect data 
 * from users which will be used to track attendance.
 *
 */
public class AttendanceApp {

	/**
	 * This method initializes a controller class that presents a user with
	 * access to interact with the application via a console.
	 */
    public static void run() {        
        System.out.println("Attendance App\n" + "==============\n\n");
        AttendanceController attendanceController = new AttendanceController();  
        attendanceController.run();
        System.out.println("Thank you for using Attendance App. Good bye.\n");        
    }
    
    /**
     * The method bellow will call a run method to be executed during runtime of the application.
     * @param args the command line arguments
     */
    public static void main(String[] args){ 
        run();
    }
    
}
