package util;

import lombok.experimental.UtilityClass;
import me.nikastroganova.astoncourse.springtask.entity.Actor;
import me.nikastroganova.astoncourse.springtask.entity.Hall;
import me.nikastroganova.astoncourse.springtask.entity.Performance;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

@UtilityClass
public class HibernateTestUtil {

    private static final PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:14")
            .withDatabaseName("performance_database")
            .withUsername("postgres")
            .withPassword("postgres")
            .withInitScript("test.sql");


    public static SessionFactory buildSessionFactory() {
        Configuration configuration = buildConfiguration()
                .setProperty("hibernate.connection.url", container.getJdbcUrl())
                .setProperty("hibernate.connection.username", container.getUsername())
                .setProperty("hibernate.connection.password", container.getPassword())
                .configure();

        return configuration.buildSessionFactory();
    }
    public static Configuration buildConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Performance.class)
                .addAnnotatedClass(Hall.class);
        return configuration;
    }
}
