package rabbitmq.publishSubscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import rabbitmq.tools.RabbitGetConnection;

/**
 * Created by JianH on 2019/10/5.
 */
public class Sender {

    private static final String EXCHANGE_NAME="wujianqinjian_rabbitmq_publish";
    public static void main(String[] args) throws Exception{

        Connection connection= RabbitGetConnection.getConnection();
        Channel channel=connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        for (int i = 1; i <11 ; i++) {
            String msg="国庆发布的第"+i+"条消息";
            channel.basicPublish(EXCHANGE_NAME,"",null,msg.getBytes());
            System.out.println(msg);
        }
        channel.close();
        connection.close();
    }
}
