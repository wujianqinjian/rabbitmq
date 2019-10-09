package qianfeng.work;

import com.rabbitmq.client.*;

import java.io.IOException;

public class TempConsumer2 {

    private static final String QUEUE="mqWork";

    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("123.206.224.131");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPort(5672);

        Connection connection=connectionFactory.newConnection();
        Channel channel=connection.createChannel();
        channel.queueDeclare( QUEUE, false, false, false, null);

        
        DefaultConsumer defaultConsumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("这是什么时候的消息:   "+new String(body));
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(QUEUE, false, defaultConsumer);
     }

}
