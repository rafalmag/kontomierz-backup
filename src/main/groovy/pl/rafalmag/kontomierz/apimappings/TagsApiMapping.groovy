package pl.rafalmag.kontomierz.apimappings

import pl.rafalmag.kontomierz.importers.ObjectWithListImporter

import javax.inject.Inject

class TagsApiMapping extends ApiMapping {
    @Inject
    TagsApiMapping(ObjectWithListImporter importer) {
        super("tags", "/k4/tags.json", "tags", importer, "name")
    }
}
