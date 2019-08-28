package com.rabbitmq_confirm;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
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
        channel.confirmSelect();
        //3 定义交换机和路由key
        String exchangeName="test_confirm_exchange";
        String routingKey="confirm.save";
        //4 发送消息
        String testConfirmMessage="Test confirm message!";
        channel.basicPublish(exchangeName,routingKey,null,testConfirmMessage.getBytes());
        //5 添加确认监听
        channel.addConfirmListener(new ConfirmListener() {
            @Override
            public void handleAck(long l, boolean b) throws IOException {
                System.out.println("ACK!");
            }

            @Override
            public void handleNack(long l, boolean b) throws IOException {
                System.out.println("No ACK!");
            }
        });
    }
}
