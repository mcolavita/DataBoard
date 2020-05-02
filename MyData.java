
import java.util.ArrayList;

public class MyData<E> implements Data<E> {

    private E element;
    private int num_likes;
    private ArrayList<String> friends_likes;


    public MyData(E element){
        this.element = element;
        this.num_likes=0;
        this.friends_likes = new ArrayList<String>();
    }


    public E getData(){
        return this.element;
    }

    public int getLikes(){
        return num_likes;
    }

    public void Display(){
        System.out.println(this.element.toString() + " "+ this.num_likes+"likes");

    }

    public void insertLikes(String friend) throws LikeAlredypresent {
        if(this.friends_likes.contains(friend)) throw new LikeAlredypresent("Like gia' presente");
        this.num_likes++;
        this.friends_likes.add(friend);
    }


    public Data cloneData(){
        return (Data) new MyData<E>(this.getData());
    }


}
