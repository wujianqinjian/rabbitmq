package com.tabbitmq_temp;

import ch.qos.logback.core.util.ContextUtil;
import com.rabbitmq.client.*;
import rabbitmq.tools.RabbitGetConnection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class TempConsumer {
    public static void main(String[] args) throws Exception{



        try {
            Connection connection= RabbitGetConnection.getConnection();
            Channel channel=connection.createChannel();
        //声明交换机属性
        String exchangeName="test_direct_exchange";
        String exchangeType="test.direct";
        String queueName="test_direct_queue";
        String routingKey="test.direct";

        /*channel.exchangeDeclare(exchangeName, exchangeType, false, false, false, null);
        channel.queueDeclare(queueName, false, false, false, null);
        channel.queueBind(queueName, exchangeName, routingKey);

        DefaultConsumer consumer =new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("收到的消息"+new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };*/

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            };
            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });



        //channel.basicConsume(queueName, false,consumer);
        }catch (IOException e){

        }catch (TimeoutException e){

        }

    }
}
