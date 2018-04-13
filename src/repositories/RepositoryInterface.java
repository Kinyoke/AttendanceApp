package repositories;

import java.util.HashSet;

import model.Swipe;

public interface RepositoryInterface {

    /**
     *
     * @param item
     */
    void add(Swipe item);

    /**
     *
     * @param id
     * @return
     */
    Swipe getItem(int id);

    HashSet<Swipe> getItems();

    /**
     *
     * @param id
     */
    
    void remove(int id);

    void setItems(HashSet<Swipe> items);

    /**
     *
     * @param filename
     */
    
    void store(String filename);

    /**
     *
     * @return
     */
    @Override
    String toString();
    
}
