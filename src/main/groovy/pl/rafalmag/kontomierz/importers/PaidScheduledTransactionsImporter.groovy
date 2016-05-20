package pl.rafalmag.kontomierz.importers;

class PaidScheduledTransactionsImporter extends ScheduledTransactionsImporter {

    @Override
    Map<String, String> getQuery() {
        [api_key            : apiKey,
         start_on           : START_ON,
         schedule_group_name: "paid"]
    }
}