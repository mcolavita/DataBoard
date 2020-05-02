import java.util.Iterator;
import java.util.List;

public interface DataBoard<E extends Data<?>> {

    public void createCategory(String Category, String passw) throws ImpossibleToPerform;

    /**
     * @Requires: Category!= null && passw!=null
     * @Throws: Category == null || passw == null lancia l'eccezione NullPointerException
     * @Modifies: this
     * Effects: crea l'identita' di una categoria dati se non esiste gia'
     *
     */

    public void removeCategory(String Category, String passw) throws ImpossibleToPerform;

    /**
     * @Requires: Category!=null && passw!=null
     * @Throws: Category == null || passw == null lancia l'eccezione NullPointerExeption
     * @Modifies: this
     * @Effects: rimuove l'identita' di una categoria dati se esiste, altrimenti il metodo
     *           non ha effetto.
     */


    public void addFriend(String Category, String passw, String friend) throws ImpossibleToPerform, FriendAlreadyPresent;

    /**
     * @Requires: Category != null && passw!= null && friend!= null
     * @Throws: Category == null || passw || null || friend == null lancia
     *                      l'eccezione NullPointerExeption
     * @Modifies:this
     * @Effects: Aggiunge un amico ad una categoria dati se non e' presente nella lista della
     *           categoria, altrimenti il metodo non ha effetto se l'amico e' gia' presente
     */

    public void removeFriend(String Category, String passw, String friend) throws ImpossibleToPerform;

    /**
     * @Requires: Category != null && passw!= null && friend!= null
     * @Throws: Category == null || passw || null || friend == null lancia
     *          l'eccezione NullPointerExeption
     * @Modifies:this
     * @Effects: rimuove friend da Category, altrimenti non ha effetto poiche'
     *           friend non compare in category
     */

    public boolean put(String passw, E dato, String categoria) throws ImpossibleToPerform;

    /**
     * @Requires: passw!=null && dato!= null && categoria!=null && categoria deve
     *            essere presente nella collezione.
     * @Throws: passw == null || dato == null || categoria == null lancia l'eccezione
     *          NullPointerExeption
     *@Modifies: -
     *@Effects: Se l'elemento(dato) non e' presente nella categoria allora viene aggiunto
     *          e restituisce true se l'azione e' andata a buon fine, false altrimenti.
     */
    
    public E get(String passw, E dato) throws ImpossibleToPerform;

    /**
     * @Requires: passw!=null && dato!==null
     * @Throws: passw==null || dato==null lancia l'eccezione NullPointerExeption
     * @Modifies: -
     * @Effetcs: restituisce dato se questo appartiene alla collezione, altrimenti resituisce null
     */

    public E remove(String passw, E dato) throws ImpossibleToPerform;

    /**
     * @Requires: passw!= null && dato!= null
     * @Throws: passw == null || dato == null lancia l'eccezione NullPointerExeption
     * @Modifies: this
     * @Effetcs: elimina dato dalla collezione e lo restituisce altrimenti restituisce null
     */

    public List <E> getDataCategory(String passw, String Category) throws ImpossibleToPerform;

    /**
     * @Requires: passw!= null && Category!= null
     * @Throws: passw == null || Category == null lancia l'eccezione NullPointerExeption
     * @Modifies: -
     * @Effetcs: crea e restituisce la lista dei dati presenti nella categoria
     */


    public Iterator<E> getIterator(String passw) throws ImpossibleToPerform;

    /*** @Requires: passw!= null
     * @Throws: passw == null  lancia l'eccezione NullPointerExeption
     * @Modifies: -
     * @Effetcs: crea una collezione di dati ordinata rispetto ai like e restituisce un iteratore su di essa
     */

    public void insertLike(String friend, E data) throws LikeAlredypresent, ImpossibleToPerform;

    /*** @Requires: friend!= null && data!= null
     * @Throws: friend == null || data == null lancia l'eccezione NullPointerExeption
     * @Modifies: this
     * @Effetcs: incrementa il numero di like associati a data
     */

    public Iterator<E> getFriendIterator(String friend) throws ImpossibleToPerform;
    /**
     * * @Requires: friend!= null e friend deve comparire in una lista di amici di almeno una categoria
     *      * @Throws: friend== null lancia l'eccezione NullPointerExeption
     *      * @Modifies: -
     *      * @Effetcs: crea una collezione con tutti i dati presenti in bacheca e condivisi con friend e
     *                  restituisce un iteratore su di esse
     */
}


