package iam.anonymous.exchange.controller;

import iam.anonymous.exchange.domain.Request;
import iam.anonymous.exchange.dto.GetRequestDTO;
import iam.anonymous.exchange.dto.RequestCancelDTO;
import iam.anonymous.exchange.dto.RequestCreateDTO;
import iam.anonymous.exchange.dto.ResultDTO;
import iam.anonymous.exchange.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/requests")
@CrossOrigin(value = "*", origins = "*", allowedHeaders = "*", originPatterns = "*")
public class RequestController {
    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getRequestById(@PathVariable("id") String id) {
        GetRequestDTO dto = requestService.getById(id);
        if (dto != null)
            return ResponseEntity.ok().body(dto);
        else
            return ResponseEntity.badRequest().body(String.format("Request with id %s doesn't exist", id));
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody RequestCreateDTO dto) {
        try {
            Request request = requestService.create(dto);
            return ResponseEntity.ok().body(new ResultDTO(request.getId()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid arguments");
        }
    }

    @PostMapping("/cancel")
    public ResponseEntity<Object> cancel(@RequestBody RequestCancelDTO dto) {
        Request request = requestService.cancel(dto);
        if (request != null)
            return ResponseEntity.ok().body(new GetRequestDTO(request));
        else
            return ResponseEntity.badRequest().body(String.format("Request with id %s doesn't exist", dto.getId()));

    }
}
