package controller;

import converter.BaseConverter;
import dto.ClientDto;
import dto.ClientsDto;
import movieRental.core.model.Client;
import movieRental.core.service.ClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class ClientController {

    @Autowired
    private ClientServiceInterface clientService;

    @Autowired
    private BaseConverter<Client, ClientDto> clientConverter;

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    ClientsDto getAll(){
        //TODO log
        return new ClientsDto(clientConverter.
                convertModelsToDtos(clientService.getAll()));
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    ClientDto save(@RequestBody ClientDto clientDto){
        //TODO log
        return clientConverter.convertModelToDto(clientService.save(
                clientConverter.convertDtoToModel(clientDto)));
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)
    ClientDto update(@PathVariable Long id,@RequestBody ClientDto clientDto){
        return  clientConverter.convertModelToDto(clientService.update(id,
                clientConverter.convertDtoToModel(clientDto)));
    }

    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    ResponseEntity<?> delete(@PathVariable Long id){
        if (clientService.deleteById(id))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/clients/{name}", method = RequestMethod.GET)
    ClientsDto filter(@PathVariable String name){
        return new ClientsDto(clientConverter.
                convertModelsToDtos(clientService.filter(name)));
    }
}
