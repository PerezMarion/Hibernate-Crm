package org.example.dao;

import org.example.entity.Order;
import org.example.jpa.EntityManagerSingleton;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

public class OrderDao {

    public static void createOrder(Order orderToCreate) {

        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(orderToCreate);
        tx.commit();
    }

    public static Order findOrderById(int id) {

        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        Order orderToFind = entityManager.find(Order.class, id);
        return orderToFind;
    }

    public static List<Order> findAllOrders() {

        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        Query findAllQuery = entityManager.createQuery
                ("select o from Order o");
        return findAllQuery.getResultList();
    }

    public static void deleteOrder(Order orderToDelete) {

        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.remove(orderToDelete);
        tx.commit();
    }

    public static void deleteOrderById(Integer id) {

        Order orderToDelete = findOrderById(id);
        deleteOrder(orderToDelete);
    }

    public static void deleteOrderByIdV2(Integer id) {

        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        Query deleteQuery = entityManager.createQuery("delete from Order o where o.id= :id");
        deleteQuery.setParameter("id", id);
        int result = deleteQuery.executeUpdate();
        entityManager.clear();

        tx.commit();
    }

    public static void updateOrder(int id, Order newOrderData) {

        EntityManager entityManager = EntityManagerSingleton.getEntityManager();
        Order orderToUpdate = entityManager.find(Order.class, id);
        orderToUpdate.setNotNullData(newOrderData);

        EntityTransaction tx = null;

        try {
            tx = entityManager.getTransaction();
            tx.begin();
            entityManager.merge(orderToUpdate);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        }
    }

    public static List<Order> findOrderByTypePresta(String typePresta) {
        EntityManager entityManager = EntityManagerSingleton.getEntityManager();

        Query queryToFindOrderByTypePresta = entityManager.createQuery("select o from Order o where o.typePresta = :typePresta");
        queryToFindOrderByTypePresta.setParameter("typePresta", typePresta);
        return queryToFindOrderByTypePresta.getResultList();
    }
}