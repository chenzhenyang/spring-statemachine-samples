/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.demo.persist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@ShellCommandGroup("Persist")
public class PersistCommands {

	@Autowired
	private OrderRepository persist;

	@ShellMethod(key = "select", value = "List entries from db")
	public String listDbEntries() {
		return persist.listDbEntries();
	}

	@ShellMethod(key = "process", value = "Process order")
	public void process(@ShellOption(value = { "", "id" }, help = "Order id") int order) {
		persist.change(order, "PROCESS");
	}

	@ShellMethod(key = "send", value = "Send order")
	public void send(@ShellOption(value = { "", "id" }, help = "Order id") int order) {
		persist.change(order, "SEND");
	}

	@ShellMethod(key = "deliver", value = "Deliver order")
	public void deliver(@ShellOption(value = { "", "id" }, help = "Order id") int order) {
		persist.change(order, "DELIVER");
	}

}
