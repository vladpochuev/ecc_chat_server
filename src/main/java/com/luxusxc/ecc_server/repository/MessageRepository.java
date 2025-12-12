package com.luxusxc.ecc_server.repository;

import com.luxusxc.ecc_server.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByFromClientAndToClientOrFromClientAndToClientOrderByTimestamp(
            String from1, String to1, String from2, String to2
    );
}
