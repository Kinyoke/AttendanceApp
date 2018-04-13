/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * @author Faisal Burhan
 */
package daos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Swipe;
import model.VisitorSwipe;
import repositories.Repository;

/**
 *
 * 
 */
public class DAOTextImpl implements DAOInterface {
    
   static int trackIndex = 0;
    
   public Repository load(String filename){
           Repository repo = new Repository();
           File myFile = new File(filename);
           Swipe mySwipe = null;
           VisitorSwipe vSwipe = null;
           HashSet<Swipe> List = new HashSet<>();
           Scanner read;
           String data;
           Calendar cal = null;
           cal = Calendar.getInstance();
        try {
            read = new Scanner(myFile);
             while(read.hasNextLine()){
                 data = read.nextLine();
                 if(data.equals(""));
                 else if(data.split(",").length <= 4){
                     SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                     sdf.parse(data.split(",")[3].replace("\"", ""));
                     cal = sdf.getCalendar();
                     mySwipe = new Swipe(Integer.parseInt(data.split(",")[0]), data.split(",")[1].replaceAll("\"", ""), data.split(",")[2].replaceAll("\"", ""), cal); List.add(mySwipe); }
                 else{ 
                     SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                     sdf.parse(data.split(",")[3].replace("\"", ""));
                     cal = sdf.getCalendar();
                     vSwipe = new VisitorSwipe(Integer.parseInt(data.split(",")[0]), data.split(",")[1].replaceAll("\"", ""), data.split(",")[2].replaceAll("\"", ""), cal, data.split(",")[4].replaceAll("\"", ""), data.split(",")[5].replaceAll("\"", "")); List.add(vSwipe); }
               }
              repo.setItems(List);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException ex) {
           Logger.getLogger(DAOTextImpl.class.getName()).log(Level.SEVERE, null, ex);
       }
        return repo;
       }
   
       public void store(String filename, Repository repository){
    	   try {
               FileWriter writeOut = new FileWriter(filename, true);
               for(int i = 1; i < repository.getItems().size()+1; i++){
            	   writeOut.write(repository.getItem(i).toString(','));
               }
              writeOut.close();
              }catch (IOException e) {
            // TODO Auto-generated catch block
               e.printStackTrace();
              }
       }

	
    
}
