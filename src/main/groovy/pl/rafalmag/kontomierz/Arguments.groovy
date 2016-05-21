package pl.rafalmag.kontomierz

import com.lexicalscope.jewel.cli.Option

interface Arguments {
    @Option(longName = "host", defaultValue = "localhost", description = "Mongo database host")
    String getHost()

    @Option(longName = "port", shortName = "p", defaultValue = "27017", description = "Mongo database port")
    int getPort()

    // from Kontomierz api docs example
    @Option(longName = "apiKey", shortName = "a",
            defaultValue = "e7cOI9zZTbprBddSHHnlniLsAvzBpfhqTIjeUid2be0fjb2REaWnudZqGSgxz1Lz",
            description = "Kontomierz api key")
    String getApiKey()

    @Option(longName = "database", shortName = "d", description = "database name")
    String getDataBaseName()

    @Option(longName = "drop", description = "drop database")
    boolean getDrop()

    @Option(helpRequest = true, description = "help")
    boolean getHelp()

    @Option(longName = "importFrom", defaultValue = "01-01-2013",
            pattern = "^(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[012])-(19|20)\\d\\d\$",
            description = "Import start date. Format DD-MM-YYYY.")
    String getImportFrom()
}