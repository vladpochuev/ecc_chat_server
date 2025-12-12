package com.luxusxc.ecc_server.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "messages")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fromClient;
    private String toClient;

    @Lob
    private String ciphertext;
    @Lob
    private String ephemeralPubKey;
    @Lob
    private String nonce;
    @Lob
    private String authTag;

    private long timestamp;
}
