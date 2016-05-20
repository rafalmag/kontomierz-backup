package pl.rafalmag.kontomierz

import com.google.inject.AbstractModule
import com.google.inject.PrivateModule
import com.google.inject.multibindings.Multibinder
import com.google.inject.name.Names
import com.mongodb.MongoClient
import com.mongodb.client.MongoDatabase
import groovyx.net.http.RESTClient
import pl.rafalmag.kontomierz.apimappings.*
import pl.rafalmag.kontomierz.importers.Importer
import pl.rafalmag.kontomierz.importers.PaidScheduledTransactionsImporter
import pl.rafalmag.kontomierz.importers.UnpaidScheduledTransactionsImporter

class BindingModule extends AbstractModule {
    static final String BASE_URL = "https://kontomierz.pl/"

    final Arguments arguments

    BindingModule(Arguments arguments) {
        this.arguments = arguments
    }

    @Override
    protected void configure() {
        bind(Arguments.class).toInstance(arguments)
        bind(String.class).annotatedWith(Names.named("apiKey")).toInstance(arguments.getApiKey())
        bind(RESTClient.class).toInstance(new RESTClient(BASE_URL))
        def client = new MongoClient(arguments.getHost(), arguments.getPort())
        bind(MongoClient.class).toInstance(client)
        bind(MongoDatabase.class).toInstance(client.getDatabase(arguments.getDataBaseName()))

        install(new PrivateModule() {
            @Override
            protected void configure() {
                Multibinder<Importer> scheduledTransactionsImporters = Multibinder.newSetBinder(binder(), Importer.class);
                scheduledTransactionsImporters.addBinding().to(PaidScheduledTransactionsImporter.class);
                scheduledTransactionsImporters.addBinding().to(UnpaidScheduledTransactionsImporter.class);
                bind(MergingImporter.class).annotatedWith(Names.named("BothScheduledTransactions")).to(MergingImporter.class)
                bind(ScheduledTransactionsApiMapping.class)
                expose(ScheduledTransactionsApiMapping.class)
            }
        });

        Multibinder<ApiMapping> apiMappings = Multibinder.newSetBinder(binder(), ApiMapping.class)
        apiMappings.addBinding().to(UserAccountsApiMapping.class)
        apiMappings.addBinding().to(MoneyTransactionApiMapping.class)
        apiMappings.addBinding().to(DepositCategoriesApiMapping.class)
        apiMappings.addBinding().to(WithdrawalCategoriesApiMapping.class)
        apiMappings.addBinding().to(TagsApiMapping.class)
        // TODO budgets
        // TODO schedules
        apiMappings.addBinding().to(ScheduledTransactionsApiMapping.class)
        apiMappings.addBinding().to(WealthPointsApiMapping.class)
        apiMappings.addBinding().to(CurrenciesApiMapping.class)

    }
}
