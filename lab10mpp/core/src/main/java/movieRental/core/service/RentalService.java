package movieRental.core.service;

import movieRental.core.model.Rental;
import movieRental.core.repository.ClientRepository;
import movieRental.core.repository.MovieRepository;
import movieRental.core.repository.RentalRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class RentalService implements RentalServiceInterface {

    @Autowired
    private RentalRepository rentalRepository;

    public static final Logger log = LoggerFactory.getLogger(RentalService.class);


    @Override
    public List<Rental> getAll() {
        log.trace("filter rentals - method entered");
        log.trace("filter rentals - method ended");
        return rentalRepository.findAll();
    }

    @Override
    public Rental save(Rental entity) {
        log.trace("save rental - method entered");
        Rental result = rentalRepository.save(entity);
        log.debug("save - added ", result);
        log.trace("save rental - method ended");
        return result;
    }

    @Override
    public Boolean deleteById(Long id) {
        log.trace("delete rental - method entered");
        AtomicReference<Boolean> deleted = new AtomicReference<>(false);
        rentalRepository.findById(id).ifPresent(rental -> {
            rentalRepository.delete(rental);
            deleted.set(true);
            log.debug("delete - deleted ", rental);
        });
        log.trace("delete rental - method ended");
        return deleted.get();
    }


    @Override
    @Transactional
    public Rental update(Long id, Rental entity) {
        log.trace("update rental - method entered");
        AtomicReference<Rental> returnValue = new AtomicReference<>(new Rental());
        rentalRepository.findById(id).ifPresent(
                rental -> {
                    rental.setClient(entity.getClient());
                    rental.setMovie(entity.getMovie());
                    log.debug("update - updated ", rental);
                }
        );
        log.trace("update rental - method ended");
        return returnValue.get();
    }

    @Override
    public List<Rental> filter(Long clientID) {
        log.trace("filter rentals - method entered");
        log.trace("filter rentals - method ended");
        return rentalRepository.findAll().stream()
                .filter(rental -> rental.getClient().getId().equals(clientID))
                .collect(Collectors.toList());
    }
}
