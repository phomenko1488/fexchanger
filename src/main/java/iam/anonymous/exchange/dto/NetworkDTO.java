package iam.anonymous.exchange.dto;

import iam.anonymous.exchange.domain.Network;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NetworkDTO {
    private Long id;
    private String name;
    private String regex;

    public NetworkDTO(Network network) {
        this.id = network.getId();
        this.name = network.getName();
        this.regex = network.getRegex();
    }
}
