package movieRental.core.service;

import movieRental.core.model.Client;

import java.util.List;

public interface ClientServiceInterface extends BaseServiceInterface<Client, Long> {

    List<Client> filter(String value);

}
