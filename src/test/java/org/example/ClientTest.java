package org.example;

import org.example.dao.ClientDao;
import org.example.entity.Client;
import org.example.utils.ClientState;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class ClientTest {

    @Test
    public void createAndFindClientById() {

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
        assertTrue(true);

        Client client1 = ClientDao.findClientById(client.getId());
        assertEquals("Marion", client1.getFirstName());

        ClientDao.deleteClient(client);
    }

    @Test
    public void DontFindClientById() {

        Client client2 = ClientDao.findClientById(0);
        assertNull(client2);
    }

    @Test
    public void findAllClients() {

        List<Client> clients = ClientDao.findAllClients();
        assertTrue(clients.size() > 0);
    }

    @Test
    public void deleteClient(){

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

        List<Client> clients = ClientDao.findAllClients();
        int listLenght = clients.size();

        ClientDao.deleteClient(client);

        clients = ClientDao.findAllClients();
        assertEquals(listLenght - 1, clients.size());
    }

    @Test
    public void deleteClientById() {

        Client client1 = new Client();
        client1.setCompanyName("Sopra Steria");
        client1.setFirstName("Marion");
        client1.setLastName("Perez");
        client1.setPhone("0786842676");
        client1.setEmail("marioncanelle2@gmail.com");
        client1.setAddress("1234 rue de la mairie");
        client1.setZipCode("31700");
        client1.setCity("Blagnac");
        client1.setCountry("France");
        client1.setClientState(ClientState.ACTIVE);
        ClientDao.createClient(client1);

        Client client2 = new Client();
        client2.setCompanyName("Sopra Steria");
        client2.setFirstName("Ludovic");
        client2.setLastName("Raulin");
        client2.setPhone("0123456789");
        client2.setEmail("lraulin843@gmail.com");
        client2.setAddress("1234 rue de l'eglise");
        client2.setZipCode("67000");
        client2.setCity("Strasbourg");
        client2.setCountry("France");
        client2.setClientState(ClientState.ACTIVE);
        ClientDao.createClient(client2);

        ClientDao.deleteClientById(client1.getId());

        assertNull(ClientDao.findClientById(client1.getId()));
        assertNotNull(ClientDao.findClientById(client2.getId()));

        ClientDao.deleteClientById(client2.getId());
    }

    @Test
    public void updateClient() {

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

        Client newClientData = new Client();
        newClientData.setEmail("marion.oceane.perez@gmail.com");
        ClientDao.updateClient(client.getId(), newClientData);

        Client updatedCustomer = ClientDao.findClientById(client.getId());
        assertEquals("marion.oceane.perez@gmail.com", updatedCustomer.getEmail());

        ClientDao.deleteClient(client);
    }

    @Test
    public void findClientByCompanyName() {

        Client client1 = new Client();
        client1.setCompanyName("Test");
        client1.setFirstName("Marion");
        client1.setLastName("Perez");
        client1.setPhone("0786842676");
        client1.setEmail("marioncanelle2@gmail.com");
        client1.setAddress("1234 rue de la mairie");
        client1.setZipCode("31700");
        client1.setCity("Blagnac");
        client1.setCountry("France");
        client1.setClientState(ClientState.ACTIVE);
        ClientDao.createClient(client1);

        Client client2 = new Client();
        client2.setCompanyName("Test");
        client2.setFirstName("Ludovic");
        client2.setLastName("Raulin");
        client2.setPhone("0123456789");
        client2.setEmail("lraulin843@gmail.com");
        client2.setAddress("1234 rue de l'eglise");
        client2.setZipCode("67000");
        client2.setCity("Strasbourg");
        client2.setCountry("France");
        client2.setClientState(ClientState.ACTIVE);
        ClientDao.createClient(client2);

        Client client3 = new Client();
        client3.setCompanyName("Pas test");
        client3.setFirstName("Valentin");
        client3.setLastName("Payet");
        client3.setPhone("0987654321");
        client3.setEmail("valentinpayet06@gmail.com");
        client3.setAddress("321 rue de l'ecole");
        client3.setZipCode("33000");
        client3.setCity("Bordeaux");
        client3.setCountry("France");
        client3.setClientState(ClientState.INACTIVE);
        ClientDao.createClient(client3);

        List<Client> testCompany = ClientDao.findClientByCompanyName("Test");
        assertEquals(2, testCompany.size());

        ClientDao.deleteClient(client1);
        ClientDao.deleteClient(client2);
        ClientDao.deleteClient(client3);
    }
}