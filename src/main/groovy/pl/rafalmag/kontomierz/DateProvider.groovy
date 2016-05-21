package pl.rafalmag.kontomierz

import javax.inject.Inject
import javax.inject.Provider
import java.text.SimpleDateFormat

class DateProvider implements Provider<List<String>> {

    public static final String DATE_PATTERN = "dd-MM-yyyy"

    @ImportFrom
    @Inject
    String importFrom

    @Override
    List<String> get() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN)
        Calendar importFromDate = dateFormat.parse(importFrom).toCalendar()
        importFromDate.set(Calendar.DAY_OF_MONTH, 1)
        def now = Calendar.getInstance()
        def result = []
        while (importFromDate.before(now)) {
            result.add(importFromDate.format(DATE_PATTERN))
            importFromDate.add(Calendar.MONTH, 1)
        }
        return result
    }
}
