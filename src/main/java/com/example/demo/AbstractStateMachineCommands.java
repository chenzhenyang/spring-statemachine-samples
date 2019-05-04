/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.demo;


import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.util.StringUtils;

@ShellComponent
public class AbstractStateMachineCommands<S, E>  {

	@Autowired
	private StateMachine<S, E> stateMachine;

	protected StateMachine<S, E> getStateMachine() {
		return stateMachine;
	}

//	@Autowired
//	@Qualifier("stateChartModel")
//	private String stateChartModel;

	@ShellMethod(key="print state",value = "Prints current state")
	public String state() {
		State<S, E> state = stateMachine.getState();
		if (state != null) {
			return StringUtils.collectionToCommaDelimitedString(state.getIds());
		} else {
			return "No state";
		}
	}

	@ShellMethod(key="start sm",value = "Start a state machine")
	public String start() {
		stateMachine.start();
		return "State machine started";
	}

	@ShellMethod(key="stop sm",value = "Stop a state machine")
	public String stop() {
		stateMachine.stop();
		return "State machine stopped";
	}

//	@ShellMethod(value = "Print state machine")
//	public String print() {
//		return stateChartModel;
//	}

	@ShellMethod(key="print esv",value = "Prints extended state variables")
	public String variables() {
		StringBuilder buf = new StringBuilder();
		Set<Entry<Object, Object>> entrySet = stateMachine.getExtendedState().getVariables().entrySet();
		Iterator<Entry<Object, Object>> iterator = entrySet.iterator();
		if (entrySet.size() > 0) {
			while (iterator.hasNext()) {
				Entry<Object, Object> e = iterator.next();
				buf.append(e.getKey() + "=" + e.getValue());
				if (iterator.hasNext()) {
					buf.append("\n");
				}
			}
		} else {
			buf.append("No variables");
		}
		return buf.toString();
	}

}
