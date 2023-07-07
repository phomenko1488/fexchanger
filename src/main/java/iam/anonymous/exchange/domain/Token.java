package iam.anonymous.exchange.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "tokens")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String abbreviation;
    private String img;
    private Double price;
    private Double difference;
    private Integer decimals;

    @ManyToOne
    private Network network;

    @Override
    public String toString() {
        return "Token{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", abbreviation='" + abbreviation + '\'' +
                ", img='" + img + '\'' +
                ", price=" + price +
                ", difference=" + difference +
                ", decimals=" + decimals +
                ", network=" + network.getId() +
                '}';
    }
}
