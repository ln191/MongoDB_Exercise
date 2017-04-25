/**
 * Created by l on 24-04-2017.
 */
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Indexes;
import org.bson.Document;

public class MongoProgram {

    public static void main(String[] args) {
        MongoClientURI connStr = new MongoClientURI("mongodb://localhost:27017");
        MongoClient mongoClient = new MongoClient(connStr);

        MongoDatabase db = mongoClient.getDatabase("social_net");
        MongoCollection<Document> collection = db.getCollection("tweets");
        //Create index on tweeter users if non already exist , makes it faster to search and count users
        collection.createIndex(Indexes.descending("user"));

        Document myDoc = collection.find().first();
        System.out.println(myDoc.toJson());
    }

    static void  CountTwitterUsers(MongoCollection<Document> collection)
    {

    }
}
