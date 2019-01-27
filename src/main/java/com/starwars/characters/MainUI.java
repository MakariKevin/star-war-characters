package com.starwars.characters;

import com.starwars.characters.navigation.Favorites;
import com.starwars.characters.views.Details;
import com.starwars.characters.views.MainView;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.PushStateNavigation;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;

@SpringUI
@PushStateNavigation
public class MainUI extends UI {

    // Routing paths
    private static final String MAINVIEW = "main";
    public static final String FAVORITESVIEW = "favorites";
    public static final String DETAILSVIEW = "details";

    @Override
    protected void init(VaadinRequest request) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(true);
        setContent(layout);

        // Routing navigation
        Navigator.ComponentContainerViewDisplay viewDisplay = new Navigator.ComponentContainerViewDisplay(layout);
        Navigator navigator = new Navigator(UI.getCurrent(), viewDisplay);
        navigator.addView(MAINVIEW, new MainView());
        navigator.addView(FAVORITESVIEW, new Favorites());
        navigator.addView(DETAILSVIEW, new Details());
        navigator.navigateTo(MAINVIEW);
    }
}
