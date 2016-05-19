package pl.rafalmag.kontomierz.apimappings

import pl.rafalmag.kontomierz.importers.WealthPointsImporter

import javax.inject.Inject

class WealthPointsApiMapping extends ApiMapping {
    @Inject
    WealthPointsApiMapping(WealthPointsImporter importer) {
        super("wealth_points", "/k4/wealth_points.json", "wealth_point", importer, "id")
    }
}
