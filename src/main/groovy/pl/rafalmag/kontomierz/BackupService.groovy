package pl.rafalmag.kontomierz

import com.mongodb.MongoBulkWriteException
import com.mongodb.client.MongoDatabase
import groovy.util.logging.Slf4j
import pl.rafalmag.kontomierz.apimappings.ApiMapping

import javax.inject.Inject
import javax.inject.Singleton

@Slf4j
@Singleton
class BackupService {

    @Inject
    Exporter exporter;

    @Inject
    Set<ApiMapping> apiMappings;

    @Inject
    Arguments arguments;

    @Inject
    MongoDatabase database;

    public backup() {
        if (arguments.drop) {
            database.drop()
        }

        apiMappings.each {
            log.info("Backing up $it.collectionName")
            List<Map> json = it.getImporter().doImport(it)
            try {
                exporter.export(it, json)
            } catch (MongoBulkWriteException e) {
                log.error("Could not save imported $it.collectionName, because of $e.message", e)
            }
        }
    }
}
