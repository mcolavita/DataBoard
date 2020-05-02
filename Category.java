import java.util.ArrayList;


public class Category<E extends Data>{

    private String name;
    private ArrayList<E> datas;
    private ArrayList<String> friends;

    public Category(String name){
        this.name= name;
        this.datas = new ArrayList<E>();
        this.friends = new ArrayList<String>();
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<String> firendsList(){
        return this.friends;
    }

    public ArrayList<E> datasList(){
        return this.datas;
    }

    public int getSizeFriend(){
        return this.friends.size();
    }

    public int getSizeCollection(){
        return this.datas.size();
    }

    public String getFriend(int i){
        return this.friends.get(i);
    }

    public void addFriend(String friend){
        this.friends.add(friend);
    }

    public void removeFriend(int i){
        this.friends.remove(i);
    }

    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Category other = (Category) obj;
        if(name == null) {
            if(other.name != null)
                return false;
        }
        return true;
    }
}
