package task.ibris.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.ResourceBundle;

@Configuration
public class BundleConfiguration {

    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("message");
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("message");
        return source;
    }
}
