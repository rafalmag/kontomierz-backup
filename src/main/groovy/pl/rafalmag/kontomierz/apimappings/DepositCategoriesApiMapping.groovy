package pl.rafalmag.kontomierz.apimappings

import pl.rafalmag.kontomierz.importers.DepositCategoriesImporter

import javax.inject.Inject

class DepositCategoriesApiMapping extends ApiMapping {

    @Inject
    DepositCategoriesApiMapping(DepositCategoriesImporter importer) {
        super("categories_deposit", "/k4/categories.json", "categories", importer)
    }
}
