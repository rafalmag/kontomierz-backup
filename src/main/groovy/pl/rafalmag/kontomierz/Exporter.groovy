package pl.rafalmag.kontomierz

import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import com.mongodb.client.model.IndexOptions
import com.mongodb.client.model.UpdateOptions
import groovy.util.logging.Slf4j
import org.bson.BsonDocument
import org.bson.Document
import org.bson.conversions.Bson
import pl.rafalmag.kontomierz.apimappings.ApiMapping

import javax.inject.Inject
import javax.inject.Singleton

@Slf4j
@Singleton
class Exporter {

    @Inject
    MongoDatabase db

    public void export(ApiMapping apiMapping, List<Map> json) {
        MongoCollection collection = db.getCollection(apiMapping.getCollectionName())
        collection.createIndex(BsonDocument.parse("{ \"$apiMapping.id\": 1 }"), new IndexOptions().unique(true))
        List<Document> documents = json.collect {
            log.debug("$apiMapping.collectionName creating document for: {}", it)
            new Document(it)
        }
        log.debug("$apiMapping.collectionName documents to persist: {}", documents)
        documents.each {
            Bson query = getQuery(apiMapping, it.get(apiMapping.id))
            def result = collection.replaceOne(query, it, new UpdateOptions().upsert(true))
            boolean updated = result.getUpsertedId() == null
            if (updated) {
                log.info("Updated existing document with id={}", it.get(apiMapping.id))
            } else {
                log.debug("Inserted new document")
            }
        }
    }

    protected Bson getQuery(ApiMapping apiMapping, id) {
        def query
        if (id instanceof Number) {
            query = "{ \$or: [ { \"$apiMapping.id\": $id }, { \"$apiMapping.id\": \"$id\" } ] }"
        } else {
            query = "{ \"$apiMapping.id\": \"$id\" }"
        }
        BsonDocument.parse(query)
    }
}
