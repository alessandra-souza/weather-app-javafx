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
import java.util.ResourceBundle;
import static sample.Weather.currWeather;

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
        citiesCombo.getItems().setAll("Montreal", "Toronto", "Vancouver", "Halifax", "Ottawa");

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
