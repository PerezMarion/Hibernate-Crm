package org.example;

import org.example.dao.ClientDao;
import org.example.dao.OrderDao;
import org.example.entity.Client;
import org.example.entity.Order;
import org.example.utils.ClientState;
import org.example.utils.OrderState;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class OrderTest {

    @Test
    public void createAndFindOrderById() {

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
        assertTrue(true);

        Order order1 = OrderDao.findOrderById(order.getId());
        assertEquals("Formation Test", order1.getTypePresta());

        OrderDao.deleteOrder(order);
        ClientDao.deleteClient(client);
    }

    @Test
    public void DontFindOrderById() {

        Order order2 = OrderDao.findOrderById(0);
        assertNull(order2);
    }

    @Test
    public void findAllOrders() {

        List<Order> orders = OrderDao.findAllOrders();
        assertTrue(orders.size() > 0);
    }

    @Test
    public void deleteOrder(){

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

        List<Order> orders = OrderDao.findAllOrders();
        int listLength = orders.size();

        OrderDao.deleteOrder(order);

        orders = OrderDao.findAllOrders();
        assertEquals(listLength - 1, orders.size());

        ClientDao.deleteClient(client);
    }

    @Test
    public void deleteOrderById() {

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

        Order order1 = new Order();
        order1.setTypePresta("Formation Test");
        order1.setDesignation("Angular");
        order1.setNbDays(5);
        order1.setUnitPrice(950F);
        order1.setOrderState(OrderState.CONFIRMED);
        order1.setClient(client);
        OrderDao.createOrder(order1);

        Order order2 = new Order();
        order2.setTypePresta("Coaching Test");
        order2.setDesignation("Jakarta EE");
        order2.setNbDays(10);
        order2.setUnitPrice(1200F);
        order2.setOrderState(OrderState.OPTION);
        order2.setClient(client);
        OrderDao.createOrder(order2);

        OrderDao.deleteOrderById(order1.getId());

        assertNull(OrderDao.findOrderById(order1.getId()));
        assertNotNull(OrderDao.findOrderById(order2.getId()));

        OrderDao.deleteOrder(order2);
        ClientDao.deleteClient(client);
    }

    @Test
    public void updateOrder() {

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

        Order newOrderData = new Order();
        newOrderData.setUnitPrice(999.99F);
        OrderDao.updateOrder(order.getId(), newOrderData);

        Order updatedOrder = OrderDao.findOrderById(order.getId());
        assertEquals(new Float(999.99F), updatedOrder.getUnitPrice());

        ClientDao.deleteClient(client);
    }

    @Test
    public void findOrderByTypePrestation() {

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

        Order order1 = new Order();
        order1.setTypePresta("Formation Test");
        order1.setDesignation("Angular");
        order1.setNbDays(5);
        order1.setUnitPrice(950F);
        order1.setOrderState(OrderState.CONFIRMED);
        order1.setClient(client);
        OrderDao.createOrder(order1);

        Order order2 = new Order();
        order2.setTypePresta("Coaching Test");
        order2.setDesignation("Jakarta EE");
        order2.setNbDays(10);
        order2.setUnitPrice(1200F);
        order2.setOrderState(OrderState.OPTION);
        order2.setClient(client);
        OrderDao.createOrder(order2);

        Order order3 = new Order();
        order3.setTypePresta("Coaching Test");
        order3.setDesignation("CSS");
        order3.setNbDays(2);
        order3.setUnitPrice(800.50F);
        order3.setOrderState(OrderState.CANCELLED);
        order3.setClient(client);
        OrderDao.createOrder(order3);

        List<Order> coachingTestPresta = OrderDao.findOrderByTypePresta("Coaching Test");
        assertEquals(2, coachingTestPresta.size());

        OrderDao.deleteOrder(order1);
        OrderDao.deleteOrder(order2);
        OrderDao.deleteOrder(order3);
        ClientDao.deleteClient(client);
    }
}