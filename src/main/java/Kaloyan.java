
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Christoffer
 */
public class Kaloyan {

    MongoClient mongoClient = new MongoClient("localhost", 27017);
    DB database = mongoClient.getDB("social_net");
    DBCollection collection = database.getCollection("tweeets");

    List<Object> myList = collection.distinct("user");
    List<String> mymyList = new ArrayList<String>();

    public void getUsers() {
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("user", "NO_QUERY");

        for (Object list : myList) {
            mymyList.add(list.toString());
            // System.out.println(list);
        }
        //  System.out.println("myList size is : " + myList.size());

        //System.out.println("mymyList users are : " + mymyList.size());
    }

    public static void main(String[] args) {
        Kaloyan kal = new Kaloyan();
        kal.getUsers();
        kal.top10ActiveUsers();
        //kal.top10LinkingUsers();
    }

    public void top10ActiveUsers() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        DB database = mongoClient.getDB("social_net");
        DBCollection collection = database.getCollection("tweeets");

        Result[] res = new Result[mymyList.size()];
        int k = 0;
        for (String names : mymyList) {

            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("user", names);
            int i = collection.find(searchQuery).count();
//            String[] a = {Integer.toString(i),names};
//            List.add(a);

            Result r = new Result(i, names);
            res[k] = r;
            k++;

//            System.out.println("Name is" +names  + "count : " + i );
        }
        Arrays.sort(res);

        System.out.println(res[0].name + " " + res[0].value);
        System.out.println(res[1].name + " " + res[1].value);
        System.out.println(res[2].name + " " + res[2].value);
        System.out.println(res[3].name + " " + res[3].value);
        System.out.println(res[4].name + " " + res[4].value);
        System.out.println(res[5].name + " " + res[5].value);
        System.out.println(res[6].name + " " + res[6].value);
        System.out.println(res[7].name + " " + res[7].value);
        System.out.println(res[8].name + " " + res[8].value);
        System.out.println(res[9].name + " " + res[9].value);

    }

    public void top5HappyUsers() {

    }

    public void top5SadUsers() {

    }

    public void top5MostMentionedUsers() {

    }

    public void top10LinkingUsers() {
       
        Result[] res = new Result[mymyList.size()];
        int k = 0;
        
        for (String names : mymyList) {
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("user", names);

            DBCursor c = collection.find(searchQuery);
            int count = 0;
            while (c.hasNext()) {
            if(c.next().toString().contains("@"))
            {
            //System.out.println(c.next());
             count ++;
            }
        }
            
         Result r = new Result(count, names);
         res[k] = r;
         k++;

    }
        Arrays.sort(res);

        System.out.println(res[0].name + " " + res[0].value);
        System.out.println(res[1].name + " " + res[1].value);
        System.out.println(res[2].name + " " + res[2].value);
        System.out.println(res[3].name + " " + res[3].value);
        System.out.println(res[4].name + " " + res[4].value);
        System.out.println(res[5].name + " " + res[5].value);
        System.out.println(res[6].name + " " + res[6].value);
        System.out.println(res[7].name + " " + res[7].value);
        System.out.println(res[8].name + " " + res[8].value);
        System.out.println(res[9].name + " " + res[9].value);

    }
}
