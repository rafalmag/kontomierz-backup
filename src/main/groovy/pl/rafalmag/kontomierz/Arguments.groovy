package pl.rafalmag.kontomierz

import com.lexicalscope.jewel.cli.Option

interface Arguments {
    @Option(longName = "host", shortName = "p", defaultValue = "localhost")
    String getHost()

    @Option(longName = "port", shortName = "p", defaultValue = "27017")
    int getPort()

    // api docs example
    @Option(longName = "apiKey", shortName = "a", defaultValue = "e7cOI9zZTbprBddSHHnlniLsAvzBpfhqTIjeUid2be0fjb2REaWnudZqGSgxz1Lz")
    String getApiKey()

    @Option(longName = "database", shortName = "d")
    String getDataBaseName()

    @Option(helpRequest = true)
    boolean getHelp()
}