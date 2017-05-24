package com.factracing.ui;


import java.util.Iterator;
import java.util.Set;

import com.factracing.beans.Deck;
import com.factracing.beans.GameRoom;
import com.factracing.components.DeckChooser;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


@SpringView(name = CreateGameRoomView.VIEW_NAME)
public class CreateGameRoomView extends VerticalLayout implements View
{

	public static final String VIEW_NAME = "createGameRoom";


	public CreateGameRoomView()
	{
		setSpacing(true);
		setMargin(true);
		setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
	}


	/**
	 * Initializes layout on each enter.
	 */
	private void initializeLayout()
	{
		removeAllComponents();
		Label factRacingLabel = new Label("<h1>Fact Racing<h1>", ContentMode.HTML);
		Label createRoomLabel = new Label("<h2>Create Game Room<h2>", ContentMode.HTML);
		TextField minPlayersField = new TextField("Minimum Players:");
		minPlayersField.setValue("2");
		TextField maxPlayersField = new TextField("Maximum Players:");
		maxPlayersField.setValue("2");

		DeckChooser usedDecks = new DeckChooser("In Use");
		HorizontalLayout deckChooserLayout = createDeckChooserLayout(usedDecks);

		Button createRoomButton = new Button("Create Game Room");
		createRoomButton.setId("createGameRoomButton");
		createRoomButton.addClickListener(e -> {
			GameRoom room = new GameRoom(((FactRacingUI) UI.getCurrent()).getUserSession());
			room.setMinPlayers(Integer.valueOf(minPlayersField.getValue()));
			room.setMaxPlayers(Integer.valueOf(maxPlayersField.getValue()));
			room.setDecks(usedDecks.getDecks());
			((FactRacingUI) UI.getCurrent()).getUserSession().setCurrentGameRoom(room);

			UI.getCurrent().getNavigator().navigateTo(GameRoomView.VIEW_NAME);
		});

		Button backButton = new Button("Back");
		backButton.setId("backButton");
		backButton.addClickListener(e -> {
			UI.getCurrent().getNavigator().navigateTo(MainNavigationView.VIEW_NAME);
		});

		addComponents(factRacingLabel, createRoomLabel, minPlayersField, maxPlayersField, deckChooserLayout, createRoomButton, backButton);
	}


	/**
	 * Creates the deck chooser lists.
	 *
	 * @return
	 */
	private HorizontalLayout createDeckChooserLayout(DeckChooser usedDecks)
	{
		HorizontalLayout layout = new HorizontalLayout();
		layout.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);

		DeckChooser availableDecks = new DeckChooser("Available");
		availableDecks.addDecks(Deck.DefaultDeck.getAllDefaultDecks());

		VerticalLayout buttonLayout = new VerticalLayout();
		Button addButton = new Button(VaadinIcons.ARROW_RIGHT);
		addButton.setId("addDeckButton");
		addButton.setWidth("100px");
		addButton.addClickListener(e -> {
			swapSelectedDecks(availableDecks, usedDecks);
		});

		Button removeButton = new Button(VaadinIcons.ARROW_LEFT);
		removeButton.setId("removeDeckButton");
		removeButton.setWidth("100px");
		removeButton.addClickListener(e -> {
			swapSelectedDecks(usedDecks, availableDecks);
		});

		buttonLayout.addComponents(addButton, removeButton);

		layout.addComponents(availableDecks, buttonLayout, usedDecks);
		return layout;
	}


	private void swapSelectedDecks(DeckChooser chooserToRemoveFrom, DeckChooser chooserToAddTo)
	{
		Set<String> selectedDecks = chooserToRemoveFrom.getSelectedItems();
		Iterator<String> it = selectedDecks.iterator();
		Deck[] decks = new Deck[chooserToRemoveFrom.getDeckCount()];
		for (int i = 0; it.hasNext(); i++)
		{
			String item = it.next();
			Deck deck = chooserToRemoveFrom.getDeckByName(item);
			decks[i] = deck;
		}
		chooserToRemoveFrom.removeDecks(decks);
		chooserToAddTo.addDecks(decks);
	}


	@Override
	public void enter(ViewChangeEvent event)
	{
		UI.getCurrent().getPage().setTitle("Create Game Room - Fact Racing");
		initializeLayout();
	}

}
