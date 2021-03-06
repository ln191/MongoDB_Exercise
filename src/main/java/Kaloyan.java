
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import org.bson.Document;

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

        for (Object list : myList) {
            mymyList.add(list.toString());
            // System.out.println(list);
        }
        System.out.println("myList size is : " + myList.size());

        System.out.println("mymyList users are : " + mymyList.size());
    }

    public static void main(String[] args) {
        Kaloyan kal = new Kaloyan();
        kal.getUsers();
     
        
      
        //System.out.println("--------------------");
        //kal.top10ActiveUsers();
        //System.out.println("--------------------");   
        //kal.top10LinkingUsers();
        kal.top5MostMentionedUsers();
        //kal.top5HappySadUsers();
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

    public void top5HappySadUsers() {
        Result[] res = new Result[mymyList.size()];
        int k = 0;

        for (String names : mymyList) {

            BasicDBObject searchQuery = new BasicDBObject();

            searchQuery.put("user", names);
            DBCursor iterable = collection.find(searchQuery);

            int count = 0;

            while (iterable.hasNext()) {
                int polarity = Integer.parseInt(iterable.next().get("polarity").toString());
                count += (polarity - 2);
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

        System.out.println(res[mymyList.size() - 1].name + " " + res[mymyList.size() - 1].value);
        System.out.println(res[mymyList.size() - 2].name + " " + res[mymyList.size() - 2].value);
        System.out.println(res[mymyList.size() - 3].name + " " + res[mymyList.size() - 3].value);
        System.out.println(res[mymyList.size() - 4].name + " " + res[mymyList.size() - 4].value);
        System.out.println(res[mymyList.size() - 5].name + " " + res[mymyList.size() - 5].value);

    }

    public void top5SadUsers() {

    }

    public void top5MostMentionedUsers() {
          
        List<Result> results = new ArrayList<Result>();
        Document regQuery = new Document();
        regQuery.append("$regex", "^(?i)" + "@");
        BasicDBObject searchQuery = new BasicDBObject();

        searchQuery.append("text", regQuery);
        DBCursor iterable = collection.find(searchQuery);
        int errorCount = 0;
        while (iterable.hasNext()) {
            //System.out.println(iterable.next());

            try {
                String s = iterable.next().get("text").toString();
                int start = s.indexOf("@") + 1;
                int end = s.indexOf(" ");
                String ss = s.substring(start, end);
                //System.out.println(ss);
                
                Result r = new Result(1, ss); 
               
                    Boolean b = false;
                    for(Result rr : results)
                    {
                        if(rr.equals(r))
                        {
                            rr.value = rr.value + 1;
                            b = true;
                        }
                    }
                    if(b == false)
                    {
                        results.add(r);
                    }
                

            } catch (Exception e) {

                //System.out.println(iterable.next().get("text").toString());
                errorCount++;

            }

        }
        System.out.println(errorCount);
        
        Collections.sort(results);
        
        System.out.println(results.get(0) + " " + results.get(0).value);
        System.out.println(results.get(1) + " " + results.get(1).value);
        System.out.println(results.get(2) + " " + results.get(2).value);
        System.out.println(results.get(3) + " " + results.get(3).value);
        System.out.println(results.get(4) + " " + results.get(4).value);
        System.out.println(results.get(5) + " " + results.get(5).value);
        System.out.println(results.get(6) + " " + results.get(6).value);
        System.out.println(results.get(7) + " " + results.get(7).value);
        System.out.println(results.get(8) + " " + results.get(8).value);
        System.out.println(results.get(9) + " " + results.get(9).value);
        
        System.out.println(results.get(results.size() - 1) + " " + results.get(results.size()-1).value);
        System.out.println(results.get(results.size() - 2) + " " + results.get(results.size()-2).value);
        System.out.println(results.get(results.size() - 3) + " " + results.get(results.size()-3).value);
        System.out.println(results.get(results.size() - 4) + " " + results.get(results.size()-4).value);
        System.out.println(results.get(results.size() - 5) + " " + results.get(results.size()-5).value);
        System.out.println(results.get(results.size() - 6) + " " + results.get(results.size()-6).value);
        System.out.println(results.get(results.size() - 7) + " " + results.get(results.size()-7).value);
        System.out.println(results.get(results.size() - 8) + " " + results.get(results.size()-8).value);
        System.out.println(results.get(results.size() - 9) + " " + results.get(results.size()-9).value);
        System.out.println(results.get(results.size() - 10) + " " + results.get(results.size()-10).value);
//        Result[] res = new Result[mymyList.size()];
//        int k = 0;
//
//        for (String names : mymyList) {
//            Document regQuery = new Document();
//            regQuery.append("$regex", "^(?i)" + "@" + Pattern.quote(names));
//            regQuery.append("$options", "i");
//            BasicDBObject searchQuery = new BasicDBObject();
//
//            searchQuery.append("text", regQuery);
//            DBCursor iterable = collection.find(searchQuery);
//
//            int count = 0;
//
//            while (iterable.hasNext()) {
//                System.out.println(iterable.next());
//                count++;
//            }
//
//            Result r = new Result(count, names);
//            res[k] = r;
//            k++;
//        }

//        Arrays.sort(res);
//
//        System.out.println(res[0].name + " " + res[0].value);
//        System.out.println(res[1].name + " " + res[1].value);
//        System.out.println(res[2].name + " " + res[2].value);
//        System.out.println(res[3].name + " " + res[3].value);
//        System.out.println(res[4].name + " " + res[4].value);
//        System.out.println(res[5].name + " " + res[5].value);
//        System.out.println(res[6].name + " " + res[6].value);
//        System.out.println(res[7].name + " " + res[7].value);
//        System.out.println(res[8].name + " " + res[8].value);
//        System.out.println(res[9].name + " " + res[9].value);
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
                if (c.next().toString().contains("@")) {
                    //System.out.println(c.next());
                    count++;
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
