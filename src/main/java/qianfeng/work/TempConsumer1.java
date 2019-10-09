package qianfeng.work;

import com.rabbitmq.client.*;

import java.io.IOException;

public class TempConsumer1 {

    private static final String queueName="mqWork";

    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("123.206.224.131");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPort(5672);

        Connection connection=connectionFactory.newConnection();
        Channel channel=connection.createChannel();
        channel.queueDeclare( queueName, false, false, false, null);

        
        DefaultConsumer defaultConsumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException{
                System.out.println("消费者2收到的消息:   "+new String(body));
                try {
                    Thread.sleep(11);
                }catch (Exception e){

                }
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(queueName, false, defaultConsumer);
     }

}
