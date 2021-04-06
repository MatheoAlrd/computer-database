package com.excilys.cdb.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.excilys.cdb.config.SpringConfig;
import com.excilys.cdb.ui.CLIMenu;

public class Main {

	public static void main(String[] args) {
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
		
		CLIMenu cliMenu = context.getBean(CLIMenu.class);
		cliMenu.useMenu();
		
		context.close();
	}
}
