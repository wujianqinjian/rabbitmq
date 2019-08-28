package com.rabbitmq_topic_unfinished;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Producer {

    public static void main(String[] args) throws Exception{
        ConnectionFactory connectionFactory= new ConnectionFactory();
        connectionFactory.setHost("123.206.224.131");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        Connection connection=connectionFactory.newConnection();
        Channel channel=connection.createChannel();

        String exchangeName="test_direct_exchange";
        String routingKey="test.direct";
        String msg="This is a special message!";
        //指定交换机和routingKey投递消息
        channel.basicPublish(exchangeName,routingKey,null,msg.getBytes());

    }



}
