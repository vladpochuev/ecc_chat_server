package com.luxusxc.ecc_server.conroller;

import com.luxusxc.ecc_server.model.Client;
import com.luxusxc.ecc_server.model.Message;
import com.luxusxc.ecc_server.repository.ClientRepository;
import com.luxusxc.ecc_server.repository.MessageRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ChatController {
    private final ClientRepository clientRepository;
    private final MessageRepository messageRepository;

    public ChatController(ClientRepository clientRepository, MessageRepository messageRepository) {
        this.clientRepository = clientRepository;
        this.messageRepository = messageRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerClient(@RequestBody Client client) {
        clientRepository.save(client);
        return ResponseEntity.ok().body("ok");
    }

    @GetMapping("/clients")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @GetMapping("/clients/{clientId}")
    public ResponseEntity<Client> getClient(@PathVariable String clientId) {
        return clientRepository.findById(clientId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/messages/send")
    public ResponseEntity<?> sendMessage(@RequestBody Message message) {
        if (!clientRepository.existsById(message.getToClient())) {
            return ResponseEntity.badRequest().body("Recipient not found");
        }
        messageRepository.save(message);
        return ResponseEntity.ok("queued");
    }

    @GetMapping("/messages/history/{clientA}/{clientB}")
    public List<Message> getHistory(@PathVariable String clientA, @PathVariable String clientB) {
        return messageRepository.findByFromClientAndToClientOrFromClientAndToClientOrderByTimestamp(
                clientA, clientB,
                clientB, clientA
        );
    }
}
