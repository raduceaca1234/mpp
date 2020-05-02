package movieRental.core.service;

import movieRental.core.model.Rental;

import java.util.List;

public interface RentalServiceInterface extends BaseServiceInterface<Rental, Long>  {

    List<Rental> filter(Long clientID);

}
