/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ufrsciencestech.sgd.modele;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import static com.mongodb.client.model.Filters.eq;
import java.util.Arrays;
import java.util.ArrayList;
import org.bson.Document;
import org.bson.conversions.Bson;
/**
 *
 * @author tf872254
 */
public class GestionBD {
    final private String id;
    private MongoDatabase db;
    
    public GestionBD(String idC){
        id = idC;
        
        initConnexion();
    }
    
    public MongoDatabase getDB(){
        return this.db;
    }
    
    private void initConnexion(){
        char [ ] pass= new char[10];
        pass = id.toCharArray();
        MongoCredential credential = MongoCredential.createCredential(id, id, pass);
        MongoClient client = new MongoClient(new ServerAddress("mongo", 27017), Arrays.asList(credential));
        db= client.getDatabase(id);        
    }
    
    public void insertionBD(String collection, Document d){
        db.getCollection(collection).insertOne(d);
    }
    
    public void insertionMultipleBD(String collection, ArrayList<Document> ld){
        db.getCollection(collection).insertMany(ld);
    }
    
    public void suppressionDocument(String collection, Bson bson){
        db.getCollection(collection).deleteOne(bson);
    }
    
    public MongoCursor rechercheDocument(String collection, Bson b){
        return db.getCollection(collection).find(b).iterator();
    }

    
    
    public void afficherCollection(){
        // Affichage de la liste des collections de la base
        for (String name : db.listCollectionNames()) { 
            System.out.println(name); 
        }
    }

    void miseAJour(String collection, String jeu, float note, String avis, String pseudo, String mail) {
        //Document d = db.getCollection("jeuxvideo").find(eq("nom", jeu)).first();
        Document docUser = new Document("pseudo", pseudo).append("mail", mail);
        //Document docAAjouter = new Document("note", note).append("commentaire", avis).append("utilisateur", docUser);
        Document docAvis = new Document("note", note).append("commentaire", avis).append("utilisateur", docUser);
        Document docAAjouter = new Document("$addToSet", new Document("avis", docAvis));
        db.getCollection("jeuxvideo").updateOne(eq("nom", jeu), docAAjouter);
        //doc.replace("avis", ald);
        //this.insertionBD("jeuxvideo", doc);
    }
}
