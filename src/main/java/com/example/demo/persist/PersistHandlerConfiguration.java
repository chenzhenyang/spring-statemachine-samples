package com.example.demo.persist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.recipes.persist.PersistStateMachineHandler;

@Configuration
public class PersistHandlerConfiguration {

	@Autowired
	private StateMachine<String, String> stateMachine;

	@Bean
	public OrderRepository persist() {
		return new OrderRepository(persistStateMachineHandler());
	}

	@Bean
	public PersistStateMachineHandler persistStateMachineHandler() {
		return new PersistStateMachineHandler(stateMachine);
	}

}