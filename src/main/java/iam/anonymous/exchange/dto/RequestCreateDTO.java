package iam.anonymous.exchange.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestCreateDTO {
    private String receiverAddress;
    private String fromId;
    private String toId;
    private String tokenFromAmount;
    private String tokenToAmount;
}
