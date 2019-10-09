package qianfeng.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import rabbitmq.tools.RabbitGetConnection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class TempProducer {

    private static final String queueName="mqWork";
    public static void main(String[] args) throws Exception{
        //2.创建连接
        try {
            Connection connection= RabbitGetConnection.getConnection();
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
