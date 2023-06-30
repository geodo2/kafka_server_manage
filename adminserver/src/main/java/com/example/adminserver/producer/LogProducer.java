package com.example.adminserver.producer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.Future;

public class LogProducer {
    private static final String TOPIC_NAME = "my-topic";
    private static final String BOOTSTRAP_SERVERS = "kafka_host:9092";

    public void sendLogMessage(String logMessage) {
        Properties props = new Properties();
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);

        try {
            ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC_NAME, logMessage);
            Future<RecordMetadata> future = producer.send(record);
            RecordMetadata metadata = future.get();

            System.out.println("Log message sent to topic: " + metadata.topic());
            System.out.println("Partition: " + metadata.partition());
            System.out.println("Offset: " + metadata.offset());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
    }
}
