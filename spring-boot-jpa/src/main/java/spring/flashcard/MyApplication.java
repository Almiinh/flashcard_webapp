package spring.flashcard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.*;

@SpringBootApplication
public class MyApplication {

	public static <T> Logger getLogger(Class<T> clazz) {

		// We create a Logger using java.util.logging, with the name "MyApplication"
		Logger logger = Logger.getLogger(clazz.getSimpleName());

		LogManager.getLogManager().reset();

		// We create a custom formatter and customize the output format for the logger
		Formatter customFormatter = new Formatter() {
			@Override
			public String format(LogRecord record) {
				return switch (record.getLevel().getName()) {
					case "SEVERE" ->
							"\u001B[41m SEVERE \u001B[0m \u001B[31m" + record.getLoggerName() + ": " + record.getMessage() + "\u001B[0m\n";
					case "INFO" ->
							"\u001B[42m \u001B[30mINFO \u001B[0m \u001B[32m" + record.getMessage() + "\u001B[0m\n";
					case "WARNING" ->
							"\u001B[43m \u001B[30mWARNING \u001B[0m \u001B[33m" + record.getMessage() + "\u001B[0m\n";
					case "CONFIG" ->
							"\u001B[44m \u001B[30mCONFIG \u001B[0m \u001B[34m" + record.getMessage() + "\u001B[0m\n";
					case "FINE" ->
							"\u001B[45m FINE \u001B[0m \u001B[35m" + record.getMessage() + "\u001B[0m\n";
					case "FINER" ->
							"\u001B[46m \u001B[30mFINER \u001B[0m \u001B[36m" + record.getMessage() + "\u001B[0m\n";
					case "FINEST" ->
							"\u001B[47m \u001B[30mFINEST \u001B[0m \u001B[37m" + record.getMessage() + "\u001B[0m\n";
					default ->
							throw new IllegalStateException("Unexpected value: " + record.getLevel().getName());
				};
			}
		};

		// Set Handler
		ConsoleHandler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(Level.ALL);
		consoleHandler.setFormatter(customFormatter);
		logger.setLevel(Level.ALL);
		logger.addHandler(consoleHandler);
		return logger;
	}


	public static void main(String[] args) throws URISyntaxException {
		Logger logger = getLogger(MyApplication.class);

		// We test some logging messages for every level
		logger.severe("This is a severe message");
		logger.info("This is an info message");
		logger.warning("This is a warning message");
		logger.config("This is a config message");
		logger.fine("This is a fine level message");
		logger.finer("This is a finer level message");
		logger.finest("This is the finest level message");

		// We start the app
		SpringApplication.run(MyApplication.class, args);

		logger.info("MyApplication successfully started at " + new URI("http://localhost:8080/"));
	}
}
