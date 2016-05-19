package pl.rafalmag.kontomierz.apimappings

import pl.rafalmag.kontomierz.importers.WithdrawalCategoriesImporter

import javax.inject.Inject

class WithdrawalCategoriesApiMapping extends ApiMapping {

    @Inject
    WithdrawalCategoriesApiMapping(WithdrawalCategoriesImporter importer) {
        super("categories_withdrawal", "/k4/categories.json", "categories", importer)
    }
}
