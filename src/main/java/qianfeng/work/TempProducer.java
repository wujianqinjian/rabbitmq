package qianfeng.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TempProducer {

    private static final String queueName="mqWork";
    public static void main(String[] args) {
        //1.创建连接工厂
        ConnectionFactory connectionFactoryCm=new ConnectionFactory();
        connectionFactoryCm.setPort(5672);
        //主机地址
        connectionFactoryCm.setHost("123.206.224.131");
        //主机目录
        connectionFactoryCm.setVirtualHost("/");
        //2.创建连接
        try {
            Connection connection =connectionFactoryCm.newConnection();
        //3.创建消息信道
            Channel channel=connection.createChannel();
        //4.声明队列
            channel.queueDeclare(queueName, false, false, false, null);
        //5.发送消息
            String msg="This is tmp message";
            //channel.basicPublish(exchangeName,queueName, null, msg.getBytes());
            for (int i = 0; i < 100; i++) {
                channel.basicPublish("", queueName, null, ("发送的消息"+i).getBytes());
                System.out.print(i+""+"----");
                System.out.println(msg.getBytes());
            }

            channel.close();
            connection.close();
        }catch (IOException e){

        }catch (TimeoutException e){

        }

    }
}
