package com.factracing.ui;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.factracing.beans.UserSession;
import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.EnableVaadin;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;


@Theme("valo")
@SpringUI
public class FactRacingUI extends UI
{

	private Navigator navigator;
	
	@Autowired
	private static UserSession user;


	@Configuration
	@EnableVaadin
	public static class MyConfiguration
	{
	}


	@Override
	protected void init(VaadinRequest vaadinRequest)
	{
		getPage().setTitle("Fact Racing");
		
		navigator = new Navigator(this, this);
		user = new UserSession();
		
		navigator.addView(WelcomeView.VIEW_NAME, new WelcomeView());
		navigator.addView(MainNavigationView.VIEW_NAME, new MainNavigationView());
		
		navigator.navigateTo("");
		setNavigator(navigator);
	}
	
	public static UserSession getUserSession() {
		return user;
	}
}