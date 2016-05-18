package pl.rafalmag.kontomierz

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.IndexOptions
import org.bson.BsonDocument
import org.bson.Document

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Exporter {

    @Inject
    MongoDatabase db;

    public void export(ApiMapping apiMapping, List<Map> json) {
        MongoCollection collection = db.getCollection(apiMapping.getCollectionName())
        collection.createIndex(BsonDocument.parse("{ \"id\": 1 }"), new IndexOptions().unique(true))
        List<Document> dbObjects = json.collect { new Document(it.get(apiMapping.getObjectName())) }
        println dbObjects
        collection.insertMany(dbObjects);
    }
}
