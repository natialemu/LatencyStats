import config.EmbeddedTomcatConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(EmbeddedTomcatConfiguration.class)
public class Application {

    protected Application() {
        // do nothing
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }
}