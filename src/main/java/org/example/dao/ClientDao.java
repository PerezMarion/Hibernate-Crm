package org.example.dao;

import org.example.entity.Client;
import org.example.jpa.EntityManagerSingleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class ClientDao {

    public static void createClient(Client clientToCreate) {

        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(clientToCreate);
        tx.commit();
    }

    public static Client findClientById(int id) {

        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        Client clientToFind = entityManager.find(Client.class, id);
        return clientToFind;
    }

    public static List<Client> findAllClients() {

        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        Query findAllQuery = entityManager.createQuery
                ("select c from Client c");
        return findAllQuery.getResultList();
    }

    public static void deleteClient(Client clientToDelete) {

        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.remove(clientToDelete);
        tx.commit();
    }

    public static void deleteClientById(Integer id) {

        Client clientToDelete = findClientById(id);
        deleteClient(clientToDelete);
    }

    public static void deleteClientByIdV2(Integer id) {

        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        Query deleteQuery = entityManager.createQuery("delete from Client c where c.id= :id");
        deleteQuery.setParameter("id", id);
        int result = deleteQuery.executeUpdate();
        entityManager.clear();

        tx.commit();
    }

    public static void updateClient(Integer id, Client newClientData) {

        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        Client clientToUpdate = entityManager.find(Client.class, id);
        clientToUpdate.setNotNullData(newClientData);

        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.merge(clientToUpdate);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    public static List<Client> findClientByCompanyName(String companyName) {

        EntityManager entityManager = EntityManagerSingleton.getEntityManager();

        Query queryToFindClientByCompanyName = entityManager.createQuery("select c from Client c where c.companyName = :companyName");
        queryToFindClientByCompanyName.setParameter("companyName", companyName);
        return queryToFindClientByCompanyName.getResultList();
    }
}