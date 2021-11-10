import com.mongodb.WriteConcernException;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int port;
        if (args.length < 1)
            port = 27017; // Default port for local MongoDB
        else {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return;
            }
        }
        String uri = "mongodb://localhost:" + port;
        try (MongoClient mongoClient = MongoClients.create(uri)) {
//            MongoDatabase database = mongoClient.getDatabase("admin");
//            try {
//                Bson command = new BsonDocument("ping", new BsonInt64(1));
//                Document commandResult = database.runCommand(command);
//                System.out.println("Connected successfully to server.");
//            } catch (MongoException me) {
//                System.err.println("An error occurred while attempting to run a command: " + me);
//            }
            MongoDatabase cbd = mongoClient.getDatabase("cbd");
            MongoCollection<Document> restaurants = cbd.getCollection("restaurants");
            Document testRestaurant = new Document("_id", new ObjectId());
            testRestaurant.append("localidade", "Test Location")
                    .append("gastronomia", "Test Gastronomy")
                    .append("nome", "Test Name")
                    .append("restaurant_id", "00000000")
                    .append("address",
                            new Document("building", "0000")
                                    .append("rua", "Test Street")
                                    .append("zipcode", "Test Zip Code")
                                    .append("coord", Arrays.asList(0, 0))
                    )
                    .append("grades", Arrays.asList(
                                    new Document("grade", "A")
                                            .append("score", 1)
                                            .append("date", new Date()),
                                    new Document("grade", "B")
                                            .append("score", 2)
                                            .append("date", new Date()),
                                    new Document("grade", "C")
                                            .append("score", 3)
                                            .append("date", new Date()),
                                    new Document("grade", "D")
                                            .append("score", 4)
                                            .append("date", new Date()),
                                    new Document("grade", "A")
                                            .append("score", 5)
                                            .append("date", new Date())
                            )
                    );
            insert(restaurants, testRestaurant);
        }
    }

    // Insert 1 document
    private static void insert(MongoCollection<Document> collection, Document document) {
        try {
            collection.insertOne(document);
        } catch (WriteConcernException e) {
            e.printStackTrace();
        }
    }

    // Insert multiple documents
    private static void insert(MongoCollection<Document> collection, List<Document> documents) {
        try {
            collection.insertMany(documents);
        } catch (WriteConcernException e) {
            e.printStackTrace();
        }
    }

    // Edit 1 document
//    private static void edit(MongoCollection<Document> collection, Document document)
}
