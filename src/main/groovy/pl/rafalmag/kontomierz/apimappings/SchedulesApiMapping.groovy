package pl.rafalmag.kontomierz.apimappings

import pl.rafalmag.kontomierz.importers.SchedulesImporter

import javax.inject.Inject

class SchedulesApiMapping extends ApiMapping {

    public static final String ID_SUBSTITUTION_MARKER = "{ID}"

    @Inject
    SchedulesApiMapping(SchedulesImporter importer) {
        super("schedules", "/k4/schedules/" + ID_SUBSTITUTION_MARKER + ".json", "schedule", importer, "id")
    }
}