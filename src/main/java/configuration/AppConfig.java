package configuration;

import com.coffeeshop.rest.RestClient;
import com.coffeeshop.utilitary.generators.IdGenerator;
import com.coffeeshop.utilitary.generators.NumberGenerator;
import com.coffeeshop.utilitary.managers.CoffeeManager;
import com.coffeeshop.utilitary.managers.ConsoleManager;
import com.coffeeshop.utilitary.managers.PricesManager;
import com.coffeeshop.utilitary.managers.StockManager;
import com.coffeeshop.utilitary.printers.Printer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.client.RestTemplate;

import java.util.Scanner;

@Configuration
@EnableScheduling
@ComponentScan({"com.coffeeshop"})
public class AppConfig {

    @Bean(name = "scanner")
    public Scanner getScanner(){
        return new Scanner(System.in);
    }

    @Bean(name = "printer")
    public Printer getPrinter(){
        return new Printer();
    }

    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler();
    }

    @Bean(name = "client")
    public RestClient getRestClient(){
        new RestTemplate();
        return new RestClient();
    }

    @Bean(name = "idGenerator")
    public IdGenerator getIdGenerator(){
        return new IdGenerator();
    }

    @Bean(name = "consoleManager")
    public ConsoleManager getConsoleManager(){
        return new ConsoleManager();
    }

    @Bean(name = "numberGenerator")
    public NumberGenerator getNumberGenerator(){
        return new NumberGenerator();
    }

    @Bean(name = "pricesManager")
    public PricesManager getPricesManager(){
        return new PricesManager();
    }

    @Bean(name = "stockManager")
    public StockManager getStockManager(){
        return new StockManager();
    }

    @Bean(name = "coffeeManager")
    public CoffeeManager getCoffeeManager(){
        return new CoffeeManager();
    }
}
