/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.waastad.jms;

import java.io.Serializable;
import java.util.Date;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import org.waastad.entity.DeltaCustomer;
import org.waastad.entity.EventLog;
import org.waastad.repository.CustomerRepository;
import org.waastad.repository.EventLogRepository;

/**
 *
 * @author Helge Waastad <helge.waastad@datametrix.no>
 */
@MessageDriven(mappedName = "EventQueue", activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class MessageListener implements javax.jms.MessageListener {

    @Inject
    private EventLogRepository eventLogRepository;
    @Inject
    private CustomerRepository customerRepository;

    @Override
    public void onMessage(Message message) {
        try {

            ObjectMessage msg = (ObjectMessage) message;
            Serializable messageObject = msg.getObject();
            EventMessage eventLogMessage = (EventMessage) msg.getObject();
            EventLog log = new EventLog(new Date(), eventLogMessage.getMessage());
            if (null != eventLogMessage.getCustomer()) {
                Long id = ((DeltaCustomer) eventLogMessage.getCustomer()).getId();
                DeltaCustomer c = customerRepository.findBy(((DeltaCustomer) eventLogMessage.getCustomer()).getId());
                c.gettEventLogsCollection().add(log);
                customerRepository.save(c);
            } else {
                eventLogRepository.save(log);
            }
            eventLogRepository.save(log);
        } catch (JMSException e) {

        }
    }

}
