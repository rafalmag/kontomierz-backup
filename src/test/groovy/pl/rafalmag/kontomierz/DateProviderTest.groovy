package pl.rafalmag.kontomierz

import spock.lang.Specification

import java.text.SimpleDateFormat

class DateProviderTest extends Specification {

    def "for current date should return first day of the month"() {
        given:
        def sut = new DateProvider()
        sut.importFrom = new SimpleDateFormat(DateProvider.DATE_PATTERN).format(new Date())
        when:
        def result = sut.get()
        then:
        result.size() == 1
        result[0].startsWith("01-")
    }

    def "for past date should return at least few months"() {
        given:
        def sut = new DateProvider()
        sut.importFrom = "20-01-2015"
        when:
        def result = sut.get()
        then:
        result.containsAll("01-01-2015",
                "01-02-2015",
                "01-03-2015",
                "01-04-2015",
                "01-05-2015",
                "01-06-2015",
                "01-07-2015",
                "01-08-2015",
                "01-09-2015",
                "01-10-2015",
                "01-11-2015",
                "01-12-2015",
                "01-01-2016",
                "01-02-2016",
                "01-03-2016",
                "01-04-2016",
                "01-05-2016"
        )
    }
}
