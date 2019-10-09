package qianfeng.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import rabbitmq.tools.RabbitGetConnection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;


public class TempConsumerHistory {
    private static String queueName="mqWork";
    public static void main(String[] args) throws Exception{



        try {
            Connection connection= RabbitGetConnection.getConnection();
            Channel channel=connection.createChannel();
        //声明交换机属性
        String exchangeName="test_direct_exchange";
        String exchangeType="test.direct";
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
            channel.queueDeclare(queueName, false, false, false, null);
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" 消费者1收到的消息 '" + message + "'");
                //channel.basicAck(envelope.getDeliveryTag(), false);
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            };

            channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });

        //channel.basicConsume(queueName, false,consumer);
        }catch (IOException e){

        }catch (TimeoutException e){

        }

    }
}
