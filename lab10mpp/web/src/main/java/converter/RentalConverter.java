package converter;

import dto.RentalDto;
import movieRental.core.model.Rental;
import movieRental.core.repository.ClientRepository;
import movieRental.core.repository.MovieRepository;
import movieRental.core.service.ClientServiceInterface;
import movieRental.core.service.MovieServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class RentalConverter extends BaseConverter<Rental, RentalDto> {
    @Autowired
    private ClientServiceInterface clientService;

    @Autowired
    private MovieServiceInterface movieService;

    @Override
    public Rental convertDtoToModel(RentalDto dto) {
        Rental rental = Rental.builder()
                .client(clientService.getAll()
                        .stream()
                        .filter(client ->
                                client.getId().equals(dto.getClientID())).collect(Collectors.toList()).get(0))
                .movie(movieService.getAll()
                        .stream()
                        .filter(movie ->
                                movie.getId().equals(dto.getMovieID())).collect(Collectors.toList()).get(0))
                .build();
        rental.setId(dto.getId());
        return rental;
    }

    @Override
    public RentalDto convertModelToDto(Rental rental) {
        RentalDto dto = RentalDto.builder()
                .clientID(rental.getClient().getId())
                .movieID(rental.getMovie().getId())
                .build();
        dto.setId(rental.getId());
        return dto;
    }
}
