package pl.rafalmag.kontomierz.importers

import pl.rafalmag.kontomierz.apimappings.ApiMapping

import javax.inject.Inject

class MergingImporter implements Importer {

    Collection<ListWithObjectsImporter> importers

    @Inject
    MergingImporter(Set<ListWithObjectsImporter> importers) {
        this((Collection) importers)
    }

    MergingImporter(Collection<ListWithObjectsImporter> importers) {
        this.importers = importers
    }

    @Override
    List<Map> doImport(ApiMapping apiMapping) {
        importers.collect { it.doImport(apiMapping) }.flatten()
    }
}
