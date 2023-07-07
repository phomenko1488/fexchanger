package iam.anonymous.exchange.dto;

import iam.anonymous.exchange.domain.Request;
import iam.anonymous.exchange.enums.RequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetRequestDTO {
    private String id;
    private TokenDTO from;
    private TokenDTO to;
    private String receiverAddress;
    private String addressToSend;
    private BigDecimal tokenFromAmount;
    private BigDecimal tokenToAmount;

    private Date creationDate;
    private Date expiryDate;

    private RequestStatus requestStatus;

    public GetRequestDTO(Request request) {
        if (request != null) {
            this.id = request.getId();
            this.from = new TokenDTO(request.getFrom());
            this.to = new TokenDTO(request.getTo());
            this.tokenFromAmount = request.getTokenFromAmount();
            this.tokenToAmount = request.getTokenToAmount();
            this.creationDate = request.getCreationDate();
            this.expiryDate = request.getExpiryDate();
            this.requestStatus = request.getRequestStatus();
            this.receiverAddress = request.getReceiverAddress();
            this.addressToSend = request.getAddressToSend();
        }
    }
}
