/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.jms;

import java.io.Serializable;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.waastad.qualifier.JmsSession;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
public class JmsService implements Serializable {

    private static final long serialVersionUID = -8751042612059402727L;
    private static final Logger LOG = LoggerFactory.getLogger(JmsService.class);
    @Inject
    @JmsSession
    private Session session;

    @Inject
    private MessageProducer eventProducer;

    public void sendEvent(EventMessage logMessage) {
        try {
            ObjectMessage message = session.createObjectMessage();
            message.setObject(logMessage);
            eventProducer.send(message);
        } catch (JMSException e) {
            LOG.error("Error sending JMS: {}", e.getMessage());
        }
    }
}
