package WEEKK_14_ASSIGNMENTS;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class WeatherApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        WeatherGUI gui = new WeatherGUI();
        Scene scene = new Scene(gui.createContent(), 800, 600);
        primaryStage.setTitle("Weather Information App");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class WeatherGUI {
    private TextField locationInput;
    private Button searchButton;
    private Label temperatureLabel, humidityLabel, windSpeedLabel, conditionLabel;
    private ImageView weatherIcon;
    private ListView<String> forecastList;
    private ToggleButton unitToggle;
    private ListView<String> historyList;
    private BackgroundManager backgroundManager;
    private LocationHistory locationHistory;
    private boolean isCelsius = true;

    public BorderPane createContent() {
        BorderPane root = new BorderPane();

        // Top: Search bar
        HBox searchBox = new HBox(10);
        locationInput = new TextField();
        searchButton = new Button("Search");
        searchBox.getChildren().addAll(locationInput, searchButton);
        root.setTop(searchBox);

        // Center: Weather information
        VBox weatherInfo = new VBox(10);
        temperatureLabel = new Label();
        humidityLabel = new Label();
        windSpeedLabel = new Label();
        conditionLabel = new Label();
        weatherIcon = new ImageView();
        weatherInfo.getChildren().addAll(weatherIcon, temperatureLabel, humidityLabel, windSpeedLabel, conditionLabel);
        root.setCenter(weatherInfo);

        // Right: Forecast
        forecastList = new ListView<>();
        root.setRight(forecastList);

        // Bottom: Unit toggle and history
        HBox bottomBox = new HBox(10);
        unitToggle = new ToggleButton("°C / °F");
        historyList = new ListView<>();
        bottomBox.getChildren().addAll(unitToggle, historyList);
        root.setBottom(bottomBox);

        // Set padding
        root.setPadding(new Insets(10));

        // Initialize background manager
        backgroundManager = new BackgroundManager(root);

        // Initialize location history
        locationHistory = new LocationHistory();

        // Add event handlers
        addEventHandlers();

        return root;
    }

    private void addEventHandlers() {
        searchButton.setOnAction(e -> searchWeather());
        unitToggle.setOnAction(e -> toggleUnits());
    }

    private void searchWeather() {
        String location = locationInput.getText();
        WeatherAPI weatherAPI = new WeatherAPI();
        try {
            WeatherData currentWeather = weatherAPI.getWeatherData(location);
            List<WeatherData> forecast = weatherAPI.getWeatherForecast(location);
            updateWeatherInfo(currentWeather);
            updateForecast(forecast);
            addLocationToHistory(location);
            backgroundManager.updateBackground();
        } catch (Exception e) {
            showError("Error retrieving weather data. Please check the location and try again.");
        }
    }

    private void updateWeatherInfo(WeatherData data) {
        double temp = data.getTemperature();
        if (!isCelsius) {
            temp = (temp * 9/5) + 32;
        }
        String tempUnit = isCelsius ? "°C" : "°F";
        temperatureLabel.setText("Temperature: " + temp + tempUnit);
        humidityLabel.setText("Humidity: " + data.getHumidity() + "%");
        windSpeedLabel.setText("Wind Speed: " + data.getWindSpeed() + " m/s");
        conditionLabel.setText("Condition: " + data.getCondition());

        String iconUrl = "https://openweathermap.org/img/wn/" + data.getIconCode() + "@2x.png";
        weatherIcon.setImage(new Image(iconUrl));
    }

    private void updateForecast(List<WeatherData> forecastData) {
        forecastList.getItems().clear();
        for (WeatherData data : forecastData) {
            forecastList.getItems().add(data.getCondition() + " - " + data.getTemperature() + "°C");
        }
    }

    private void toggleUnits() {
        isCelsius = !isCelsius;
        searchWeather(); // Refresh the display with new units
    }

    private void addLocationToHistory(String location) {
        locationHistory.addEntry(location);
        updateHistoryList();
    }

    private void updateHistoryList() {
        historyList.getItems().clear();
        for (LocationHistory.HistoryEntry entry : locationHistory.getEntries()) {
            historyList.getItems().add(entry.getLocation() + " - " + entry.getTimestamp().toString());
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(message);
        alert.showAndWait();
    }
}

class WeatherAPI {
    private static final String API_KEY = "your_api_key_here";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    public WeatherData getWeatherData(String location) throws Exception {
        String url = API_URL + "?q=" + location + "&appid=" + API_KEY + "&units=metric";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonResponse = new JSONObject(response.body());
        double temperature = jsonResponse.getJSONObject("main").getDouble("temp");
        int humidity = jsonResponse.getJSONObject("main").getInt("humidity");
        double windSpeed = jsonResponse.getJSONObject("wind").getDouble("speed");
        String condition = jsonResponse.getJSONArray("weather").getJSONObject(0).getString("description");
        String iconCode = jsonResponse.getJSONArray("weather").getJSONObject(0).getString("icon");

        return new WeatherData(temperature, humidity, windSpeed, condition, iconCode);
    }

    public List<WeatherData> getWeatherForecast(String location) throws Exception {
        String url = "https://api.openweathermap.org/data/2.5/forecast?q=" + location + "&appid=" + API_KEY + "&units=metric";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsonResponse = new JSONObject(response.body());
        JSONArray forecastList = jsonResponse.getJSONArray("list");
        List<WeatherData> forecastData = new ArrayList<>();

        for (int i = 0; i < forecastList.length(); i += 8) { // e.g., get one forecast per day
            JSONObject forecast = forecastList.getJSONObject(i);
            double temperature = forecast.getJSONObject("main").getDouble("temp");
            int humidity = forecast.getJSONObject("main").getInt("humidity");
            double windSpeed = forecast.getJSONObject("wind").getDouble("speed");
            String condition = forecast.getJSONArray("weather").getJSONObject(0).getString("description");
            String iconCode = forecast.getJSONArray("weather").getJSONObject(0).getString("icon");

            forecastData.add(new WeatherData(temperature, humidity, windSpeed, condition, iconCode));
        }

        return forecastData;
    }
}

class WeatherData {
    private double temperature;
    private int humidity;
    private double windSpeed;
    private String condition;
    private String iconCode;

    public WeatherData(double temperature, int humidity, double windSpeed, String condition, String iconCode) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.condition = condition;
        this.iconCode = iconCode;
    }

    // Getters
    public double getTemperature() { return temperature; }
    public int getHumidity() { return humidity; }
    public double getWindSpeed() { return windSpeed; }
    public String getCondition() { return condition; }
    public String getIconCode() { return iconCode; }
}

class LocationHistory {
    private List<HistoryEntry> entries;

    public LocationHistory() {
        entries = new ArrayList<>();
    }

    public void addEntry(String location) {
        entries.add(new HistoryEntry(location, LocalDateTime.now()));
    }

    public List<HistoryEntry> getEntries() {
        return entries;
    }

    public static class HistoryEntry {
        private String location;
        private LocalDateTime timestamp;

        public HistoryEntry(String location, LocalDateTime timestamp) {
            this.location = location;
            this.timestamp = timestamp;
        }

        public String getLocation() { return location; }
        public LocalDateTime getTimestamp() { return timestamp; }
    }
}

class BackgroundManager {
    private Pane root;

    public BackgroundManager(Pane root) {
        this.root = root;
    }

    public void updateBackground() {
        int hour = java.time.LocalTime.now().getHour();
        String imagePath;

        if (hour >= 6 && hour < 12) {
            imagePath = "morning.jpg";
        } else if (hour >= 12 && hour < 18) {
            imagePath = "afternoon.jpg";
        } else if (hour >= 18 && hour < 21) {
            imagePath = "evening.jpg";
        } else {
            imagePath = "night.jpg";
        }

        Image image = new Image(getClass().getResourceAsStream(imagePath));
        BackgroundImage backgroundImage = new BackgroundImage(image, null, null, null, null);
        root.setBackground(new Background(backgroundImage));
    }
}