package rabbitmq.work;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.tools.RabbitGetConnection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

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
            String msg="生产者1开始发送第";
            String msg_end="条消息";
            //channel.basicPublish(exchangeName,queueName, null, msg.getBytes());
            for (int i = 1; i < 1010; i++) {
                channel.basicPublish("", queueName, null, (msg+i).getBytes());
                System.out.print(msg+i+msg_end);
                System.out.println(msg);
            }

            channel.close();
            connection.close();
        }catch (IOException e){

        }catch (TimeoutException e){

        }

    }
}
