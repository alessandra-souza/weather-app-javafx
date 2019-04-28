package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.*;

import static sample.Weather.currWeather;
import javax.json.*;
import javax.json.stream.JsonParser;
import java.io.*;
import java.lang.Exception;

public class Controller implements Initializable {

    City city = new City();
    Weather currentWeather;

    @FXML
    public ImageView icon;
    @FXML
    public Label cityTempDesc;
    @FXML
    public Label cityTemp;
    @FXML
    public Label cityMinTemp;
    @FXML
    public Label cityMaxTemp;
    @FXML
    public Label lblSkiiable;
    @FXML
    public Label lblSailable;
    @FXML
    public Label lblBikable;
    @FXML
    public Label lblFlyable;
    @FXML
    private ComboBox<String> citiesCombo; // Value injected by FXMLLoader
    @FXML
    private ComboBox<String> countriesCombo;
    @FXML
    private ComboBox<String> provincesCombo;
    @FXML
    public Label selectedCity;

    /**
     * Displays weather information for each city
     * @param selCity
     */
    public void display(String selCity) throws NullPointerException {
        try {
            cityTemp.setText(String.valueOf((int)currWeather(selCity).getTemp()) + "°");
            cityTempDesc.setText(String.valueOf(currWeather(selCity).getDescription().replace("[", "").replace("]","")));
            cityMinTemp.setText("Min: " + String.valueOf((int)currWeather(selCity).getTempMin()) + "°   ");
            cityMaxTemp.setText("Max: " + String.valueOf((int)currWeather(selCity).getTempMax()) + "°");
            currentWeather = Weather.currWeather(String.valueOf(selectedCity));
            lblSkiiable.setText(city.checkSkiiable(currWeather(selCity)));
            lblSailable.setText(city.checkSailable(currWeather(selCity)));
            lblBikable.setText(city.checkBikable(currWeather(selCity)));
            lblFlyable.setText(city.checkFlyiable(currWeather(selCity)));
            icon.setImage(new Image("http://www.openweathermap.org/img/w/" + currWeather(selCity).getIcon().replace("[", "").replace("]","") + ".png"));
        } catch(NullPointerException e) {
            System.out.print("NullPointerException Caught");
        }

    }


    @Override // This method is called by the FXMLLoader when initialization is complete
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        // populate combo box
//        citiesCombo.getItems().setAll("Dubai", "Tokyo", "Montreal", "Toronto", "Vancouver", "Halifax", "Ottawa");

        Country usa = new Country("USA");

        countriesCombo.getItems().setAll("USA");


        try {

            FileInputStream is = new FileInputStream("src/sample/cities_usa.json");
            JsonReader reader = Json.createReader(is);

            JsonArray objectArray = reader.readArray();

            List<String> cities = new ArrayList<String>();
            List<String> provinces = new ArrayList<String>();

            for(int i = 0; i < objectArray.size(); i++) {
                cities.add(objectArray.getJsonObject(i).getString("city"));
                provinces.add(objectArray.getJsonObject(i).getString("state"));

                Set<String> set = new HashSet<>(provinces);
                provinces.clear();
                provinces.addAll(set);
                Set<String> set2 = new HashSet<>(cities);
                cities.clear();
                cities.addAll(set2);
                Collections.sort(provinces);
                Collections.sort(cities);
            }

            citiesCombo.getItems().setAll(cities);
            provincesCombo.getItems().setAll(provinces);

        } catch (Exception e) {
            System.out.println("Exception: "+e.getMessage());
        }



        // bind the selected city label to the selected city in the combo box.
        selectedCity.textProperty().bind(citiesCombo.getSelectionModel().selectedItemProperty());

        // listen for changes to the city combo box selection and update the displayed city label accordingly
        citiesCombo.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> selected, String oldCity, String newCity) {
                if (newCity != null) {
                    display(newCity);
                }
            }

        });

    }

}
