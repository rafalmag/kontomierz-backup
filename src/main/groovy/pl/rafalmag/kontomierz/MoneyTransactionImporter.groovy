package pl.rafalmag.kontomierz

import pl.rafalmag.kontomierz.apimappings.ApiMapping


class MoneyTransactionImporter extends Importer {

    public List<Map> doImport(ApiMapping apiMapping) {
        int page =1
        def response = restClient.get(path: apiMapping.getUrlPath(), query: [
                api_key: apiKey,
                page: page,
                per_page: 100
        ]);
        assert response.status == 200
        response.getData()
    }
}
