package com.rabbitmq_topic_unfinished;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {


    public static void main(String[] args) throws Exception{
        //创建链接
        ConnectionFactory connectionFactory= new ConnectionFactory();
        connectionFactory.setHost("123.206.224.131");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        Connection connection=connectionFactory.newConnection();
        //创建一个管道
        Channel channel=connection.createChannel();

        //声明交换机、队列相关属性
        String exchangeName="test_direct_exchange";
        String exchangeType="direct";
        String queueName="test_direct_queue";
        String routingKey="test.direct";
        //声明一个交换机
        channel.exchangeDeclare(exchangeName,exchangeType,true,false,false,null);
        // 声明一个队列
        channel.queueDeclare(queueName,true,false,false,null);
        //将交换机与队列绑定
        channel.queueBind(queueName,exchangeName,routingKey);

        // 创建消费者
        DefaultConsumer consumer= new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //super.handleDelivery(consumerTag, envelope, properties, body);
                String message = new String(body);
                System.out.println("[Receive]：" + message);
            }
        };
        channel.basicConsume(queueName,true,consumer);

        //test
    }

}
