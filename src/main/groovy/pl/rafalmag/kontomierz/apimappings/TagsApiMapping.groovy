package pl.rafalmag.kontomierz.apimappings

import pl.rafalmag.kontomierz.importers.TagsImporter

import javax.inject.Inject

class TagsApiMapping extends ApiMapping {
    @Inject
    TagsApiMapping(TagsImporter importer) {
        super("tags", "/k4/tags.json", "tags", importer, "name")
    }
}
