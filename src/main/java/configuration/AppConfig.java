package configuration;

import com.coffeeshop.utilitary.Ingredients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Scanner;

@Configuration
@ComponentScan({"com.coffeeshop"})
public class AppConfig {

    @Bean(name = "scanner")
    public Scanner getScanner(){
        return new Scanner(System.in);
    }
}
