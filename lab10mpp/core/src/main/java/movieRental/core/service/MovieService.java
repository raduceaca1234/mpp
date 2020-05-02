package movieRental.core.service;

import movieRental.core.model.Movie;
import movieRental.core.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class MovieService implements MovieServiceInterface {

    @Autowired
    private MovieRepository movieRepository;

    public static final Logger log = LoggerFactory.getLogger(MovieService.class);

    @Override
    public List<Movie> getAll() {
        log.trace("filter movies - method entered");
        log.trace("filter movies - method ended");
        return movieRepository.findAll();
    }

    @Override
    public Movie save(Movie entity) {
        log.trace("save movie - method entered");
        Movie result = movieRepository.save(entity);
        log.debug("save - added", result);
        log.trace("save movie - method ended");
        return result;
    }

    @Override
    public Boolean deleteById(Long id) {
        log.trace("delete movie - method entered");
        AtomicBoolean deleted = new AtomicBoolean(false);
        movieRepository.findById(id).ifPresent(movie -> {
            movieRepository.delete(movie);
            deleted.set(true);
            log.debug("delete - deleted", movie);
        });
        log.trace("delete movie - method ended");
        return deleted.get();
    }

    @Override
    @Transactional
    public Movie update(Long id, Movie entity) {
        log.trace("update movie - method entered");
        movieRepository.findById(id).ifPresent(movie -> {
            movie.setTitle(entity.getTitle());
            movie.setCategory(entity.getCategory());
            movie.setRating(entity.getRating());
            movie.setRating(entity.getPrice());
            log.debug("update - updated ", movie);
        });
        log.trace("update movie - method ended");
        return entity;
    }

    @Override
    public List<Movie> filter(String category) {
        log.trace("filter movies - method entered");
        log.trace("filter movies - method ended");
        return movieRepository.findAll().stream()
                .filter(movie -> movie.getCategory().toLowerCase().contains(category.toLowerCase()))
                .collect(Collectors.toList());
    }
}
