import javax.management.ListenerNotFoundException;
import javax.swing.plaf.synth.ColorType;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.*;
public class MyDataBoard1<E extends Data<?>> implements DataBoard<E>{

    private String password;
    private ArrayList<Category<E>> board;
    private int dim;


    public MyDataBoard1(String passw){
        this.password=passw;
        this.dim=0;
        this.board = new ArrayList<Category<E>>();
    }

    public void createCategory(String Category, String passw) throws ImpossibleToPerform, NullPointerException {
        if(Category == null || passw == null)
            throw new NullPointerException();
        if(this.findCategory(Category))
            throw new ImpossibleToPerform("Categoria gia' presente");
        if(!this.password.equals(passw))
            throw new ImpossibleToPerform("Password errata!");
        this.board.add(new Category(Category));

    }

    public void removeCategory(String Category, String passw)  throws ImpossibleToPerform, NullPointerException {
        if(Category == null || passw == null)
            throw new NullPointerException();
        if(!this.findCategory(Category))
            throw new ImpossibleToPerform("Categoria non esistente");
        if(!this.password.equals(passw))
            throw new ImpossibleToPerform("Password errata!");
        int i=0;
        while(!this.board.get(i).getName().equals(Category)){
            i++;
        }
        this.board.remove(i);
    }

    @Override
    public void addFriend(String Category, String passw, String friend) throws ImpossibleToPerform, NullPointerException, FriendAlreadyPresent {
        if(Category == null || passw == null || friend == null)
            throw new NullPointerException();
        if(!this.findCategory(Category))
            throw new ImpossibleToPerform("Categoria non esistente");
        if(!this.password.equals(passw))
            throw new ImpossibleToPerform("Password errata!");
        if(findFriend(Category,friend))
            throw new FriendAlreadyPresent("Amico gia' presente");
        int i = 0;
        while(!this.board.get(i).getName().equals(Category)){
            i++;
        }
        this.board.get(i).addFriend(friend);

    }


    public void removeFriend(String Category, String passw, String friend) throws ImpossibleToPerform, NullPointerException {
        if(Category == null || passw == null || friend == null)
            throw new NullPointerException();
        if(!this.findCategory(Category))
            throw new ImpossibleToPerform("Categoria non esistente");
        if(!this.password.equals(passw))
            throw new ImpossibleToPerform("Password errata!");
        if(!findFriend(Category,friend))
            throw new ImpossibleToPerform("Operazione non possibile");
        int i = 0 ;
        while(!this.board.get(i).getName().equals(Category)){
            i++;
        }
        int j=0;
        while(!this.board.get(i).firendsList().get(j).equals(friend))
            j++;
        this.board.get(i).removeFriend(j);
    }


    public boolean put(String passw, E dato, String categoria) throws ImpossibleToPerform, NullPointerException {
        if(passw == null || dato == null || categoria== null)
            throw new NullPointerException();
        if(!this.password.equals(passw))
            throw new ImpossibleToPerform("Password errata");
        int i=0;
        while(!board.get(i).getName().equals(categoria) && i<board.size()){
            i++;
        }
        int j;
        for(j=0;j<board.get(i).getSizeCollection();j++){
            if(board.get(i).datasList().contains(dato))
                throw new ImpossibleToPerform("Dato gia' presente");
        }
        this.board.get(i).datasList().add(dato);
        return true;
    }

    public E get(String passw, E dato) throws ImpossibleToPerform, NullPointerException {
            if(passw==null || dato == null)
                throw new NullPointerException();
            if(!this.password.equals(passw))
                throw new ImpossibleToPerform("Password errata");
            for(Category cat : board ){
                if(cat.datasList().contains(dato))
                    return (E) dato.cloneData();
            }
            throw new ImpossibleToPerform("Dato non presente");
    }


    public E remove(String passw, E dato) throws ImpossibleToPerform {
        if(passw==null || dato == null)
            throw new NullPointerException();
        if(!this.password.equals(passw))
            throw new ImpossibleToPerform("Password errata");
        boolean finder = false;
        for(Category cat : board){
            if(cat.datasList().contains(dato)) {
                finder=true;
                cat.datasList().remove(dato);
            }
        }
        if(finder) return (E) dato.cloneData();
        throw new ImpossibleToPerform("Dato non presente");
    }




    public List<E> getDataCategory(String passw, String Category) throws ImpossibleToPerform,NullPointerException {
        if(passw==null || Category == null )
            throw new NullPointerException();
        if(!this.findCategory(Category))
            throw new ImpossibleToPerform("Categoria non presente");
        if(!this.password.equals(passw))
            throw new ImpossibleToPerform("Password errata");
        int i =0;
        List<E> all_datas = null;
        for(i=0;i<this.board.size();i++){
            if(this.board.get(i).getName().equals(Category)) {
                all_datas = this.board.get(i).datasList();
                return all_datas;
            }
        }
        return all_datas;
    }

    @Override
    public Iterator<E> getIterator(String passw) throws ImpossibleToPerform {
        if (passw == null) throw new NullPointerException();
        if (!this.password.equals(passw))
            throw new ImpossibleToPerform("Password errata");

        SortedSet<E> showcase = new TreeSet<E> ((Comparator<? super E>) new Sort());
        for(Category cat: this.board){
            if(cat.datasList()!=null){
                showcase.addAll(cat.datasList());
            }
        }
        return Collections.unmodifiableSortedSet(showcase).iterator();
    }

    @Override
    public void insertLike(String friend, E data) throws LikeAlredypresent, ImpossibleToPerform {
        if (friend == null || data == null)
            throw new NullPointerException();
        boolean findFriend = false;
        boolean findData=false;
        for(Category cat : board){
            if(cat.firendsList().contains(friend))
                findFriend=true;
            if(cat.datasList().contains(data))
                findData=true;
            if(cat.firendsList().contains(friend) && cat.datasList().contains(data))
                data.insertLikes(friend);
        }
        if(!findFriend) throw new ImpossibleToPerform("Amico non presente");
        if(!findData) throw new ImpossibleToPerform("Dato non presente");
    }

    @Override
    public Iterator<E> getFriendIterator(String friend) throws ImpossibleToPerform {
        if(friend == null) throw new NullPointerException();

        ArrayList<E> all_datas = new ArrayList<E>();
        int i;
        boolean finder=true;
        for(i=0;i<board.size();i++){
            if(this.board.get(i).firendsList().contains(friend)) {
                all_datas.addAll(this.board.get(i).datasList());
                finder=true;
            }
        }
        if(!finder)
            throw new ImpossibleToPerform("Amico non presente");
        return all_datas.iterator();
    }


    public boolean findCategory(String name){
        boolean finder = false;
        for(Category  cat : board){
            if(name.equals(cat.getName()))
                finder=true;
        }
        return finder;
    }


    private boolean findFriend(String Category, String friend) {
        boolean finder = false;
        if (!findCategory(Category))
            return finder;
        int i = 0;
        while (!(board.get(i).getName().equals(Category)))
            i++;
        int j;
        for (j = 0; j < board.get(i).getSizeFriend(); j++) {
            if (board.get(i).getFriend(j).equals(friend))
                finder = true;
        }
        return finder;
    }


    private void removeData(E dato){
        int i=0;
        for(i=0;i<board.size();i++) {
            int j = 0;
            for (j = 0; j < board.get(i).getSizeCollection(); j++) {
                if (board.get(i).datasList().get(j).getData().equals(dato))
                    this.board.get(i).datasList().remove(new MyData(dato));
            }
        }
        return;
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