package pl.rafalmag.kontomierz.importers;

class PaidScheduledTransactionsImporter extends ScheduledTransactionsImporter {

    @Override
    Map<String, String> getQuery() {
        [api_key            : apiKey,
         start_on           : importFrom,
         schedule_group_name: "paid"]
    }
}