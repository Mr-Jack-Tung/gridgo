package io.gridgo.core.support.template.impl;

import java.util.List;

import org.joo.promise4j.Promise;
import org.joo.promise4j.impl.SimpleFailurePromise;

import io.gridgo.connector.Connector;
import io.gridgo.core.support.template.ProducerTemplate;
import io.gridgo.framework.support.Message;

public abstract class AbstractProducerTemplate implements ProducerTemplate {
	
	public void send(List<Connector> connectors, Message message) {
		for(var connector : connectors) {
			send(connector, message);
		}
	}

	protected Promise<Message, Exception> call(Connector connector, Message message) {
		if (connector.getProducer().isEmpty()) {
			return new SimpleFailurePromise<>(
					new UnsupportedOperationException("No producer found for this connector " + connector.getName()));
		}
		return connector.getProducer().get().call(message);
	}

	protected void send(Connector connector, Message message) {
		if (connector.getProducer().isEmpty())
			return;
		connector.getProducer().get().send(message);
	}

	protected Promise<Message, Exception> sendWithAck(Connector connector, Message message) {
		if (connector.getProducer().isEmpty()) {
			return new SimpleFailurePromise<>(
					new UnsupportedOperationException("No producer found for this connector " + connector.getName()));
		}
		return connector.getProducer().get().sendWithAck(message);
	}
}