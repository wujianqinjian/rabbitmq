package com.tabbitmq_temp;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import rabbitmq.tools.RabbitGetConnection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TempProducer {

    public static void main(String[] args)throws Exception {
        //2.创建连接
        try {
            Connection connection= RabbitGetConnection.getConnection();
        //3.创建消息信道
            Channel channel=connection.createChannel();
        //4.指定交换机和路由键
            String exchangeName="test_direct_exchange";
            String routingKey="test.direct";
            String exchangeType="test.direct";
            String queueName="test_direct_queue";
        //5.发送消息
            String msg="This is tmp message";
            channel.queueDeclare(queueName, false, false, false, null);
            //channel.basicPublish(exchangeName,queueName, null, msg.getBytes());
            channel.basicPublish(exchangeName, routingKey, null, msg.getBytes("UTF-8"));
            System.out.println(msg);
        }catch (IOException e){

        }catch (TimeoutException e){

        }

    }
}
