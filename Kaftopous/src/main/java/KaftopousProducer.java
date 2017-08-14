import java.util.Properties;
import org.apache.kafka.clients.producer.Producer;
import java.util.Scanner;


public class KaftopousProducer {
	public static void main(String[] args) {

        //Configure the Producer
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        org.apache.kafka.clients.producer.Producer<String, String>producer = new org.apache.kafka.clients.producer.KafkaProducer<>(props);
        String mesg="helloworld";
        producer.send(new org.apache.kafka.clients.producer.ProducerRecord<String, String>("demo", null, mesg));
        producer.close();
        
     }
}
