package rabbitmq.work;

import com.rabbitmq.client.*;
import rabbitmq.tools.RabbitGetConnection;

import java.io.IOException;

public class Consumer1 {

    private static final String queueName="mqWork";

    public static void main(String[] args) throws Exception{
        Connection connection= RabbitGetConnection.getConnection();
        Channel channel=connection.createChannel();
        channel.queueDeclare( queueName, false, false, false, null);

        
        DefaultConsumer defaultConsumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException{
                System.out.println("消费者1：现在接收到: 第"+new String(body)+"条消息");
                try {
                    Thread.sleep(500);
                }catch (Exception e){
                    System.out.print("");
                }
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        channel.basicConsume(queueName, false, defaultConsumer);
     }

}
