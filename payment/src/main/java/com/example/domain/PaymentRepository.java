package com.example.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}


//afka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 3 --topic created_article