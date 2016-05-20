package pl.rafalmag.kontomierz.apimappings;

import pl.rafalmag.kontomierz.importers.Importer

import javax.inject.Inject;


class MergingImporter extends Importer {

    Collection<Importer> importers

    @Inject
    MergingImporter(Set<Importer> importers) {
        this((Collection) importers)
    }

//    @Inject
    MergingImporter(Collection<Importer> importers) {
        this.importers = importers
    }

    @Override
    List<Map> doImport(ApiMapping apiMapping) {
        importers.collect { it.doImport(apiMapping) }.flatten()
    }
}
