package view;

import dto.*;
import movieRental.core.model.Client;
import movieRental.core.model.Rental;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class View {

    @Autowired
    private RestTemplate restTemplate;

    private static final String clientsURL = "http://localhost:8080/api/clients";
    private static final String moviesURL = "http://localhost:8080/api/movies";
    private static final String rentalsURL = "http://localhost:8080/api/rentals";

    private Scanner scanner = new Scanner(System.in);

    public void run(){
        while(true){
            printConsole();
            int option = scanner.nextInt();
            System.out.println(option);
            switch (option){
                case 0:
                    return;
                case 1:
                    handleAdd();
                    break;
                case 2:
                    handleShow();
                    break;
                case 3:
                    handleFilter();
                    break;
                case 4:
                    handleDelete();
                    break;
                case 5:
                    handleUpdate();
                    break;
//                case 6:
//                    handleSort();
//                    break;
            }
        }
    }

//    private void handleSort() {
//        System.out.println("Choose your option: " +
//                "\n\t1.Clients" +
//                "\n\t2.Movies");
//        int option = scanner.nextInt();
//        scanner.nextLine();
//        switch (option){
//            case 1:
//                handleSortClient();
//                break;
//            case 2:
//                handleSortMovie();
//                break;
//        }
//    }
//
//    private void handleSortMovie() {
//        System.out.println("Available fields:id, title, category, rating \n>");
//        System.out.println("How many fields do you want to sort by?\n>");
//        int count = scanner.nextInt();
//        count = Math.min(4, count);
//        List<String> fields = new LinkedList<>();
//        for(int i = 0; i < count; i++){
//            System.out.println(">");
//            fields.add(scanner.nextLine());
//        }
//        try {
//            movieService.getMoviesSorted(fields).forEach(System.out::println);
//        } catch (ExecutionException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void handleSortClient() {
//        System.out.println("Available fields:id, name \n>");
//        System.out.println("How many fields do you want to sort by?\n>");
//        int count = scanner.nextInt();
//        List<String> fields = new LinkedList<>();
//        for(int i = 0; i < count; i++){
//            System.out.println(">");
//            fields.add(scanner.nextLine());
//        }
//        try {
//            clientService.getClientsSorted(fields).forEach(System.out::println);
//        } catch (ExecutionException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }

    private void handleUpdate() {
        printEntityOption();
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option){
            case 1:
                handleUpdateClient();
                break;
            case 2:
                handleUpdateMovie();
                break;
            case 3:
                handleUpdateRental();
                break;
        }
    }

    private void handleUpdateClient() {
        printAllClient();
        System.out.println("Give the ID of the client you want to update \n >");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter a new firstname for this client \n >");
        String newClientName = scanner.nextLine();
        System.out.println("Enter a new secondname for this client \n >");
        String newSecondName = scanner.nextLine();
        System.out.println("Enter a new job for this client \n >");
        String newJob = scanner.nextLine();
        System.out.println("Enter a new age for this movie \n >");
        int newAge = scanner.nextInt();
        restTemplate.put(clientsURL + "/{id}",
                new ClientDto(newClientName,newSecondName,newJob,newAge), id);

        System.out.println("Done!\n");
    }

    private void handleUpdateMovie() {
        printAllMovies();
        System.out.println("Give the ID of the movie you want to update \n >");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter a new name for this movie \n >");
        String newTitle = scanner.nextLine();
        System.out.println("Enter a new category for this movie \n >");
        String newCategory = scanner.nextLine();
        System.out.println("Enter a new rating for this movie \n >");
        int newRating = scanner.nextInt();
        System.out.println("Enter a new price for this movie \n >");
        int newPrice = scanner.nextInt();
        scanner.nextLine();

        restTemplate.put(moviesURL + "/{id}",
                new MovieDto(newTitle, newCategory, newRating, newPrice), id);

        System.out.println("Done!\n");
    }

    private void handleUpdateRental() {
        printAllRentals();
        System.out.println("Give the ID of the rental you want to update \n >");
        Long id = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter a new Movie id \n >");
        Long movieId = scanner.nextLong();
        scanner.nextLine();
        System.out.println("Enter a new Client id \n >");
        Long clientId = scanner.nextLong();
        scanner.nextLine();

        restTemplate.put(rentalsURL + "/{id}",
                new RentalDto(clientId, movieId), id);

        System.out.println("Done!\n");
    }

    private void handleDelete() {
        printEntityOption();
        int option = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Give the ID of the entity you want to delete \n >");
        Long id = scanner.nextLong();
        scanner.nextLine();
        switch (option){
            case 1:
                deleteClient(id);
                break;
            case 2:
                deleteMovie(id);
                break;
            case 3:
                deleteRental(id);
                break;
        }
    }

    private void deleteClient(Long id) {
        restTemplate.delete(clientsURL + "/{id}", id);
        System.out.println("Done!\n");
    }

    private void deleteMovie(Long id) {
        restTemplate.delete(moviesURL + "/{id}", id);
        System.out.println("Done!\n");
    }

    private void deleteRental(Long id) {
        restTemplate.delete(rentalsURL + "/{id}", id);
        System.out.println("Done!\n");
    }


    private void handleFilter() {
        printEntityOption();
        int option = scanner.nextInt();
        scanner.nextLine();
        switch (option){
            case 1:
                System.out.println("Name:");
                String name = scanner.nextLine();
                filterClients(name);
                break;
            case 2:
                System.out.println("Category:");
                String category = scanner.nextLine();
                filterMovies(category);
                break;
            case 3:
                System.out.println("Client ID:");
                Long id = scanner.nextLong();
                scanner.nextLine();
                filterRentalsByClient(id);
                break;
        }
    }

    private void handleShow() {
        printEntityOption();
        int option = scanner.nextInt();
        switch (option){
            case 1:
                printAllClient();
                break;
            case 2:
                printAllMovies();
                break;
            case 3:
                printAllRentals();
        }
    }

    private void handleAdd() {
        printEntityOption();
        int option = scanner.nextInt();
        scanner.nextLine();
        System.out.println(option);
        switch (option){
            case 1:
                addClient();
                break;
            case 2:
                addMovie();
                break;
            case 3:
                addRental();
        }
    }


    private void printConsole(){
        System.out.println("Choose your option: " +
                "\n\t1.Add" +
                "\n\t2.Show" +
                "\n\t3.Filter" +
                "\n\t4.Delete" +
                "\n\t5.Update" +
                "\n\t6.Sort" +
                "\n\t0.Exit");
    }

    private void printEntityOption(){
        System.out.println("Choose your option: " +
                "\n\t1.Clients" +
                "\n\t2.Movies" +
                "\n\t3.Rentals");
    }

    private void printAllClient(){
        ClientsDto clients = restTemplate.getForObject(clientsURL, ClientsDto.class);
        System.out.println("Clients:\n\t"+ clients.toString());
    }

    private void printAllMovies(){
        MoviesDto movies = restTemplate.getForObject(moviesURL, MoviesDto.class);
        System.out.println("Movies:\n\t" + movies.toString());
    }

    private void printAllRentals(){
        RentalsDto rentals = restTemplate.getForObject(rentalsURL, RentalsDto.class);
        System.out.println("Rentals:\n\t" + rentals.toString());
    }

    private void filterClients(String name){
        ClientsDto clients = restTemplate.getForObject(clientsURL + "/{name}", ClientsDto.class, name);
        System.out.println("Clients with name like %" + name + "%\n\t"
                + clients.toString());
    }

    private void filterMovies(String category){
        MoviesDto movies = restTemplate.getForObject(moviesURL + "/{category}", MoviesDto.class, category);
        System.out.println("Movies with category like %" + category + "%\n\t"
                + movies.toString());
    }

    private void filterRentalsByClient(Long id){
        RentalsDto rentals = restTemplate.getForObject(rentalsURL + "/{id}", RentalsDto.class, id);
        System.out.println("Rentals of client with id: " + id + "\n\t"
                + rentals.toString());
    }

    private void addClient(){
        System.out.println("FirstName:");
        String name = scanner.nextLine();
        System.out.println("SecondName:");
        String secondName = scanner.nextLine();
        System.out.println("Job:");
        String job = scanner.nextLine();
        System.out.println("Age:");
        int age = scanner.nextInt();
        ClientDto savedClient = restTemplate.postForObject(
                clientsURL,
                new ClientDto(name,secondName,job,age),
                ClientDto.class);
        System.out.println("saved: " + savedClient.toString());
    }

    private void addMovie(){
        System.out.println("Title:");
        String title = scanner.nextLine();
        System.out.println("Category:");
        String category = scanner.nextLine();
        System.out.println("Rating:");
        int rating = scanner.nextInt();
        System.out.println("Price:");
        int price = scanner.nextInt();
        MovieDto savedMovie = restTemplate.postForObject(
                moviesURL,
                new MovieDto(title, category, rating, price),
                MovieDto.class);
        System.out.println("saved: " + savedMovie.toString());
    }

    private void addRental(){
        System.out.println("Client ID:");
        Long clientID = scanner.nextLong();
        System.out.println("Movie ID:");
        Long movieID = scanner.nextLong();
        RentalDto savedRental = restTemplate.postForObject(
                rentalsURL,
                new RentalDto(clientID, movieID),
                RentalDto.class);
        System.out.println("saved: " + savedRental.toString());
    }
}