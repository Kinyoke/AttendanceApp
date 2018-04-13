package repositories;

import daos.DAOTextImpl;
import java.util.HashSet;
import java.util.function.Predicate;
import model.Swipe;


public class Repository implements RepositoryInterface {
    private HashSet<Swipe> items;    
    
    public Repository() {
        this.items = new HashSet<>();       
    }
    
    public Repository(HashSet<Swipe> items) {        
        this.items = items;
    }
    
    public Repository(String filename) {
        this();
        // Create dao and execute load
        DAOTextImpl dao = new DAOTextImpl();
        this.items = dao.load(filename).getItems();
    }
    
    @Override
    public HashSet<Swipe> getItems() {        
        return this.items;
    }
    
    @Override
    public void setItems(HashSet<Swipe> items) {        
        this.items = items;
    }
    
    @Override
    public void add(Swipe item) {
        this.items.add(item);
    }
       
    @Override
    public void remove(int id) {
        Predicate<Swipe> predicate = e->e.getId() == id;       
        this.items.removeIf(predicate);
    }
    
    @Override
    public Swipe getItem(int id) {
        for (Swipe item:this.items) {
            if (item.getId() == id)
                return item;
        }
        return null;
    }
    
    @Override
    public String toString() {
        return "\nItems: " + this.items;
    }    
    
    @Override
    public void store(String filename) {       
        // create dao and execute store    
        DAOTextImpl dao = new DAOTextImpl();
        dao.store(filename, this);
    }        
}
