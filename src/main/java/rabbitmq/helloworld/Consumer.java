package rabbitmq.helloworld;

import com.rabbitmq.client.*;
import rabbitmq.tools.RabbitGetConnection;


import java.io.IOException;

public class Consumer {

    private static String queueName="wujianqinjian_queue_hello";
    public static void main(String[] args) throws Exception{
        //创建链接
        Connection connection= RabbitGetConnection.getConnection();
        //创建一个管道
        Channel channel=connection.createChannel();

        //声明交换机、队列相关属性
        String exchangeName="test_hello";

        // 声明一个队列
        channel.queueDeclare(queueName,false,false,false,null);

        //接收消息
        // 创建消费者
        DefaultConsumer consumer= new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //super.handleDelivery(consumerTag, envelope, properties, body);
                System.out.println(envelope.getExchange());
                String message = new String(body);

                System.out.println("[Receive]：" + message);
            }
        };
        channel.basicConsume(queueName,true,consumer);
    }

}
