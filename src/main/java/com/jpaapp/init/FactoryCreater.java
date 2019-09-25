package com.jpaapp.init;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author YBolshakova
 */
public class FactoryCreater {
    
    private final EntityManagerFactory entittyManagerFactoty = Persistence.createEntityManagerFactory("JpaAppPostgresql");

    public FactoryCreater() {
    }

    public EntityManagerFactory getEntittyManagerFactoty() {
        return entittyManagerFactoty;
    }
    
    

}
