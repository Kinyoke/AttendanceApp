package daos;

//import repositories.Repository;

import repositories.Repository;


/**
 *
 * @author Faisal Burhan
 */
public interface DAOInterface {

    public Repository load(String filename);

    public void store(String filename, Repository repository);
}
