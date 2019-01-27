package com.starwars.characters.navigation;

import com.starwars.characters.dao.Results;
import com.starwars.characters.utils.Util;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ClassResource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@SuppressWarnings("serial")
public class Favorites extends VerticalLayout implements View {

    // Data object holding our favorites
    private Grid<Results> favorite_grid = new Grid<>();

    // Constructor
    public Favorites() {
        setSizeFull();
        setSpacing(true);
        addComponent(headingLabel());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Set<Results> favorites = Util.mFavorites;
        if(favorites != null) {
            callRestService(favorites);
        }
    }

    // Title
    private Label headingLabel() {
        return new Label(String.format("<font size = \"5\" color=\"black\"> Favorites" )
                , ContentMode.HTML);
    }

    /**
     * Render favorites to in our list
     */
    private void callRestService(Set<Results> favorites) {
        List<Results> favorites_list = new ArrayList<>(favorites);

        favorite_grid.setItems(favorites_list);
        favorite_grid.addColumn(Results::getName).setCaption("Name");
        favorite_grid.addColumn(Results::getGender).setCaption("Gender");
        favorite_grid.addColumn(Results::getBirth_year).setCaption("Birth Year");
        favorite_grid.addColumn(Results::getHeight).setCaption("Height");

        favorite_grid.setWidth("80%");

        // render a button that deletes a favorite item
        favorite_grid.addComponentColumn(favItem -> {
            Button button = new Button("Delete");
            button.setIcon(new ClassResource("/heart.png"));
            button.addClickListener(click -> {
                favorites_list.remove(favItem);
                favorite_grid.setItems(favorites_list);
            });
            return button;
        });

        // make sure the buttons fit in the cells of the Grid
        favorite_grid.setBodyRowHeight(40);

        // add grid to component
        addComponent(favorite_grid);
    }
}
