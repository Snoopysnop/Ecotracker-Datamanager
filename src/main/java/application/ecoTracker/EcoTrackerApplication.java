package application.ecoTracker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@SpringBootApplication
public class EcoTrackerApplication implements CommandLineRunner{

	private final DataSource dataSource;

    public EcoTrackerApplication(DataSource dataSource) {
        this.dataSource = dataSource;
    }

	public static void main(String[] args) {
		SpringApplication.run(EcoTrackerApplication.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();

            statement.executeUpdate("ALTER TABLE ecoTracker.Campaign MODIFY COLUMN image LONGBLOB;");
			statement.executeUpdate("ALTER TABLE ecoTracker.Observation MODIFY COLUMN imageList LONGBLOB;");
            statement.executeUpdate("ALTER TABLE ecoTracker.User MODIFY COLUMN image LONGBLOB;");
        }
	}
}
