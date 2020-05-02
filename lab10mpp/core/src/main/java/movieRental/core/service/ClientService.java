package movieRental.core.service;

import movieRental.core.model.Client;
import movieRental.core.repository.ClientRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import java.util.stream.Collectors;


@Service
public class ClientService implements ClientServiceInterface {

    @Autowired
    private ClientRepository clientRepository;

    public static final Logger log = LoggerFactory.getLogger(ClientService.class);

    @Override
    public List<Client> getAll() {
        log.trace("getAll clients - method entered");
        log.trace("getAll clients - method ended");
        return clientRepository.findAll();
    }

    @Override
    public Client save(Client entity) {
        log.trace("save client - method entered");
        Client result = clientRepository.save(entity);
        log.debug("save - added ", result);
        log.trace("save client - method ended");
        return result;
    }

    @Override
    public Boolean deleteById(Long id) {
        log.trace("delete client - method entered");
        AtomicBoolean deleted = new AtomicBoolean(false);
        clientRepository.findById(id).ifPresent(client -> {
            clientRepository.delete(client);
            deleted.set(true);
            log.debug("delete - deleted ", client);
        });
        log.trace("delete client - method ended");
        return deleted.get();
    }

    @Override
    @Transactional
    public Client update(Long id, Client entity) {
        log.trace("update client - method entered");
        clientRepository.findById(id).ifPresent(client -> {
            client.setFirstname(entity.getFirstname());
            log.debug("update - updated ", client);
        });
        log.trace("update client - method ended");

        return entity;
    }

    @Override
    public List<Client> filter(String name) {
        log.trace("filter clients - method entered");
        log.trace("filter clients - method ended");
        return clientRepository.findAll().stream()
                .filter(client -> client.getFirstname().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
}
