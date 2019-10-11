package rabbitmq.work;

import com.rabbitmq.client.*;
import rabbitmq.tools.RabbitGetConnection;

import java.io.IOException;

public class Consumer4 {

    private static final String QUEUE="mqWork";

    public static void main(String[] args) throws Exception{
        Connection connection= RabbitGetConnection.getConnection();
        Channel channel=connection.createChannel();
        channel.queueDeclare( QUEUE, false, false, false, null);

        
        DefaultConsumer defaultConsumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException{
                System.out.println("消费者4：现在接收到: "+new String(body)+"条消息");
                channel.basicAck(envelope.getDeliveryTag(), false);
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    System.out.print("");
                }
            }
        };
        Thread.sleep(1000);
        channel.basicConsume(QUEUE, false, defaultConsumer);
     }

}
