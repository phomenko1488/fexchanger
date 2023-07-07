package iam.anonymous.exchange.repository;

import iam.anonymous.exchange.domain.Request;
import iam.anonymous.exchange.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RequestRepository extends JpaRepository<Request, String> {
    Request getRequestById(String id);

    List<Request> findAllByExpiryDateBeforeAndRequestStatusIs(Date now, RequestStatus status);
}
