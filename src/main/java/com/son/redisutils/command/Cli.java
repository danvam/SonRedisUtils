package com.son.redisutils.command;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class Cli {
	private static final Logger log = Logger.getLogger(Cli.class.getName());
	
	private static String OP_HELP = "h";
	private static String OP_HELP_LONG = "help";
	private static String OP_REMOVE_KEY = "r";
	private static String OP_REMOVE_KEY_LONG = "remove";
	private static String OP_MIGRATION = "m";
	private static String OP_MIGRATION_LONG = "migration";
	private static String OP_SOURCE_HOST = "sh";
	private static String OP_SOURCE_HOST_LONG = "sourceHost";
	private static String OP_TARGET_HOST = "th";
	private static String OP_TARGET_HOST_LONG = "targetHost";
	private static String OP_SOURCE_PORT = "sp";
	private static String OP_SOURCE_PORT_LONG = "sourcePort";
	private static String OP_TARGET_PORT = "tp";
	private static String OP_TARGET_PORT_LONG = "targetPort";
	private static String OP_PATTERN = "k";
	private static String OP_PATTERN_LONG = "key";
	private static String OP_TIMEOUT = "t";
	private static String OP_TIMEOUT_LONG = "timeout";

	private String[] args = null;
	private Options options = new Options();
	private Connection source;
	private Connection target;
	private String pattern;

	public Cli(String[] args) {

		this.args = args;

		options.addOption(OP_HELP, OP_HELP_LONG, false, "show help.");

		options.addOption(OP_MIGRATION, OP_MIGRATION_LONG, false, "migration function from redis to redis.");
		options.addOption(OP_REMOVE_KEY, OP_REMOVE_KEY_LONG, false, "remove value key with patthern.");
		
		options.addOption(OP_SOURCE_HOST, OP_SOURCE_HOST_LONG, true, "redis source host.");
		options.addOption(OP_SOURCE_PORT, OP_SOURCE_PORT_LONG, true, "redis source port.");

		options.addOption(OP_TARGET_HOST, OP_TARGET_HOST_LONG, true, "redis target host.");
		options.addOption(OP_TARGET_PORT, OP_TARGET_PORT_LONG, true, "redis target port.");

		options.addOption(OP_PATTERN, OP_PATTERN_LONG, true, "redis key pattern to search.");

		options.addOption(OP_TIMEOUT, OP_TIMEOUT_LONG, true, "redis timeout connection.");

	}

	public void parse() {
		CommandLineParser parser = new DefaultParser();

		CommandLine cmd = null;
		try {
			cmd = parser.parse(options, args);

			if (cmd.hasOption(OP_HELP)) {
				help();
			} else {
				checkOptions(cmd);
			}

			createState(cmd);

			execute(cmd);
		} catch (ParseException e) {
			log.log(Level.SEVERE, "Failed to parse comand line properties", e);
			help();
		}
	}

	private void createState(CommandLine cmd) {
		if(cmd.hasOption(OP_MIGRATION)) {
			createMigrationState(cmd);
		}else if(cmd.hasOption(OP_REMOVE_KEY)) {
			createRemoveKeyState(cmd);
		}

	}

	private void createRemoveKeyState(CommandLine cmd) {
		this.target = new Connection(cmd.getOptionValue(OP_TARGET_HOST),
				Integer.valueOf(cmd.getOptionValue(OP_TARGET_PORT)));
		log.log(Level.INFO, "Connect to redis target ");

		this.pattern = cmd.getOptionValue(OP_PATTERN);
		
	}

	private void createMigrationState(CommandLine cmd) {
		if (cmd.hasOption(OP_TIMEOUT)) {
			this.source = new Connection(cmd.getOptionValue(OP_SOURCE_HOST),
					Integer.valueOf(cmd.getOptionValue(OP_SOURCE_PORT)),
					Integer.valueOf(cmd.getOptionValue(OP_TIMEOUT)));
		} else {
			this.source = new Connection(cmd.getOptionValue(OP_SOURCE_HOST),
					Integer.valueOf(cmd.getOptionValue(OP_SOURCE_PORT)));
		}

		log.log(Level.INFO, "Connect to redis source  ");
		this.target = new Connection(cmd.getOptionValue(OP_TARGET_HOST),
				Integer.valueOf(cmd.getOptionValue(OP_TARGET_PORT)));
		log.log(Level.INFO, "Connect to redis target ");

		this.pattern = cmd.getOptionValue(OP_PATTERN);
	}

	private void execute(CommandLine cmd) {
		log.log(Level.INFO, "Start execution... ");
		if(cmd.hasOption(OP_MIGRATION)) {
			executeMigration();
		}else if(cmd.hasOption(OP_REMOVE_KEY)) {
			executeRemoveKey();
		}
	}

	private void executeRemoveKey() {
		this.target.connect();
		Set<String> keys = this.target.getKeys(this.pattern);
		log.log(Level.INFO, "Keys to remove: " + keys.size());
		for (String key : keys) {
			log.log(Level.INFO, "List of stored keys::  " + key);
			this.target.remove(key);
		}
		this.target.closeConnection();
		
	}

	private void executeMigration() {
		this.source.connect();
		this.target.connect();
		Set<String> keys = this.source.getKeys(this.pattern);
		log.log(Level.INFO, "Total keys: " + keys.size());
		for (String key : keys) {
			log.log(Level.INFO, "List of stored keys::  " + key);
			String value = this.source.getValue(key);
			this.target.setValue(key, value);
		}
		this.source.closeConnection();
		this.target.closeConnection();
	}

	private void checkOptions(CommandLine cmd) {

		String msg = "";
		
		if(cmd.hasOption(OP_MIGRATION) && cmd.hasOption(OP_REMOVE_KEY)){
			msg = "Debe especificar una de las dos opciones";
		}else if(cmd.hasOption(OP_MIGRATION)) {
			msg += (cmd.hasOption(OP_SOURCE_HOST) ? "" : "Required source host\n");
			msg += (cmd.hasOption(OP_SOURCE_PORT) ? "" : "Required source port\n");
			msg += (cmd.hasOption(OP_TARGET_HOST) ? "" : "Required target host\n");
			msg += (cmd.hasOption(OP_TARGET_PORT) ? "" : "Required target host\n");
			msg += (cmd.hasOption(OP_PATTERN) ? "" : "Required filter key pattern\n");
		}else if(cmd.hasOption(OP_REMOVE_KEY)) {
			msg += (cmd.hasOption(OP_TARGET_HOST) ? "" : "Required target host\n");
			msg += (cmd.hasOption(OP_TARGET_PORT) ? "" : "Required target host\n");
			msg += (cmd.hasOption(OP_PATTERN) ? "" : "Required filter key pattern\n");
		}

		if (!"".equals(msg)) {
			log.log(Level.SEVERE, msg);
			help();
		}
	}

	private void help() {
		// This prints out some help
		HelpFormatter formater = new HelpFormatter();

		formater.printHelp("Main", options);
		System.exit(0);
	}

}