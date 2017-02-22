import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan("com.messenger")
@EnableJpaRepositories
@EnableTransactionManagement
public class StartApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(StartApplication.class, args);
    }
}
