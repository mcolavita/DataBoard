import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

public class Test {
    private static DataBoard<MyData<?>> board;
    public static void main(String[] args) throws ImpossibleToPerform {
        /************************ /
         /      Prima batteria     /
         /         di test         /
         /*************************/
        System.out.println();
        System.out.println("Prima batteria di test:");
        board = new MyDataBoard1<MyData<?>>("passw");
        test();


        /************************ /
         /     Seconda batteria    /
         /         di test         /
         /*************************/
        System.out.println();
        System.out.println("Seconda batteria di test:");
        board = new Board2<>("passw");
        test();
    }


    private static void test() throws ImpossibleToPerform {
        // Posts
        MyData<String> post1 = new MyData<String>("Post1: Mare");
        MyData<String> post2 = new MyData<String>("Post2: Montagna");
        MyData<String> post3 = new MyData<String>("Post3: Viaggio");
        MyData<String> post4 = new MyData<String>("Post4: Estate");
        MyData<String> post5 = new MyData<String>("Post5: Coca-cola");



        // Freinds
        String friend1 = "FriendX";
        String friend2 = "FriendY";
        String friend3 = "FriendZ";

        Integer i = new Integer(0);

        System.out.println("Prova: creazione di 3 categorie");

        try{
            board.createCategory("Insta","passw");
            System.out.println("Categoria Insta creata con successo\n");
        }catch (ImpossibleToPerform e){
            System.out.println("Password errata o categoria esistente");
        }


        try{
            board.createCategory("Facebook","passw");
            System.out.println("Categoria Facebook creata con successo\n");
        }catch (ImpossibleToPerform e){
            System.out.println("Password errata o categoria esistente");
        }


        try{
            board.createCategory("Twitter","passw");
            System.out.println("Categoria creata con successo\n");
        }catch (ImpossibleToPerform e){
            System.out.println("Password errata o categoria esistente");
        }

        System.out.println("Prova: creazione categoria gia' esistente");

        try{
            board.createCategory("Insta","passw");
            System.out.println("Categoria creata con successo");
        }catch (ImpossibleToPerform e){
            System.out.println("Password errata o categoria esistente");
        }

        System.out.println("\nProva: Rimozione di una categoria esistente");
        try{
            board.removeCategory("Twitter","passw");
            System.out.println("Categoria eliminata con successo");
        }catch (ImpossibleToPerform e){
            System.out.println("Password errata o categoria non esistente");
        }


        System.out.println("\nProva: inserimento di due amici in Insta");
        try{
            board.addFriend("Insta","passw",friend1);
            System.out.println("Amico aggiunto con successo");
        }catch (ImpossibleToPerform e){
            System.out.println("Password errata o categoria esistente");
        } catch (FriendAlreadyPresent e){
            System.out.println("Amico gia'presente");
        }

        try{
            board.addFriend("Insta","passw",friend2);
            System.out.println("Amico aggiunto con successo");
        }catch (ImpossibleToPerform e){
            System.out.println("Password errata o categoria esistente");
        } catch (FriendAlreadyPresent e){
            System.out.println("Amico gia'presente");
        }

        System.out.println("\nProva: rimozione di un amico in Insta");
        try{
            board.removeFriend("Insta","passw",friend2);
            System.out.println("Amico rimosso con successo");
        }catch (ImpossibleToPerform e){
            System.out.println("Password errata o categoria esistente oppure amico non presente");
        }

        System.out.println("\nAggiunta friend3 in Insta");
        try{
            board.addFriend("Insta","passw",friend3);
            System.out.println("Amico aggiunto con successo");
        }catch (ImpossibleToPerform e){
            System.out.println("Password errata o categoria esistente");
        } catch (FriendAlreadyPresent e){
            System.out.println("Amico gia'presente");
        }




        System.out.println("\nProva: aggiunta di 3 post");
        try{
            board.put("passw",post1,"Insta");
            System.out.println("Dato aggiunto con successo");

        }catch(ImpossibleToPerform e){
            System.out.println("Operazione non riuscita");
        }

        try{
            board.put("passw",post3,"Insta");
            System.out.println("Dato aggiunto con successo");

        }catch(ImpossibleToPerform e){
            System.out.println("Operazione non riuscita");
        }

        try{
            board.put("passw",post2,"Insta");
            System.out.println("Dato aggiunto con successo");

        }catch(ImpossibleToPerform e){
            System.out.println("Operazione non riuscita");
        }

        System.out.println("\nProva: aggiunta post gia' presente");
        try{
            board.put("passw",post2,"Insta");
            System.out.println("Dato aggiunto con successo");

        }catch(ImpossibleToPerform e){
            System.out.println("Operazione non riuscita");
        }

        System.out.println("\nProva: prova get");
        try{
            Data get = board.get("passw",post3);
            System.out.println("Ho ottenuto la copia:");
            get.Display();
        } catch (ImpossibleToPerform e){
            System.out.println("Operazione non riuscita");
        }

        System.out.println("\nProva: rimozione di post2 da insta");
        try {
            board.remove("passw",post2);
            System.out.println("Dato rimosso con successo");
        }catch (ImpossibleToPerform e ){
            System.out.println("Operazione non riuscita");
        }

        System.out.println("\nProva: rimozione di un post non presente in bacheca");
        try {
            board.remove("passw",post2);
            System.out.println("Dato rimosso con successo");
        }catch (ImpossibleToPerform e ){
            System.out.println("Operazione non riuscita");
        }

        System.out.println("\nProva: lista dei post della categoria Insta");
        try{
            List<MyData<?>> lista = board.getDataCategory("passw","Insta");
            System.out.println("Nella categoria Insta sono presenti i seguenti dati:");
            for(MyData data: lista){
                data.Display();
            }
        } catch(ImpossibleToPerform e){
            System.out.println("Operazione non riuscita");
        }

        System.out.println("\nProva: inserimento like al post1 da parte di friend1 e friend3 ");
        try{
            board.insertLike(friend1,post3);
            System.out.println("Like lasciato");
        }catch (ImpossibleToPerform e){
            System.out.println("Operazione non riuscita");
        }catch (LikeAlredypresent e ){
            System.out.println("Like gia' presente");
        }

        try{
            board.insertLike(friend3,post3);
            System.out.println("Like lasciato");
        }catch (ImpossibleToPerform e){
            System.out.println("Operazione non riuscita");
        }catch (LikeAlredypresent e ){
            System.out.println("Like gia' presente");
        }


        System.out.println("\nProva: eccezione LikeAlreadyPresent");
        try{
            board.insertLike(friend1,post3);
            System.out.println("Like lasciato");
        }catch (ImpossibleToPerform e){
            System.out.println("Operazione non riuscita");
        }catch (LikeAlredypresent e ){
            System.out.println("Like gia' presente");
        }

        System.out.println("\nProva: Iteratore che ordina in base al numero di like");
        try{
            Iterator<MyData<?>> iterator = board.getIterator("passw");
            System.out.println("I dati ordinati per numero di like presenti in bacheca sono:");
            while(iterator.hasNext())
                iterator.next().Display();
        } catch (ImpossibleToPerform e){
            System.out.println("Operazione non riuscita");
        }

        System.out.println("\nProva: inserimento nuovi post in Facebook");
        try{
            board.put("passw",post4,"Facebook");
            System.out.println("Dato aggiunto con successo");

        }catch(ImpossibleToPerform e){
            System.out.println("Operazione non riuscita");
        }

        try{
            board.put("passw",post5,"Facebook");
            System.out.println("Dato aggiunto con successo");

        }catch(ImpossibleToPerform e){
            System.out.println("Operazione non riuscita");
        }

        System.out.println("\nProva: Inserimento friend3 in categoria Facebook");
        try{
            board.addFriend("Facebook","passw",friend3);
            System.out.println("Amico aggiunto con successo");
        }catch (ImpossibleToPerform e){
            System.out.println("Password errata o categoria esistente");
        } catch (FriendAlreadyPresent e){
            System.out.println("Amico gia'presente");
        }

        System.out.println("\nProva: Dati che vede friend3 nella bacheca");
        try{
            Iterator<MyData<?>> iterator = board.getFriendIterator(friend3);
            System.out.println(friend3 + " nella sua bacheca vede i seguenti dati:");
            while(iterator.hasNext())
                iterator.next().Display();
        }catch (ImpossibleToPerform e){
            System.out.println("Operazione non riuscita");
        }

    }

}



