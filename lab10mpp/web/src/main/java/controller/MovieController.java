package controller;

import converter.BaseConverter;
import dto.MovieDto;
import dto.MoviesDto;
import movieRental.core.model.Movie;
import movieRental.core.service.MovieServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MovieController {
    @Autowired
    private MovieServiceInterface movieService;

    @Autowired
    private BaseConverter<Movie, MovieDto> movieConverter;

    @RequestMapping(value = "/movies", method = RequestMethod.GET)
    MoviesDto getAll(){
        //TODO log
        return new MoviesDto(movieConverter.
                convertModelsToDtos(movieService.getAll()));
    }

    @RequestMapping(value = "/movies", method = RequestMethod.POST)
    MovieDto save(@RequestBody MovieDto movieDto){
        //TODO log
        return movieConverter.convertModelToDto(movieService.save(
                movieConverter.convertDtoToModel(movieDto)));
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.PUT)
    MovieDto update(@PathVariable Long id, @RequestBody MovieDto movieDto){
        return movieConverter.convertModelToDto(movieService.update(id,
                movieConverter.convertDtoToModel(movieDto)));
    }

    @RequestMapping(value = "/movies/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable Long id){
        if (movieService.deleteById(id))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
