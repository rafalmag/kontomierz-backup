package pl.rafalmag.kontomierz.apimappings

import javax.inject.Inject
import javax.inject.Named

class ScheduledTransactionsApiMapping extends ApiMapping {

    @Inject
    ScheduledTransactionsApiMapping(@Named("BothScheduledTransactions") MergingImporter importer) {
        super("scheduled_transactions", "/k4/scheduled_transactions.json", "scheduled_transactions", importer, "schedule_id")
    }
}
