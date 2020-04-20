package com.example.vaultconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.vault.core.VaultKeyValueOperationsSupport;
import org.springframework.vault.core.VaultTemplate;
import org.springframework.vault.support.VaultResponse;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootApplication
@EnableConfigurationProperties(MyConfiguration.class)
class Application implements CommandLineRunner {

	private final MyConfiguration configuration;

	@Autowired
	private VaultTemplate vaultTemplate;

	@Autowired
	DataSource dataSource;



	public Application(MyConfiguration configuration) {
		this.configuration = configuration;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) {

		Logger logger = LoggerFactory.getLogger(Application.class);

		logger.info("----------------------------------------");
		logger.info("Configuration properties");
		logger.info("   example.username is {}", configuration.getUsername());
		logger.info("   example.password is {}", configuration.getPassword());
		logger.info("----------------------------------------");



		// You usually would not print a secret to stdout
		/*VaultResponse response = vaultTemplate
				.opsForKeyValue("my-secret", VaultKeyValueOperationsSupport.KeyValueBackend.KV_1).get("kar-secret");
		System.out.println("Value of Vault config");
		System.out.println("-------------------------------");
		System.out.println(response.getData().entrySet());
		System.out.println("-------------------------------");
		System.out.println();*/
	}

	@PostConstruct
	private void postConstruct() throws Exception {
		try (Connection connection = dataSource.getConnection();
			 Statement statement = connection.createStatement()) {
			ResultSet resultSet = statement.executeQuery("SELECT CURRENT_USER();");
			resultSet.next();
			System.out.println("Current USER created dynamically ======================> " + resultSet.getString(1));
			resultSet.close();
		}
	}
}
