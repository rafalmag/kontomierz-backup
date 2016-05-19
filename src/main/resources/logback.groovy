import ch.qos.logback.classic.encoder.PatternLayoutEncoder
import ch.qos.logback.core.ConsoleAppender

import static ch.qos.logback.classic.Level.DEBUG
import static ch.qos.logback.classic.Level.INFO

appender("STDOUT", ConsoleAppender) {
    encoder(PatternLayoutEncoder) {
        pattern = "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{20} - %msg%n"
    }
}

root(DEBUG, ["STDOUT"])
logger("pl.rafalmag", DEBUG, ["STDOUT"], additivity = false)
logger("org.mongodb.driver", INFO, ["STDOUT"], additivity = false)

