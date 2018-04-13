package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 *
 * @author Faisal Burhan
 */
public class Swipe {

    /**
     *
     */
    protected final int id;

    Comparable<Swipe> SwipeDateTimeComparator;
    
    /**
     *
     */
    protected String cardId;

    /**
     *
     */
    protected String room;

    /**
     *
     */
    protected final Calendar swipeDateTime;
    
    private static int lastSwipeIdUsed = 0;
    
    static final char EOLN='\n';       
    
    static final String QUOTE="\"";    
    
    /**
     *
     */
    public Swipe() {
        this.id = ++lastSwipeIdUsed;
        this.cardId = "Unknown";
        this.room = "Unknown";
        this.swipeDateTime = getNow();
    }
    
    /**
     *
     * @param cardId
     * @param room
     */
    public Swipe(String cardId, String room) {
        this.id = ++lastSwipeIdUsed;
        this.cardId = cardId;
        this.room = room;        
        this.swipeDateTime = getNow();
    }    
    
    /**
     *
     * @param swipeId
     * @param cardId
     * @param room
     * @param swipeDateTime
     */
    public Swipe(int swipeId, String cardId, String room, Calendar swipeDateTime) {
        this.id = swipeId;
        this.cardId = cardId;
        this.room = room;
        this.swipeDateTime = swipeDateTime;
        if (swipeId > Swipe.lastSwipeIdUsed)
            Swipe.lastSwipeIdUsed = swipeId;          
    }     
    
    private Calendar getNow() {
        Calendar now = Calendar.getInstance();  
        return now;
    }    

    /**
     * @return the id
     */
    public int getId() {
        return this.id;
    }

    // Methods required: getters, setters, hashCode, equals, compareTo, comparator
    public String getCardId(){
        return this.cardId;
    }
    
   public void setCardId (String myCardId){
       this.cardId = myCardId;
   }
    
    public String getRoom(){
        return this.room;
    }
    
    public void setRoom (String myRoom){
        this.room = myRoom;
    }
    
    public Calendar getSwipeDateTime(){
        return this.swipeDateTime;
    }
    
    @Override
    public int hashCode(){
        final int prime = 29;
        int result = 1;
        result = prime * result + ((SwipeDateTimeComparator==null)?0 :SwipeDateTimeComparator.hashCode());
        result = prime * result + ((cardId==null)?0:cardId.hashCode());
        result = prime * result + id;
        result = prime * result + ((room==null)?0:room.hashCode());
        result = prime * result + ((swipeDateTime==null)?0:swipeDateTime.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object o){      
        Boolean status = false;
        status = (this == o)? true : false;
        status = (o == null)? false : true;
        status = (getClass() != o.getClass())? false : true;
        Swipe obj = (Swipe) o;
        if(SwipeDateTimeComparator == null) status = (obj.SwipeDateTimeComparator != null)? false : true;  
        else if(!SwipeDateTimeComparator.equals(obj.SwipeDateTimeComparator)) status = false;
        if(cardId == null) status = (obj.cardId != null)? false : true;
        else if(!cardId.equals(obj.cardId)) status = false;
        status = (id != obj.id)? false : true;
        if(room == null) status = (obj.room != null)? false : true;
        else if(!room.equals(obj.room)) status = false;
        if(swipeDateTime == null) status = (obj.swipeDateTime != null)? false : true;
        else if(!swipeDateTime.equals(obj.swipeDateTime)) status = false;
        return status;
    }
    
    /**
     *
     * @param calendar
     * @return
     */
        
    public static String formatSwipeDateTime(Calendar calendar) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        //Calendar now = Calendar.getInstance();  
        return dateFormat.format(calendar.getTime());
    }    

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "\nSwipe Id: " + this.id + " - Card Id: " + this.cardId + " - Room: " + this.room + " - Swiped: " + formatSwipeDateTime(this.swipeDateTime);
    }
    
    public String toString(char delimiter){
        return EOLN + Integer.toString(this.id) + delimiter + QUOTE + this.cardId + QUOTE + delimiter + QUOTE + this.room + QUOTE + delimiter + QUOTE + formatSwipeDateTime(this.swipeDateTime) + QUOTE;
    }
    
    /**
     * @param compareSwipe
     * @return 
     */
    
    public int compareTo(Swipe compareSwipe){
        return this.getSwipeDateTime().compareTo(compareSwipe.getSwipeDateTime());
    }
    
    
}
