package pl.rafalmag.kontomierz

import groovyx.net.http.RESTClient
import pl.rafalmag.kontomierz.apimappings.ApiMapping

import javax.inject.Inject
import javax.inject.Named

class Importer {

    @Inject
    RESTClient restClient;

    @Named("apiKey")
    @Inject
    String apiKey;

    public List<Map> doImport(ApiMapping apiMapping) {
        def response = restClient.get(path: apiMapping.urlPath, query: [api_key: apiKey]);
        assert response.status == 200
        response.getData()
    }
}
