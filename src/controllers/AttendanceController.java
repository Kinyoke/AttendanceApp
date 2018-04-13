package controllers;

import helpers.InputHelper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import model.Swipe;
import model.VisitorSwipe;
import repositories.Repository;

/**
 *
 * @author Faisal Burhan
 */
public class AttendanceController {
    private final Repository repository;
    
    /**
     *
     */
    public AttendanceController() {
        char flag = new InputHelper().readCharacter("Load data from the already existing Attendance file [y/n]");
        if(flag == 'y' || flag == 'Y'){
            String fname = new InputHelper().readString("Enter file name");
            this.repository = new Repository(fname);
        }else{
        this.repository = new Repository();
      }
    }

    /**
     *
     */
    public void run() {
        boolean finished = false;
        
        do {
            char choice = displayAttendanceMenu();
            switch (choice) {
                case 'A': 
                    addSwipe();
                    break;
                case 'B':  
                    listSwipes();
                    break;
                case 'C': 
                    listSwipesByCardIdOrderedByDateTime(); // 
                    break;                    
                case 'D': 
                    listSwipeStatistics(); //
                    break;
                case 'Q': 
                    finished = true;
            }
        } while (!finished);
    }
    
    private char displayAttendanceMenu() {
        InputHelper inputHelper = new InputHelper();
        System.out.print("\nA. Add Swipe");
        System.out.print("\tB. List Swipes");        
        System.out.print("\tC. List Swipes In Date Time Order");
        System.out.print("\tD. List Swipes Which Match Card Id");       
        System.out.print("\tQ. Quit\n");         
        return inputHelper.readCharacter("   Enter choice", "ABCDQ");
    }    
    
    private void addSwipe() {
        System.out.format("\033[31m%s\033[0m%n", "Add Swipe");
        System.out.format("\033[31m%s\033[0m%n", "=========");       
        InputHelper input = new InputHelper();
        switch(input.readInt("1. Normal swipe     2. Visitor swipe")){
            case 1:
                Swipe swipe = new Swipe();
                swipe.setCardId(input.readString("   Enter card ID"));
                swipe.setRoom(input.readString("   Enter room"));
                this.repository.add(swipe);
                this.repository.store("swipes.txt");
            break;
            case 2:
                VisitorSwipe vSwipe = new VisitorSwipe();
                vSwipe.setCardId(input.readString("   Enter card Id"));
                vSwipe.setRoom(input.readString("   Enter room"));
                vSwipe.setVisitorName(input.readString("   Enter name"));
                vSwipe.setVisitorCompany(input.readString("   Enter company name"));
                this.repository.add(vSwipe);
                this.repository.store("swipes.txt");
            break;
            default:
                System.out.println("   Please enter a valid option");
            break;
        }
    }
    
    private void listSwipes() {        
        System.out.format("\033[31m%s\033[0m%n", "Swipes");
        System.out.format("\033[31m%s\033[0m%n", "======");   
        for(int i = 1; i < repository.getItems().size()+1; i++) System.out.print(this.repository.getItem(i).toString());
        System.out.println();
        
    }      
      

    private void listSwipesByCardIdOrderedByDateTime() {        
        System.out.format("\033[31m%s\033[0m%n", "Swipes By Card Id");
        System.out.format("\033[31m%s\033[0m%n", "=================");
        String temp = new InputHelper().readString("   Enter card Id");
        for(int i = this.repository.getItems().size(); i > 0; i--){
        	if(this.repository.getItem(i).getCardId().equals(temp)){
        	   if(this.repository.getItem(i).compareTo(this.repository.getItem(i)) == 0) System.out.println(this.repository.getItem(i));
        	}
        }
        
        
    }    
    
    private void listSwipeStatistics() {
        System.out.format("\033[31m%s\033[0m%n", "Swipe Statistics for room");
        System.out.format("\033[31m%s\033[0m%n", "========================="); 
        int counter = 0;
        String temp = new InputHelper().readString("   Enter room name");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar timeStamp = Calendar.getInstance();  
        for(int i  = 1; i < this.repository.getItems().size()+1; i++){
            if(this.repository.getItem(i).getRoom().equals(temp)){
              timeStamp =  this.repository.getItem(i).getSwipeDateTime();
              counter++;
            }
        }
        System.out.println("   Number of swipes for " + temp + " room is " + counter + " last time swiped on " + dateFormat.format(timeStamp.getTime()));
    }
}
