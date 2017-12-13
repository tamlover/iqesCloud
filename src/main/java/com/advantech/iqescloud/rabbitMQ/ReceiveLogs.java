package com.advantech.iqescloud.rabbitMQ;

import com.rabbitmq.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
@Lazy(value = false)
public class ReceiveLogs {
  private static final String EXCHANGE_NAME = "logs";

  @Autowired
  private Dispatcher dispatcher;

  @PostConstruct
  public void receiveMessage() throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("172.21.84.91");
    factory.setUsername("testUser");
    factory.setPassword("123");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
    String queueName = channel.queueDeclare().getQueue();
    channel.queueBind(queueName, EXCHANGE_NAME, "update_restaurant_info");

    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

    Consumer consumer = new DefaultConsumer(channel) {
      @Override
      public void handleDelivery(String consumerTag, Envelope envelope,
                                 AMQP.BasicProperties properties, byte[] body) throws IOException {
        String message = new String(body, "UTF-8");
        System.out.println(" [x] Received '" + message + "'");
        dispatcher.dispatcherCommmand(message);

      }
    };
    channel.basicConsume(queueName, true, consumer);
  }
}