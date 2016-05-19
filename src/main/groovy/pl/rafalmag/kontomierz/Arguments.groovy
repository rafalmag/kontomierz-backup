package pl.rafalmag.kontomierz

import com.lexicalscope.jewel.cli.Option

interface Arguments {
    @Option(longName = "host", defaultValue = "localhost")
    String getHost()

    @Option(longName = "port", shortName = "p", defaultValue = "27017")
    int getPort()

    // from Kontomierz api docs example
    @Option(longName = "apiKey", shortName = "a", defaultValue = "e7cOI9zZTbprBddSHHnlniLsAvzBpfhqTIjeUid2be0fjb2REaWnudZqGSgxz1Lz")
    String getApiKey()

    @Option(longName = "database", shortName = "d")
    String getDataBaseName()

    @Option(longName = "drop")
    boolean getDrop()

    @Option(helpRequest = true)
    boolean getHelp()
}