package movieRental.core.service;

import movieRental.core.model.Movie;

import java.util.List;

public interface MovieServiceInterface extends BaseServiceInterface<Movie, Long> {
    List<Movie> filter(String value);

}
