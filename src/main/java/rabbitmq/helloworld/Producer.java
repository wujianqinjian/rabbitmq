package rabbitmq.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import rabbitmq.tools.RabbitGetConnection;

public class Producer {

    private static String QUEUE_NAME="wujianqinjian_queue_hello";
    public static void main(String[] args) throws Exception{
        //创建链接
        Connection connection= RabbitGetConnection.getConnection();
        //创建通道
        Channel channel=connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //发送消息
        String msg="国庆第一条消息!";
        //channel.basicPublish("","",QUEUE_NAME,null,msg.getBytes());
        for (int i = 1; i < 8000000; i++) {
            channel.basicPublish("", QUEUE_NAME, null, (msg+i).getBytes());
            //Thread.sleep(1);
            System.out.println(msg);
        }
        //关闭链接
        channel.close();
        connection.close();
    }

}
