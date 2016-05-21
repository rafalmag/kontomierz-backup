package pl.rafalmag.kontomierz.apimappings

import pl.rafalmag.kontomierz.importers.BudgetsImporter

import javax.inject.Inject

class BudgetsApiMapping extends ApiMapping {
    @Inject
    BudgetsApiMapping(BudgetsImporter importer) {
        super("budgets", "/k4/budgets.json", "budgets", importer, "month")
    }
}
