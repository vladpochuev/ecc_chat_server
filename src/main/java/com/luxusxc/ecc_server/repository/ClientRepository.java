package com.luxusxc.ecc_server.repository;

import com.luxusxc.ecc_server.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
}
