package rabbitmq.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.tools.RabbitGetConnection;

public class Producer {

    private static String QUEUE_NAME="wujianqinjian_queue_one";
    public static void main(String[] args) throws Exception{
        //创建链接
        Connection connection= RabbitGetConnection.getConnection();
        //创建通道
        Channel channel=connection.createChannel();
        //声明队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        //发送消息

        //channel.basicPublish("","",QUEUE_NAME,null,msg.getBytes());
        for (int i = 1; i < 100; i++) {
            String msg="国庆第"+i+"条消息!";
            channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
            Thread.sleep(100);
            System.out.println(msg);
        }
        //关闭链接
        channel.close();
        connection.close();
    }

}
