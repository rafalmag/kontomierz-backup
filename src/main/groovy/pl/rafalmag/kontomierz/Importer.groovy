package pl.rafalmag.kontomierz

import groovyx.net.http.RESTClient

import javax.inject.Inject
import javax.inject.Named

class Importer {

    @Inject
    RESTClient restClient;

    @Named("apiKey")
    @Inject
    String apiKey;

    String path = "user_accounts.json"

    public List<Map> doImport(ApiMapping apiMapping) {
        def response = restClient.get(path: apiMapping.urlPath, query: [api_key: apiKey]);
        assert response.status == 200
        println response.getData().getClass()
        println response.getData().get(0).getClass()
        response.getData()
    }
}
