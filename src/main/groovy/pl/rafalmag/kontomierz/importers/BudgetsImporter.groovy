package pl.rafalmag.kontomierz.importers

import groovy.util.logging.Slf4j
import groovyx.net.http.RESTClient
import pl.rafalmag.kontomierz.ApiKey
import pl.rafalmag.kontomierz.Dates
import pl.rafalmag.kontomierz.apimappings.ApiMapping

import javax.inject.Inject

@Slf4j
class BudgetsImporter implements Importer {

    @Inject
    RESTClient restClient

    @ApiKey
    @Inject
    String apiKey

    @Dates
    @Inject
    List<String> dates

    @Override
    public List<Map> doImport(ApiMapping apiMapping) {
        dates.collectEntries {
            // date to json result map
            [(it): importForDate(it, apiMapping)]
        }.findAll {
            // filter out empty results
            !it.value.get(apiMapping.getObjectName()).isEmpty()
        }.collect {
            addDateToReceivedJson(it.value, it.key)
        }
    }

    private Map importForDate(String date, ApiMapping apiMapping) {
        def response = restClient.get(path: apiMapping.getUrlPath(), query: [
                api_key : apiKey,
                month_on: date
        ])
        assert response.status == 200
        log.debug("Received response for $apiMapping.collectionName date $date: {}", response.getData())
        response.getData()
    }

    protected Map addDateToReceivedJson(Map json, String date) {
        json.put("month", date)
        json
    }
}
