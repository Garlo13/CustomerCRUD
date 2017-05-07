/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repeticioncustomer.ejb;

import java.util.Arrays;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import repeticioncustomer.entity.Customer;

/**
 *
 * @author Adrián
 */
@Stateless
public class CustomerFacade extends AbstractFacade<Customer> {

    @PersistenceContext(unitName = "RepeticionCustomerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomerFacade() {
        super(Customer.class);
    }

    /*public Customer findById(int customerId) {
        Query q;
        q = this.em.createNamedQuery("Customer.findByCustomerId");
        q.setParameter("customerId", customerId);
        return (Customer) q.getSingleResult();
    }*/
    public int nextId() {
        Query q;
        q = this.em.createQuery("select max(c.customerId) from Customer c");
        List<Integer> lista = q.getResultList();
        if (lista == null || lista.isEmpty()) {
            return 0;
        } else {
            int id = lista.get(0);
            return id + 1;
        }
    }

    public List<Customer> findBySearch(String buscador) {
        Query q;
        q = this.em.createQuery("select c from Customer c where c.name LIKE :buscador");
        q.setParameter("buscador", "%" + buscador + "%");
        return (List<Customer>) q.getResultList();
    }

    public List<Customer> findBySearchAndZipCode(String buscador, String[] zipsCode) {
        List<String> listaZipsCode = Arrays.asList(zipsCode);
        Query q;
        q = this.em.createQuery("select c from Customer c where c.name LIKE :buscador and c.zip.zipCode IN :listaZipsCode");
        q.setParameter("buscador", "%" + buscador + "%");
        q.setParameter("listaZipsCode", listaZipsCode);
        return (List<Customer>) q.getResultList();
    }
}
