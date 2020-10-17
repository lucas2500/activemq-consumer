import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.w3c.dom.Text;

import java.awt.desktop.SystemSleepEvent;

public class PacienteConsumer implements Runnable {

    @Override
    public void run() {
        try {

            //-- Create a connection factory.
            ActiveMQConnectionFactory factory =
                    new ActiveMQConnectionFactory("tcp://localhost:61616");
            factory.setTrustAllPackages(true);

            //-- Create connection.
            Connection connection = factory.createConnection();

            //-- Start the connection
            connection.start();

            //-- Cria a sessão
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            //-- Cria a fila e informa qual o destinatário.
            Destination queue = session.createQueue("br.lucas");
            MessageConsumer consumer = session.createConsumer(queue);
            Message message = consumer.receive();

            //-- Envia a mensagem via TextMessage
            /*
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                String text = textMessage.getText();
                System.out.println("Consumer Received: " + text);
            }
             */

            //-- Envia a mensagem via ObjectMessage
            if (message instanceof ObjectMessage){
                ObjectMessage objectMessage = (ObjectMessage) message;
                Paciente p = (Paciente) objectMessage.getObject();
                System.out.println("O paciente " + p.getNome() + "/" + p.getId() + " recebeu uma mensagem: ");
                System.out.println(p.getMsg());
            }

            session.close();

            connection.close();
        }
        catch (Exception ex) {
            System.out.println("Exception Occured" + ex);
        }
    }
}