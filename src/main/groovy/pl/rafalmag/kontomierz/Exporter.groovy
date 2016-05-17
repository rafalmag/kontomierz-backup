package pl.rafalmag.kontomierz

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.Document

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Exporter {

    @Inject
    MongoDatabase db;

    public void export(ApiMapping apiMapping, List<Map> json) {
        MongoCollection collection = db.getCollection(apiMapping.getCollectionName())
        List<Document> dbObjects = json.collect { new Document(it) }
        println dbObjects
        collection.insertMany(dbObjects);
    }
}
