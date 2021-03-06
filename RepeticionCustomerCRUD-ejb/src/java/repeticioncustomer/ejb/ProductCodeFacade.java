/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repeticioncustomer.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import repeticioncustomer.entity.ProductCode;

/**
 *
 * @author Adrián
 */
@Stateless
public class ProductCodeFacade extends AbstractFacade<ProductCode> {

    @PersistenceContext(unitName = "RepeticionCustomerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductCodeFacade() {
        super(ProductCode.class);
    }
    
}
