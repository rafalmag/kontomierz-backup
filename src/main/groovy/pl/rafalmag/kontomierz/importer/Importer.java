package pl.rafalmag.kontomierz.importer;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Importer {
    @Inject
    UserAccountsImporter userAccountsImporter;

    void doImport(){
        //TODO setup mongodb
        userAccountsImporter.doImport()
    }
}
