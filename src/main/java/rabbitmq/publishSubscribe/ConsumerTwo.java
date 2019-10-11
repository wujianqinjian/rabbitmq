package rabbitmq.publishSubscribe;

import com.rabbitmq.client.*;
import rabbitmq.tools.RabbitGetConnection;

import java.io.IOException;

/**
 * Created by JianH on 2019/10/5.
 */
public class ConsumerTwo {

    private static final String EXCHANGE_NAME="wujianqinjian_rabbitmq_publish";
    public static void main(String[] args) throws  Exception{
        Connection connection= RabbitGetConnection.getConnection();
        Channel channel=connection.createChannel();
        channel.queueDeclare("publish_queue2",false,false,false,null);
        channel.queueBind("publish_queue2",EXCHANGE_NAME,"");
        channel.basicQos(1);
        DefaultConsumer defaultConsumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                channel.basicAck(envelope.getDeliveryTag(),false);
                System.out.println(new String(body));
            }
        };
        channel.basicConsume("publish_queue2",false,defaultConsumer);


    }
}
