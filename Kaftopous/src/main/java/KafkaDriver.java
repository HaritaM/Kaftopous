import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class KafkaDriver{public static void main(String[] args) { 
	  int numConsumers = 1;
	  String groupId = "test-consumer-group";
	  List<String> topics = Arrays.asList("demo");
	  ExecutorService executor = Executors.newFixedThreadPool(numConsumers);

	  final List<KaftopousConsumer> consumers = new ArrayList<>();
	  for (int i = 0; i < numConsumers; i++) {
		  KaftopousConsumer consumer = new KaftopousConsumer(i, groupId, topics);
	    consumers.add(consumer);
	    executor.submit(consumer);
	  }

	  Runtime.getRuntime().addShutdownHook(new Thread() {
	    @Override
	    public void run() {
	      for (KaftopousConsumer consumer : consumers) {
	        consumer.shutdown();
	      } 
	      executor.shutdown();
	      try {
	        executor.awaitTermination(5000, TimeUnit.MILLISECONDS);
	      } catch (InterruptedException e) {
	        e.getStackTrace();
	      }
	    }
	  });
	}

}