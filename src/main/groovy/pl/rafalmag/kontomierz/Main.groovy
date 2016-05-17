package pl.rafalmag.kontomierz

import com.google.inject.Guice
import com.google.inject.Injector
import com.lexicalscope.jewel.cli.ArgumentValidationException
import com.lexicalscope.jewel.cli.CliFactory

class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new BindingModule(getArguments(args)))
        injector.getInstance(BackupService.class).backup()
    }

    static Arguments getArguments(String[] args) {
        try {
            CliFactory.parseArguments(Arguments, args);
        }
        catch (ArgumentValidationException e) {
            // TODO usage and help
            println(e.getMessage())
            System.exit(-1);
            throw new IllegalStateException("after system exit", e)
        }
    }
}
