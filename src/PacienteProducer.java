import javax.jms.*;

import org.apache.activemq.ActiveMQConnectionFactory;

public class PacienteProducer implements Runnable {

    public void run() {
        try { // Create a connection factory.
            ActiveMQConnectionFactory factory =
                    new ActiveMQConnectionFactory("tcp://localhost:61616");

            //Create connection.
            Connection connection = factory.createConnection();


            // Start the connection
            connection.start();

            // Create a session which is non transactional
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create Destination queue
            Destination queue = session.createQueue("br.lucas");

            // Create a producer
            MessageProducer producer = session.createProducer(queue);

            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            //String msg = "Cuma é a história homi?";

            // insert message
            //TextMessage message = session.createTextMessage(msg);
            //System.out.println("Producer Sent: " + msg);
            //producer.send(message);

            Paciente p = new Paciente();
            p.setNome("Lucas Barbosa");
            p.setId(1);
            p.setMsg("bla bla bla");

            ObjectMessage objm = session.createObjectMessage(p);

            System.out.println("Mensagem enviada para o paciente " + p.getNome() + "/" + p.getId());
            producer.send(objm);

            session.close();
            connection.close();
        }
        catch (Exception ex) {
            System.out.println("Exception Occured");
        }
    }
}