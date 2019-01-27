package com.starwars.characters.views;

import com.starwars.characters.MainUI;
import com.starwars.characters.dao.Characters;
import com.starwars.characters.dao.Results;
import com.starwars.characters.utils.Util;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ClassResource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.starwars.characters.utils.Util.mFavorites;

@SuppressWarnings("serial")
public class MainView extends VerticalLayout implements View {

    private List<Results> result = new ArrayList<>();
    private Grid<Results> grid = new Grid<>();
    private Set<Results> selected_result;
    private Button button = new Button("Favorites",click -> {
        getUI().getNavigator().navigateTo(MainUI.FAVORITESVIEW);
    });

    // Constructor
    public MainView() {
        setSizeFull();
        Label label = headingLabel();
        label.setWidth("300");
        button.setVisible(false);
        button.setIcon(new ClassResource("/heart.png"));
        HorizontalLayout hLayout = new HorizontalLayout();
        hLayout.addComponents(label,button);
        addComponent(hLayout);
        addComponent(addToFavoriteLabel());
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        callRestService();
    }

    // Title
    private Label headingLabel() {
        return new Label(String.format("<font size = \"5\" color=\"black\"> Star War Characters" )
                , ContentMode.HTML);
    }

    // Add to favorite
    private Label addToFavoriteLabel() {
        return new Label(String.format("Select to add to favorites" )
                , ContentMode.HTML);
    }

    /**
     * Call the Star Wars Rest API
     */
    private void callRestService() {
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        String url = "https://swapi.co/api/people";
        ResponseEntity<Characters> res = rt.exchange(url, HttpMethod.GET, entity, Characters.class);
        Characters character = res.getBody();
        result.clear();
        result.addAll(character.getResults());

        grid.setItems(result);
        grid.addColumn(Results::getName).setCaption("Name");
        grid.addColumn(Results::getGender).setCaption("Gender");
        grid.addColumn(Results::getBirth_year).setCaption("Birth Year");
        grid.addColumn(Results::getHeight).setCaption("Height");

        addComponent(grid);

        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.setWidth("80%");

        grid.addSelectionListener(event -> {
            selected_result = event.getAllSelectedItems();
            mFavorites = event.getAllSelectedItems();
            if(selected_result.size()>0) {
                button.setVisible(true);
            } else
                button.setVisible(false);
        });

        grid.addItemClickListener(event -> goToDetails(event.getItem()));
    }

    /**
     * When the table element is clicked, show more info
     * @param results object of the clicked results element
     */
    private void goToDetails(Results results){
        Util.mResult = results;
        getUI().getNavigator().navigateTo(MainUI.DETAILSVIEW);
    }

}
