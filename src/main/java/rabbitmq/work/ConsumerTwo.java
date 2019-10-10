package rabbitmq.work;

import com.rabbitmq.client.*;
import rabbitmq.tools.RabbitGetConnection;
import java.io.IOException;

public class ConsumerTwo {

    private static String QUEUE_NAME="wujianqinjian_rabbitmq_work";
    public static void main(String[] args) throws Exception{
        //创建链接
        Connection connection= RabbitGetConnection.getConnection();
        //创建管道
        Channel channel=connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        // 创建消费者
        DefaultConsumer consumer= new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消息为："+new String(body));
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        };
        channel.basicConsume(QUEUE_NAME,false,consumer);

    }

}
