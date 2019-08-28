package com.rabbitmq_confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建ConnectionFactory，定义链接属性
        ConnectionFactory connectionFactory= new ConnectionFactory();
        connectionFactory.setHost("123.206.224.131");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/");
        //1 创建链接
        Connection connection=connectionFactory.newConnection();
        //2 创建管道
        Channel channel=connection.createChannel();



    }
}
