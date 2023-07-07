package iam.anonymous.exchange.domain;

import iam.anonymous.exchange.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "requests")
public class Request {
    @Id
    private String id;
    @ManyToOne
    private Token from;
    @ManyToOne
    private Token to;
    private String receiverAddress;
    private String addressToSend;

    private BigDecimal tokenFromAmount;
    private BigDecimal tokenToAmount;

    private Date creationDate;
    private Date expiryDate;
    @Enumerated(value = EnumType.STRING)
    private RequestStatus requestStatus;

}
