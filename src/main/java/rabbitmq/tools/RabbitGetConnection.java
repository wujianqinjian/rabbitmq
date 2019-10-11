package rabbitmq.tools;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * Created by JianH on 2019/10/3.
 */
public class RabbitGetConnection{

    public static Connection getConnection() throws Exception{
        ConnectionFactory connectionFactory=new ConnectionFactory();
        connectionFactory.setHost("123.206.224.131");
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPort(5672);
        //connectionFactory.setUsername("guest");
        //connectionFactory.setPassword("guest");
        Connection connection=connectionFactory.newConnection();
        System.out.println(connection);
        return connectionFactory.newConnection();
    }

}
