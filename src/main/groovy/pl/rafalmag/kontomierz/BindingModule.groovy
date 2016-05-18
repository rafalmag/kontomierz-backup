package pl.rafalmag.kontomierz

import com.google.inject.AbstractModule
import com.google.inject.multibindings.Multibinder
import com.google.inject.name.Names
import com.mongodb.MongoClient
import com.mongodb.client.MongoDatabase
import groovyx.net.http.RESTClient
import pl.rafalmag.kontomierz.apimappings.ApiMapping
import pl.rafalmag.kontomierz.apimappings.MoneyTransactionApiMapping
import pl.rafalmag.kontomierz.apimappings.UserAccountsApiMapping

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

        Multibinder<ApiMapping> apiMappings = Multibinder.newSetBinder(binder(), ApiMapping.class)
        apiMappings.addBinding().to(UserAccountsApiMapping.class)
        apiMappings.addBinding().to(MoneyTransactionApiMapping.class)
        // TODO categories deposit
        // TODO categories withdrawal
        // TODO tags
        // TODO budgets
        // TODO scheduled_transactions
        // TODO schedules
        // TODO wealth_points
        // TODO currencies

    }
}
