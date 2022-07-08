package org.example;

import org.example.dao.ClientDao;
import org.example.dao.OrderDao;
import org.example.entity.Client;
import org.example.entity.Order;
import org.example.utils.ClientState;
import org.example.utils.OrderState;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RelationshipTest {

    @Test
    public void ManyToOne() {

        Client client = new Client();
        client.setCompanyName("Sopra Steria");
        client.setFirstName("Marion");
        client.setLastName("Perez");
        client.setPhone("0786842676");
        client.setEmail("marioncanelle2@gmail.com");
        client.setAddress("1234 rue de la mairie");
        client.setZipCode("31700");
        client.setCity("Blagnac");
        client.setCountry("France");
        client.setClientState(ClientState.ACTIVE);
        ClientDao.createClient(client);

        Order order = new Order();
        order.setTypePresta("Formation Test");
        order.setDesignation("Angular");
        order.setNbDays(5);
        order.setUnitPrice(950F);
        order.setOrderState(OrderState.CONFIRMED);
        order.setClient(client);
        OrderDao.createOrder(order);

        // Test de la correspondance client et clientId

        assertEquals(client.getId(), order.getClient().getId() );

        assertEquals(order.getClient(),client);

        // Test du delete cascade : si je supprime le client, est ce que l'order associée supprimée

        List<Order> orders = OrderDao.findAllOrders();
        int listLength = orders.size();

        ClientDao.deleteClient(client);

        orders = OrderDao.findAllOrders();
        assertEquals(listLength - 1, orders.size());

        // Test autrement

        assertNull(ClientDao.findClientById(client.getId()));
    }
}