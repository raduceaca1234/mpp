package controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import converter.BaseConverter;
import dto.ClientsDto;
import dto.RentalDto;
import dto.RentalsDto;
import movieRental.core.model.Rental;
import movieRental.core.service.RentalServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RentalController {

    @Autowired
    private RentalServiceInterface rentalService;

    @Autowired
    private BaseConverter<Rental, RentalDto> rentalConverter;

    @RequestMapping(value = "/rentals", method = RequestMethod.GET)
    RentalsDto getAll(){
        //TODO log
        return new RentalsDto(rentalConverter.
                convertModelsToDtos(rentalService.getAll()));
    }

    @RequestMapping(value = "/rentals", method = RequestMethod.POST)
    RentalDto save(@RequestBody RentalDto rentalDto){
        return rentalConverter.convertModelToDto(rentalService.save(
                rentalConverter.convertDtoToModel(rentalDto)));
    }

    @RequestMapping(value = "/rentals/{id}", method = RequestMethod.PUT)
    RentalDto update(@PathVariable Long id, @RequestBody RentalDto rentalDto){
        return rentalConverter.convertModelToDto(rentalService.update(id,
                rentalConverter.convertDtoToModel(rentalDto)));
    }

    @RequestMapping(value = "/rentals/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable Long id){
        if (rentalService.deleteById(id))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/rentals/{id}", method = RequestMethod.GET)
    RentalsDto filter(@PathVariable Long clientID){
        return new RentalsDto(rentalConverter.
                convertModelsToDtos(rentalService.filter(clientID)));
    }
}
