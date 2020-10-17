public class ConsomeMensagem {

    public static void main(String[] args) {

        System.setProperty("org.apache.activemq.SERIALIZABLE_PACKAGES","*");
        PacienteConsumer consumer = new PacienteConsumer();

        consumer.run();
    }
}
