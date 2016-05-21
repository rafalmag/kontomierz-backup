package pl.rafalmag.kontomierz

import com.google.inject.AbstractModule
import com.google.inject.PrivateModule
import com.google.inject.TypeLiteral
import com.google.inject.multibindings.Multibinder
import com.google.inject.name.Names
import com.mongodb.MongoClient
import com.mongodb.client.MongoDatabase
import groovyx.net.http.RESTClient
import pl.rafalmag.kontomierz.apimappings.*
import pl.rafalmag.kontomierz.importers.*

class BindingModule extends AbstractModule {

    static final String BASE_URL = "https://kontomierz.pl/"

    final Arguments arguments

    BindingModule(Arguments arguments) {
        this.arguments = arguments
    }

    @Override
    protected void configure() {
        bind(Arguments).toInstance(arguments)
        bind(String).annotatedWith(ApiKey).toInstance(arguments.getApiKey())
        bind(String).annotatedWith(ImportFrom).toInstance(arguments.getImportFrom())
        bind(RESTClient).toInstance(new RESTClient(BASE_URL))
        def client = new MongoClient(arguments.getHost(), arguments.getPort())
        bind(MongoClient).toInstance(client)
        bind(MongoDatabase).toInstance(client.getDatabase(arguments.getDataBaseName()))

        bind(new TypeLiteral<List<String>>() {}).annotatedWith(Dates).toProvider(DateProvider)

        install(new PrivateModule() {
            @Override
            protected void configure() {
                Multibinder<ListWithObjectsImporter> scheduledTransactionsImporters = Multibinder.newSetBinder(
                        binder(), ListWithObjectsImporter)
                scheduledTransactionsImporters.addBinding().to(PaidScheduledTransactionsImporter)
                scheduledTransactionsImporters.addBinding().to(UnpaidScheduledTransactionsImporter)
                bind(Importer).annotatedWith(Names.named("BothScheduledTransactions")).to(MergingImporter)
                bind(ScheduledTransactionsApiMapping)
                expose(ScheduledTransactionsApiMapping)
            }
        })

        Multibinder<ApiMapping> apiMappings = Multibinder.newSetBinder(binder(), ApiMapping)
        apiMappings.addBinding().to(UserAccountsApiMapping)
        apiMappings.addBinding().to(MoneyTransactionApiMapping)
        apiMappings.addBinding().to(DepositCategoriesApiMapping)
        apiMappings.addBinding().to(WithdrawalCategoriesApiMapping)
        apiMappings.addBinding().to(TagsApiMapping)
        apiMappings.addBinding().to(BudgetsApiMapping)
        apiMappings.addBinding().to(ScheduledTransactionsApiMapping)
        apiMappings.addBinding().to(SchedulesApiMapping)
        apiMappings.addBinding().to(WealthPointsApiMapping)
        apiMappings.addBinding().to(CurrenciesApiMapping)

    }
}
