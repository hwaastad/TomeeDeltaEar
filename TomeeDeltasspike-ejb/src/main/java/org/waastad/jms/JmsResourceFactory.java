/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.jms;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.waastad.qualifier.JmsSession;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@ApplicationScoped
public class JmsResourceFactory {
    
    private static final Logger LOG = LoggerFactory.getLogger(JmsResourceFactory.class);

    @Resource(mappedName = "MyJmsConnectionFactory")
    private ConnectionFactory connectionFactory;


    @Produces
    @ApplicationScoped
    @Resource(mappedName = "EventQueue")
    private Queue eventQueue;

    @Produces
    @ApplicationScoped
    @JmsSession
    public Connection createJmsConnection() throws JMSException {
        LOG.debug("Producing JMS Connection...");
        return connectionFactory.createConnection();
    }

    public void closeJmsConnection(@Disposes @JmsSession Connection connection) throws JMSException {
        LOG.debug("Disposing JMS Connection...");
        connection.close();
    }

    @Produces
    @ApplicationScoped
    @JmsSession
    public Session createJMSSession(@JmsSession Connection connection) throws JMSException {
        LOG.debug("Producing JMS Session...");
        return connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    public void closeJMSSession(@Disposes @JmsSession Session session) throws JMSException {
        LOG.debug("Disposing JMS Session...");
        session.close();
    }


    @Produces
    @ApplicationScoped
    public MessageProducer createEventLogMessageProducer(@JmsSession Session session) throws JMSException {
        LOG.debug("Producing JMS Eventlog MessageProducer...");
        return session.createProducer(eventQueue);
    }

    public void closeEventLogMessageProducer(@Disposes MessageProducer producer) throws JMSException {
        LOG.debug("Disposing JMS Eventlog MessageProducer...");
        producer.close();
    }
}
