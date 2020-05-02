import dto.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;
import view.View;

public class ClientApp {
    public static final String url = "http://localhost:8080/api/clients";

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("clientConfig");
        context.getBean(View.class).run();
    }

}
