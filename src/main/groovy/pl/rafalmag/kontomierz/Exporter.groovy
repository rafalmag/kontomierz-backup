package pl.rafalmag.kontomierz

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.IndexOptions
import groovy.util.logging.Slf4j
import org.bson.BsonDocument
import org.bson.Document
import pl.rafalmag.kontomierz.apimappings.ApiMapping

import javax.inject.Inject
import javax.inject.Singleton

@Slf4j
@Singleton
class Exporter {

    @Inject
    MongoDatabase db;

    public void export(ApiMapping apiMapping, List<Map> json) {
        MongoCollection collection = db.getCollection(apiMapping.getCollectionName())
        collection.createIndex(BsonDocument.parse("{ \"$apiMapping.id\": 1 }"), new IndexOptions().unique(true))
        List<Document> dbObjects = json.collect {
            log.debug("$apiMapping.collectionName creating document for: {}", it)
            new Document(it)
        }
        log.debug("$apiMapping.collectionName documents to persist: {}", dbObjects)
        collection.insertMany(dbObjects);
    }
}
