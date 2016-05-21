package pl.rafalmag.kontomierz.importers

import com.mongodb.client.MongoDatabase
import groovy.util.logging.Slf4j
import groovyx.net.http.RESTClient
import org.bson.BsonDocument
import pl.rafalmag.kontomierz.ApiKey
import pl.rafalmag.kontomierz.apimappings.ApiMapping
import pl.rafalmag.kontomierz.apimappings.ScheduledTransactionsApiMapping
import pl.rafalmag.kontomierz.apimappings.SchedulesApiMapping

import javax.inject.Inject

@Slf4j
class SchedulesImporter implements Importer {

    @Inject
    RESTClient restClient

    @ApiKey
    @Inject
    String apiKey

    @Inject
    MongoDatabase db

    @Inject
    ScheduledTransactionsApiMapping scheduledTransactionsApiMapping

    @Override
    List<Map> doImport(ApiMapping apiMapping) {
        List<String> scheduleIdsToImport = getScheduleIdsToImport()
        log.debug(scheduleIdsToImport.size() + " schedules to import")
        scheduleIdsToImport.collect {
            importForId(apiMapping, it as String)
        }
    }

    List<String> getScheduleIdsToImport() {
        def scheduledTransactions = db.getCollection(scheduledTransactionsApiMapping.collectionName)
        scheduledTransactions
                .find()
                .projection(BsonDocument.parse("{$scheduledTransactionsApiMapping.id : 1}"))
                .collect { it.get(scheduledTransactionsApiMapping.id) } as List<String>
    }

    Map importForId(ApiMapping apiMapping, String scheduleId) {
        def path = apiMapping.getUrlPath().replace(SchedulesApiMapping.ID_SUBSTITUTION_MARKER, scheduleId)
        def response = restClient.get(path: path, query: [
                api_key: apiKey
        ])
        assert response.status == 200
        log.debug("Received response for $scheduleId scheduleId {}", response.getData())
        response.getData().get(apiMapping.objectName)
    }
}
