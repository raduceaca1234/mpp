package converter;

import dto.ClientDto;
import movieRental.core.model.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter extends BaseConverter<Client, ClientDto> {
    @Override
    public Client convertDtoToModel(ClientDto dto) {
        Client client = Client.builder()
                .firstname(dto.getFirstname())
                .secondname(dto.getSecondname())
                .job(dto.getJob())
                .age(dto.getAge())
                .build();
        client.setId(dto.getId());
        return client;
    }

    @Override
    public ClientDto convertModelToDto(Client client) {
        ClientDto dto = ClientDto.builder()
                .firstname(client.getFirstname())
                .secondname(client.getSecondname())
                .job(client.getJob())
                .age(client.getAge())
                .build();
        dto.setId(client.getId());
        return dto;
    }
}
