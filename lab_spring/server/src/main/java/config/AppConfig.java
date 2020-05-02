package config;

import domain.Client;
import domain.validators.ClientValidator;
import domain.validators.MovieValidator;
import domain.validators.RentalValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import repository.database.ClientDBRepository;

import repository.database.MovieDBRepository;
import repository.database.RentalDBRepository;
import repository.database.orm.ClientMapper;
import repository.database.orm.MovieMapper;
import repository.database.orm.RentalMapper;
import service.*;

import java.util.concurrent.Executors;

@Configuration
public class AppConfig {
    @Bean(name = "ClientService")
    RmiServiceExporter rmiClientServiceExporter()
    {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("ClientService");
        rmiServiceExporter.setRegistryPort(1099);
        rmiServiceExporter.setServiceInterface(ClientService.class);
        rmiServiceExporter.setService(clientService());

        return rmiServiceExporter;
    }
    @Bean(name = "MovieService")
    RmiServiceExporter rmiMovieServiceExporter()
    {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("MovieService");
        rmiServiceExporter.setRegistryPort(1099);
        rmiServiceExporter.setServiceInterface(MovieService.class);
        rmiServiceExporter.setService(movieService());

        return rmiServiceExporter;
    }
    @Bean(name = "RentalService")
    RmiServiceExporter rmiRentalServiceExporter()
    {
        RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
        rmiServiceExporter.setServiceName("RentalService");
        rmiServiceExporter.setRegistryPort(1099);
        rmiServiceExporter.setServiceInterface(RentalService.class);
        rmiServiceExporter.setService(rentalService());

        return rmiServiceExporter;
    }
    @Bean
    ClientService clientService() {
        return new ClientServiceImpl();
    }

    @Bean
    ClientValidator clientValidator(){
        return new ClientValidator();
    }
    @Bean
    ClientMapper clientMapper()
    {
        return new ClientMapper();
    }

    @Bean
    ClientDBRepository clientRepository(){
        return new ClientDBRepository();
    }
    @Bean
    MovieValidator movieValidator() {
        return new MovieValidator();
    }
    @Bean
    RentalValidator rentalValidator() {
        return new RentalValidator();
    }
    @Bean
    MovieService movieService() {
        return new MovieServiceImpl();
    }
    @Bean
    MovieDBRepository movieRepository() {
        return new MovieDBRepository();
    }
    @Bean
    MovieMapper movieMapper() {
        return new MovieMapper();
    }
    @Bean
    RentalMapper rentalMapper() {
        return new RentalMapper();
    }

    @Bean
    RentalDBRepository rentalRepository() {
        return new RentalDBRepository();
    }

    @Bean
    RentalService rentalService() {
        return new RentalServiceImpl();
    }








}