package iam.anonymous.exchange.dto;

import iam.anonymous.exchange.domain.Token;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenDTO {
    private Long id;
    private String name;
    private String abbreviation;
    private String img;
    private Double price;
    private Double difference;

    private NetworkDTO network;

    public TokenDTO(Token token) {
        this.id = token.getId();
        this.name = token.getName();
        this.abbreviation = token.getAbbreviation();
        this.img = token.getImg();
        this.price = token.getPrice();
        this.network = new NetworkDTO(token.getNetwork());
        this.difference= token.getDifference();
    }
}
