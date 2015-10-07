package it.cnr.ilc.lc.omega.test;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;

/**
 *
 * @author oakgen
 */
public class Neo4jSessionFactory {

    private static final SessionFactory SESSION_FACTORY = new SessionFactory("it.cnr.ilc.lc.omega");

    static {
        System.setProperty("username", "neo4j");
        System.setProperty("password", "omega");
    }

    public static Session getNeo4jSession() {
        return SESSION_FACTORY.openSession("http://wafi.iit.cnr.it:8074"); //http://wafi.iit.cnr.it:8074 http://localhost:7474
    }

}
