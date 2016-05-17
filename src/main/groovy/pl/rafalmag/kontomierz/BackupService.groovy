package pl.rafalmag.kontomierz

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BackupService {

    @Inject
    Importer importer;

    @Inject
    Exporter exporter;

    @Inject
    List<ApiMapping> apiMapping;

    public backup() {
        apiMapping.each {
            List<Map> json = importer.doImport(it)
            exporter.export(it, json)
        }

    }
}
