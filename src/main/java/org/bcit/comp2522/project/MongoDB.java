package org.bcit.comp2522.project;

import com.mongodb.ConnectionString;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.reactivestreams.client.MongoClients;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;
import com.mongodb.MongoClientSettings;
import org.bson.Document;

import java.time.LocalDateTime;

import static com.mongodb.client.model.Filters.eq;

/**
 * Class for MongoDB client.
 *
 * @author Taswinder Dhaliwal & Alan Fung & Noufil Saqib
 * @version 1.0.0
 */
public class MongoDB {
  private MongoCollection<Document> collection;
  private String nameValue;
  private int scoreValue;
  private int waveValue;
  private static final String USER = System.getenv("USER");
  private static final String PASS = System.getenv("PASS");
  private Window window;
  private String json = "";

  /**
   * MongoDB client constructor.
   *
   * @param window the window
   */
  public MongoDB(Window window) {
    this.window = window;
  }

  public void run() {
    // MongoDB database connection credentials
    ConnectionString connectionString = new ConnectionString(
            "mongodb+srv://" + USER + ":" + PASS + "@cluster0.7uhm8ow.mongodb.net/?retryWrites=true&w=majority"
    );

    // Settings for connecting to MongoDB
    MongoClientSettings settings = MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .serverApi(ServerApi.builder()
                    .version(ServerApiVersion.V1)
                    .build())
            .build();

    // Connect to MongoDB
    MongoClient mongoClient = MongoClients.create(settings);

    // Get the leaderboard database
    MongoDatabase database = mongoClient.getDatabase("leaderboard");

    // Get the rankings collection
    collection = database.getCollection("rankings");
  }

  /**
   * Inserts a document into the database collection.
   *
   * @param collection the collection
   */
  private void insertDoc(MongoCollection<Document> collection) {
    Document doc = new Document("name", nameValue)
            .append("score", scoreValue)
            .append("wave", waveValue)
            .append("created_on", LocalDateTime.now());

    // Insert document into collection asynchronously
    collection.insertOne(doc)
            .subscribe(new SubscriberHelpers.OperationSubscriber<InsertOneResult>());
  }

  /**
   * Creates a document to be inserted into the database collection.
   *
   * @param v1 the name
   * @param v2 the score
   */
  public void createDoc(String v1, int v2, int v3) {
    this.nameValue = v1;
    this.scoreValue = v2;
    this.waveValue = v3;

    if (!nameValue.equals("")) {
      insertDoc(collection);
    }
  }

  /**
   * Stores documents from database as json string and sends it to window.
   * @param doc the documents as json
   */
  public void getDocuments(String doc) {
    if (json.equals("")) {
      json = json + doc;
    } else {
      json = json + "," + doc;
    }

    window.getLeaderboard(json);
  }

  /**
   * Clears the leaderboard json.
   */
  public void clearDocuments() {
    json = "";
  }

  /**
   * Calls functions to retrieve documents from the database
   * in order to create the leaderboard.
   */
  public void createLeaderboard() {
    // Get the name and score of all documents in descending order
    collection.find()
            .projection(new Document("name", 1)
                    .append("score", 1)
                    .append("wave", 1)
                    .append("_id", 0))
            .sort(Sorts.descending("score"))
            .subscribe(new SubscriberHelpers.PrintDocumentSubscriber(this));
  }
}
