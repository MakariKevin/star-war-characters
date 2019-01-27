package com.starwars.characters.views;

import com.starwars.characters.dao.Results;
import com.starwars.characters.utils.Util;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;

@SuppressWarnings("serial")
public class Details extends VerticalLayout implements View {

    public Details() {
        setSizeFull();
        setSpacing(true);
        addComponent(headingLabel());

        hl_name.addComponents(l_name, l_name_value);
        addComponent(hl_name);

        hl_height.addComponents(l_height, l_height_value);
        addComponent(hl_height);

        hl_mass.addComponents(l_mass, l_mass_value);
        addComponent(hl_mass);

        hl_hair_color.addComponents(l_hair_color, l_hair_color_value);
        addComponent(hl_hair_color);

        hl_skin_color.addComponents(l_skin_color, l_skin_color_value);
        addComponent(hl_skin_color);

        hl_eye_color.addComponents(l_eye_color, l_eye_color_value);
        addComponent(hl_eye_color);

        hl_birth_year.addComponents(l_birth_year, l_birth_year_value);
        addComponent(hl_birth_year);

        hl_gender.addComponents(l_gender, l_gender_value);
        addComponent(hl_gender);

        hl_homeworld.addComponents(l_homeworld, l_homeworld_value);
        addComponent(hl_homeworld);

        hl_created.addComponents(l_created, l_created_value);
        addComponent(hl_created);

        hl_edited.addComponents(l_edited, l_edited_value);
        addComponent(hl_edited);

        hl_url.addComponents(l_url, l_url_value);
        addComponent(hl_url);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Results results = Util.mResult;

        l_name.setWidth("100");
        l_height.setWidth("100");
        l_mass.setWidth("100");
        l_hair_color.setWidth("100");
        l_skin_color.setWidth("100");
        l_eye_color.setWidth("100");
        l_birth_year.setWidth("100");
        l_gender.setWidth("100");
        l_homeworld.setWidth("100");
        l_created.setWidth("100");
        l_edited.setWidth("100");
        l_url.setWidth("100");

        if(results == null) return;

        l_name_value.setValue(results.getName());
        l_height_value.setValue(results.getHeight());
        l_mass_value.setValue(results.getMass());
        l_hair_color_value.setValue(results.getHair_color());
        l_skin_color_value.setValue(results.getSkin_color());
        l_eye_color_value.setValue(results.getEye_color());
        l_birth_year_value.setValue(results.getBirth_year());
        l_gender_value.setValue(results.getGender());
        l_homeworld_value.setValue(results.getHomeworld());
        l_created_value.setValue(results.getCreated());
        l_edited_value.setValue(results.getEdited());
        l_url_value.setValue(results.getUrl());
    }

    // Title
    private Label headingLabel() {
        return new Label(String.format("<font size = \"5\" color=\"black\"> Details" )
                , ContentMode.HTML);
    }

    // Name
    private HorizontalLayout hl_name = new HorizontalLayout();
    private Label l_name =  new Label("Name:");
    private Label l_name_value =  new Label(" - ");

    // Height
    private HorizontalLayout hl_height = new HorizontalLayout();
    private Label l_height =  new Label("Height:");
    private Label l_height_value =  new Label(" - ");

    // Mass
    private HorizontalLayout hl_mass = new HorizontalLayout();
    private Label l_mass =  new Label("Mass:");
    private Label l_mass_value =  new Label(" - ");

    // Hair color
    private HorizontalLayout hl_hair_color = new HorizontalLayout();
    private Label l_hair_color =  new Label("Hair color:");
    private Label l_hair_color_value =  new Label(" - ");

    // Skin color
    private HorizontalLayout hl_skin_color = new HorizontalLayout();
    private Label l_skin_color =  new Label("Skin color:");
    private Label l_skin_color_value =  new Label(" - ");

    // Eye color
    private HorizontalLayout hl_eye_color = new HorizontalLayout();
    private Label l_eye_color =  new Label("Eye color:");
    private Label l_eye_color_value =  new Label(" - ");

    // Birth year
    private HorizontalLayout hl_birth_year = new HorizontalLayout();
    private Label l_birth_year =  new Label("Birth year:");
    private Label l_birth_year_value =  new Label(" - ");

    // Gender
    private HorizontalLayout hl_gender = new HorizontalLayout();
    private Label l_gender =  new Label("Gender:");
    private Label l_gender_value =  new Label(" - ");

    // Homeworld
    private HorizontalLayout hl_homeworld = new HorizontalLayout();
    private Label l_homeworld =  new Label("Homeworld:");
    private Label l_homeworld_value =  new Label(" - ");

    // Created
    private HorizontalLayout hl_created = new HorizontalLayout();
    private Label l_created =  new Label("Created:");
    private Label l_created_value =  new Label(" - ");

    // Name
    private HorizontalLayout hl_edited = new HorizontalLayout();
    private Label l_edited =  new Label("Edited:");
    private Label l_edited_value =  new Label(" - ");

    // Url
    private HorizontalLayout hl_url = new HorizontalLayout();
    private Label l_url =  new Label("Url:");
    private Label l_url_value =  new Label(" - ");

}
