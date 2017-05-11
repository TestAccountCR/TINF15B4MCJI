package com.factracing.ui;


import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.UserError;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


@SpringView(name = WelcomeView.VIEW_NAME)
public class WelcomeView extends VerticalLayout implements View
{

	public static final String VIEW_NAME = "";


	public WelcomeView()
	{
		setMargin(true);
		setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

		Label factRacingLabel = new Label("<h1>Fact Racing<h1>", ContentMode.HTML);
		Label welcomeLabel = new Label("<h2><center>Welcome!<center><br>What's your name?<h2>", ContentMode.HTML);
		final TextField nameField = new TextField();

		Button button = new Button("Use");
		button.addClickListener(e -> {
			String userName = nameField.getValue();
			if (userName == null || userName.length() <= 0)
			{
				nameField.setComponentError(new UserError("Invalid name!"));
				return;
			}
			FactRacingUI.getUserSession().setUserName(userName);
			UI.getCurrent().getNavigator().navigateTo("mainNav");
		});

		addComponents(factRacingLabel, welcomeLabel, nameField, button);
	}


	@Override
	public void enter(ViewChangeEvent event)
	{

	}

}