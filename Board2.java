import java.util.*;

public class Board2<E extends Data<?>> implements DataBoard<E> {

    private String password;
    private HashMap<String,Category<E>> board;


    public Board2(String password){
        this.password= password;
        this.board = new HashMap<String,Category<E>>();
    }

    public void createCategory(String Category, String passw) throws ImpossibleToPerform {
        if(Category == null || passw == null)
            throw new NullPointerException();
        if(this.board.containsKey(Category))
            throw new ImpossibleToPerform("Categoria gia' presente");
        if(!this.password.equals(passw))
            throw new ImpossibleToPerform("Password errata");
        this.board.put(Category,new Category<>(Category));
    }

    @Override
    public void removeCategory(String Category, String passw) throws ImpossibleToPerform {
        if(Category == null || passw == null)
            throw new NullPointerException();
        if(!this.board.containsKey(Category))
            throw new ImpossibleToPerform("Non esiste la categoria richiesta");
        if(!this.password.equals(passw))
            throw new ImpossibleToPerform("Password errata");
        this.board.remove(Category);
    }

    public void addFriend(String Category, String passw, String friend) throws ImpossibleToPerform, FriendAlreadyPresent {
        if(Category == null || passw == null || friend == null)
            throw new NullPointerException();
        if(!this.board.containsKey(Category))
            throw new ImpossibleToPerform("Categoria non presente");
        if(!this.password.equals(passw))
            throw new ImpossibleToPerform("Password errata");
        if(this.board.get(Category).firendsList().contains(friend))
            throw new FriendAlreadyPresent("Amico presente nella categoria");
        this.board.get(Category).addFriend(friend);
    }

    @Override
    public void removeFriend(String Category, String passw, String friend) throws ImpossibleToPerform {
        if(Category == null || passw == null || friend == null)
            throw new NullPointerException();
        if(!this.board.containsKey(Category))
            throw new ImpossibleToPerform("Categoria non presente");
        if(!this.password.equals(passw))
            throw new ImpossibleToPerform("Password errata");
        if(!this.board.get(Category).firendsList().contains(friend))
            throw new ImpossibleToPerform("Amico non presente");
        this.board.get(Category).firendsList().remove(friend);
    }


    public boolean put(String passw, E dato, String categoria) throws ImpossibleToPerform {
        if(passw == null || dato == null || categoria == null)
            throw new NullPointerException();
        if(!this.password.equals(passw))
            throw new ImpossibleToPerform("Password errata");
        if(!this.board.containsKey(categoria))
            throw new ImpossibleToPerform("Categoria non presente");
        if(this.board.get(categoria).datasList().contains(dato))
            throw new ImpossibleToPerform("Dato gia' presente");
        this.board.get(categoria).datasList().add(dato);
        return true;
    }

    @Override
    public E get(String passw, E dato) throws ImpossibleToPerform {
        if(passw== null || dato == null)
            throw new NullPointerException();
        if(!this.password.equals(passw))
            throw new ImpossibleToPerform("Password errata");
        boolean finder=true;
        for(Category<E> cat: board.values()){
            if(cat.datasList().contains(dato))
                finder=true;
        }
        if(!finder)
            throw new ImpossibleToPerform("Dato non presente");
        return (E) dato.cloneData();
    }

    @Override
    public E remove(String passw, E dato) throws ImpossibleToPerform {
        if (passw == null || dato == null)
            throw new NullPointerException();
        if (!this.password.equals(passw))
            throw new ImpossibleToPerform("Password errata");
        boolean finder = false;
        for (Category<E> cat : this.board.values()) {
            if(cat.datasList().contains(dato)) {
                cat.datasList().remove(dato);
                finder = true;
            }
        }
        if(finder) return (E) dato.cloneData();
        throw new ImpossibleToPerform("Dato non presente");
    }


    @Override
    public List<E> getDataCategory(String passw, String Category) throws ImpossibleToPerform {
        if(passw == null || Category == null)
            throw new NullPointerException();
        if(!this.password.equals(passw))
            throw new ImpossibleToPerform("Password errata");
        if(!this.board.containsKey(Category))
            throw new ImpossibleToPerform("Categoria non presente");
        List<E> myList= this.board.get(Category).datasList();
        return myList;
    }


    public Iterator<E> getIterator(String passw) throws ImpossibleToPerform {
        if(passw == null)
            throw new NullPointerException();
        if(!this.password.equals(passw))
            throw new ImpossibleToPerform("Password errata");
        SortedSet<E> showcase = new TreeSet<E>((Comparator<? super E>) new Sort());
        for(Category cat : this.board.values()){
            if(cat.datasList()!=null)
                showcase.addAll(cat.datasList());
        }
        return Collections.unmodifiableSortedSet(showcase).iterator();
    }

    @Override
    public void insertLike(String friend, E data) throws LikeAlredypresent, ImpossibleToPerform {
        if(friend == null || data == null)
            throw new NullPointerException();
        boolean findFriend=false;
        boolean findData=false;
        for(Category cat : board.values()){
            if(cat.firendsList().contains(friend))
                findFriend=true;
            if(cat.datasList().contains(data))
                findData=true;
        }
    if(findFriend==true && findData==true)
        data.insertLikes(friend);
    if(!findFriend) throw new ImpossibleToPerform("Amico non presente");
    if(!findData) throw new ImpossibleToPerform("Dato non presente");
    }

    @Override
    public Iterator<E> getFriendIterator(String friend) throws ImpossibleToPerform {
        if(friend == null)
            throw new NullPointerException();
        boolean findFriend=false;
        ArrayList<E> all_datas = new ArrayList<E>();
        for(Category cat : board.values()){
            if(cat.firendsList().contains(friend)){
                all_datas.addAll(cat.datasList());
                findFriend=true;
            }
        }
        if(!findFriend)
            throw new ImpossibleToPerform("Amico non presente");
        return all_datas.iterator();
    }


    class Sort implements Comparator<MyData<?>> {

        @Override
        public int compare(MyData<?> o1, MyData<?> o2) {
            if (o2 == null) return -1;
            if (o1 == null) return 1;
            return o2.getLikes() - o1.getLikes();
        }
    }
}
