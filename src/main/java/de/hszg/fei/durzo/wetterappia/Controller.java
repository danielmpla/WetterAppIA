package de.hszg.fei.durzo.wetterappia;

import de.hszg.fei.durzo.wetterappia.bussiness.exception.SearchException;
import de.hszg.fei.durzo.wetterappia.bussiness.util.WeatherHelper;
import de.hszg.fei.durzo.wetterappia.repo.Location;
import de.hszg.fei.durzo.wetterappia.repo.Weather;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    public TextField searchField;
    public Button searchButton;
    public ListView<Location> listView;

    public Label locationLabel;

    public Label tempMinSixLabel;
    public Label tempMaxSixLabel;
    public Label windSixLabel;
    public Label downfallSixLabel;
    public Label stateSixLabel;

    public Label tempMinElevenLabel;
    public Label tempMaxElevenLabel;
    public Label windElevenLabel;
    public Label downfallElevenLabel;
    public Label stateElevenLabel;

    public Label tempMinSeventeenLabel;
    public Label tempMaxSeventeenLabel;
    public Label windSeventeenLabel;
    public Label downfallSeventeenLabel;
    public Label stateSeventeenLabel;

    public Label tempMinTwentyThreeLabel;
    public Label tempMaxTwentyThreeLabel;
    public Label windTwentyThreeLabel;
    public Label downfallTwentyThreeLabel;
    public Label stateTwentyThreeLabel;

    private WeatherHelper weatherHelper;

    public void openWetterCom(Event event) {
        try {
            Desktop.getDesktop().browse(new URI("http://wetter.com"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void initialize() {
        this.weatherHelper = new WeatherHelper();

        resetLabels();

        Location goerlitz = new Location();
        Location loebau = new Location();
        Location dresden = new Location();
        Location leipzig = new Location();

        goerlitz.setLocation("Görlitz [DE]");
        goerlitz.setLocationCode("DE0003184");
        loebau.setLocation("Löbau [DE]");
        loebau.setLocationCode("DE0005912");
        dresden.setLocation("Dresden [DE]");
        dresden.setLocationCode("DE0002265");
        leipzig.setLocation("Leipzig [DE]");
        leipzig.setLocationCode("DE0006194056");

        List<Location> locations = new ArrayList<>();
        locations.add(goerlitz);
        locations.add(loebau);
        locations.add(dresden);
        locations.add(leipzig);

        listView.setItems(FXCollections.observableArrayList(locations));

        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Location>() {
            @Override
            public void changed(ObservableValue<? extends Location> observable, Location oldValue, Location newValue) {
                if (newValue != null) {
                    try {
                        setWeather(weatherHelper.getWeather(newValue.getLocationCode()));
                        locationLabel.setText(newValue.getLocation());
                    } catch (SearchException e) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setContentText("Ein Fehler ist aufgetreten, bitte versuchen Sie es später erneut!");
                        alert.showAndWait();
                    }
                }
            }
        });
    }

    public void searchButtonClicked(ActionEvent actionEvent) {
        listView.setItems(FXCollections.emptyObservableList());

        List<Location> locations = new ArrayList<>();
        try {
            locations = this.weatherHelper.getLocation(this.searchField.getText());
        } catch (SearchException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Ein Fehler ist aufgetreten, bitte versuchen Sie es später erneut!");
            alert.showAndWait();
        }

        listView.setItems(FXCollections.observableList(locations));
    }

    private void resetLabels() {
        this.locationLabel.setText("");

        this.tempMinSixLabel.setText("");
        this.tempMaxSixLabel.setText("");
        this.windSixLabel.setText("");
        this.downfallSixLabel.setText("");
        this.stateSixLabel.setText("");

        this.tempMinElevenLabel.setText("");
        this.tempMaxElevenLabel.setText("");
        this.windElevenLabel.setText("");
        this.downfallElevenLabel.setText("");
        this.stateElevenLabel.setText("");

        this.tempMinSeventeenLabel.setText("");
        this.tempMaxSeventeenLabel.setText("");
        this.windSeventeenLabel.setText("");
        this.downfallSeventeenLabel.setText("");
        this.stateSeventeenLabel.setText("");

        this.tempMinTwentyThreeLabel.setText("");
        this.tempMaxTwentyThreeLabel.setText("");
        this.windTwentyThreeLabel.setText("");
        this.downfallTwentyThreeLabel.setText("");
        this.stateTwentyThreeLabel.setText("");
    }

    private void setWeather(Weather weather) {
        tempMinSixLabel.setText(weather.getTempMinSix());
        tempMaxSixLabel.setText(weather.getTempMaxSix());
        windSixLabel.setText(weather.getWindSix());
        downfallSixLabel.setText(weather.getDownfallSix());
        stateSixLabel.setText(weather.getStateSix());

        tempMinElevenLabel.setText(weather.getTempMinEleven());
        tempMaxElevenLabel.setText(weather.getTempMaxEleven());
        windElevenLabel.setText(weather.getWindEleven());
        downfallElevenLabel.setText(weather.getDownfallEleven());
        stateElevenLabel.setText(weather.getStateEleven());

        tempMinSeventeenLabel.setText(weather.getTempMinSeventeen());
        tempMaxSeventeenLabel.setText(weather.getTempMaxSeventeen());
        windSeventeenLabel.setText(weather.getWindSeventeen());
        downfallSeventeenLabel.setText(weather.getDownfallSeventeen());
        stateSeventeenLabel.setText(weather.getStateSeventeen());

        tempMinTwentyThreeLabel.setText(weather.getTempMinTwentyThree());
        tempMaxTwentyThreeLabel.setText(weather.getTempMaxTwentyThree());
        windTwentyThreeLabel.setText(weather.getWindTwentyThree());
        downfallTwentyThreeLabel.setText(weather.getDownfallTwentyThree());
        stateTwentyThreeLabel.setText(weather.getStateTwentyThree());
    }
}
