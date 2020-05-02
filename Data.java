public interface Data<E> {

    public E getData();
    public int getLikes();
    public void Display();
    public void insertLikes(String friend) throws LikeAlredypresent;
    public Data cloneData();
    public boolean equals(Object obj);

}
