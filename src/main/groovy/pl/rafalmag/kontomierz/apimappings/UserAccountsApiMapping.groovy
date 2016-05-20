package pl.rafalmag.kontomierz.apimappings

import pl.rafalmag.kontomierz.importers.ListWithObjectsImporter

import javax.inject.Inject

class UserAccountsApiMapping extends ApiMapping {

    @Inject
    UserAccountsApiMapping(ListWithObjectsImporter importer) {
        super("user_accounts", "/k4/user_accounts.json", "user_account", importer, "id")
    }
}
