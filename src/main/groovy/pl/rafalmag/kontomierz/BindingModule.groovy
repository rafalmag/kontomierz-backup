package pl.rafalmag.kontomierz

import com.google.common.collect.ImmutableList
import com.google.inject.AbstractModule
import com.google.inject.TypeLiteral
import com.google.inject.name.Names
import com.mongodb.MongoClient
import com.mongodb.client.MongoDatabase
import groovyx.net.http.RESTClient

class BindingModule extends AbstractModule {
    static final String BASE_URL = "https://kontomierz.pl/"

    final Arguments arguments

    BindingModule(Arguments arguments) {
        this.arguments = arguments
    }

    @Override
    protected void configure() {
        bind(String.class).annotatedWith(Names.named("apiKey")).toInstance(arguments.getApiKey())
        bind(RESTClient.class).toInstance(new RESTClient(BASE_URL));
        bind(new TypeLiteral<List<ApiMapping>>() {}).toInstance(ImmutableList.of(
                new ApiMapping("user_accounts", "/k4/user_accounts.json")))
        def client = new MongoClient(arguments.getHost(), arguments.getPort());
        bind(MongoClient.class).toInstance(client);
        bind(MongoDatabase.class).toInstance(client.getDatabase(arguments.getDataBaseName()));
    }
}
