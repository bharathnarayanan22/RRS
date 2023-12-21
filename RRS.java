package RRS;
import java.sql.Statement;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
/*import javafx.scene.control.TextInputDialog;
import java.util.Optional;*/
import java.util.List;
import java.util.ArrayList;
//import javafx.stage.Modality;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
//import com.sun.speech.freetts.Voice;
//import com.sun.speech.freetts.VoiceManager;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RRS extends Application {

    private Stage primaryStage;
    private TextArea outputTextArea;
    
    private static final String DB_URL = "jdbc:mysql://localhost:3306/RRS";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Bharath@22";

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showIntroPage();
    }

   private void showIntroPage() {
    primaryStage.setTitle("Recipe Recommendation System");

    Image introImage = new Image("rrs/image_1.jpg");
    ImageView introImageView = new ImageView(introImage);

    Label welcomeLabel = new Label("Welcome to");
    welcomeLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #FF6347;"); // Tomato red color

    Label systemLabel = new Label("Recipe Recommendation System");
    systemLabel.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: #4682B4;"); // Steel blue color

    Label journeyLabel = new Label("Let's embark on a delicious journey together");
    journeyLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #696969;"); // Dim gray color

    Label explorationLabel = new Label("where your cravings are met and culinary exploration is endless. ❤️");
    explorationLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #696969;"); // Dim gray color

    Button findRecipeButton = new Button("Find Your Recipe");
    findRecipeButton.setStyle("-fx-font-size: 18px; -fx-background-color: #4682B4;; -fx-text-fill: #FFFFFF;"); // Medium sea green button

    VBox introLayout = new VBox(20, welcomeLabel, systemLabel, journeyLabel, explorationLabel, findRecipeButton);
    introLayout.setAlignment(Pos.CENTER);
    introLayout.setStyle("-fx-background-color: transparent; -fx-padding: 40px;"); 
    
    addFadeTransition(welcomeLabel);
    addTranslateTransition(systemLabel);
    addScaleTransition(journeyLabel);
    addTranslateTransition(explorationLabel);
    addFadeTransition(findRecipeButton);

    StackPane stackPane = new StackPane(introImageView, introLayout);

    findRecipeButton.setOnAction(event -> showWelcomePage());

    Scene introScene = new Scene(stackPane, 800, 600);

    primaryStage.setScene(introScene);
    primaryStage.show();
}
   
   private void addFadeTransition(javafx.scene.Node node) {
    FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), node);
    fadeTransition.setFromValue(0);
    fadeTransition.setToValue(1);
    fadeTransition.play();
}

private void addTranslateTransition(javafx.scene.Node node) {
    TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), node);
    translateTransition.setFromY(-50);
    translateTransition.setToY(0);
    translateTransition.play();
}

private void addScaleTransition(javafx.scene.Node node) {
    ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(1), node);
    scaleTransition.setFromX(0);
    scaleTransition.setToX(1);
    scaleTransition.setFromY(0);
    scaleTransition.setToY(1);
    scaleTransition.play();
}

private void showWelcomePage() {
    primaryStage.setTitle("Welcome Page");

    Image welcomeImage = new Image("rrs/image_1.jpg");
    ImageView welcomeImageView = new ImageView(welcomeImage);

    Label welcomeLabel = new Label("Ready to spice up your culinary journey?");
    welcomeLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #4682B4;"); // Steel blue color

    Label introLabel = new Label("Join us and discover a world of delightful recipes waiting for you to explore. 🍽️");
    introLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: normal; -fx-text-fill: #696969;"); // Dim gray color

    Button loginButton = new Button("Login");
    loginButton.setStyle("-fx-font-size: 18px; -fx-background-color: #4682B4; -fx-text-fill: #FFFFFF; -fx-padding: 10px 20px; -fx-border-radius: 5px;");
    loginButton.setOnAction(event -> showLoginPage());

    Button signUpButton = new Button("Sign Up");
    signUpButton.setStyle("-fx-font-size: 18px; -fx-background-color: #4682B4; -fx-text-fill: #FFFFFF; -fx-padding: 10px 20px; -fx-border-radius: 5px;");
    signUpButton.setOnAction(event -> showSignUpPage());

    VBox welcomeLayout = new VBox(20, welcomeLabel, introLabel, loginButton, signUpButton);
    welcomeLayout.setAlignment(Pos.CENTER);
    welcomeLayout.setStyle("-fx-background-color: transparent; -fx-padding: 40px;"); 
     
    addFadeTransition(welcomeLabel);
    addTranslateTransition(introLabel);
    addScaleTransition(loginButton);
    addTranslateTransition(signUpButton);

    StackPane stackPane = new StackPane(welcomeImageView, welcomeLayout);

    Scene scene = new Scene(stackPane, 800, 600);

    primaryStage.setScene(scene);
    primaryStage.show();
}

private void showLoginPage() {
    primaryStage.setTitle("Login Page");

    Image loginImage = new Image("rrs//image_1.jpg"); 
    ImageView loginImageView = new ImageView(loginImage);

    TextField usernameField = new TextField();
    usernameField.setPromptText("Enter your email_id");
    PasswordField passwordField = new PasswordField();
    passwordField.setPromptText("Enter your password");

    Button loginButton = new Button("Login");
    loginButton.setStyle("-fx-font-size: 18; -fx-background-color: #4682B4; -fx-text-fill: #FFFFFF; -fx-padding: 10 20; -fx-border-radius: 5px;");
    loginButton.setOnAction(event -> performLogin(usernameField.getText(), passwordField.getText()));

    Hyperlink forgotPasswordLink = new Hyperlink("Forgot Password?");
    forgotPasswordLink.setStyle("-fx-font-size: 14;");

    Hyperlink goToSignUpLink = new Hyperlink("Don't have an account? Sign Up");
    goToSignUpLink.setStyle("-fx-font-size: 14;");
    goToSignUpLink.setOnAction(event -> showSignUpPage());

    VBox loginLayout = new VBox(10, new Label("Email_ID:"), usernameField, new Label("Password:"), passwordField, loginButton, forgotPasswordLink, goToSignUpLink);
    loginLayout.setPadding(new Insets(20));
    loginLayout.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7); -fx-padding: 20; -fx-background-radius: 10;");

    Hyperlink goToWelcomeLink = new Hyperlink("Back to Welcome Page");
    goToWelcomeLink.setStyle("-fx-font-size: 14;");
    goToWelcomeLink.setOnAction(event -> showWelcomePage());

    VBox fullLoginPage = new VBox(20, loginLayout, goToWelcomeLink);
    fullLoginPage.setAlignment(Pos.CENTER);
    fullLoginPage.getStyleClass().add("main-background");

    StackPane stackPane = new StackPane(loginImageView, fullLoginPage);

    FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), fullLoginPage);
    fadeTransition.setFromValue(0);
    fadeTransition.setToValue(1);
    fadeTransition.play();


    Scene loginScene = new Scene(stackPane, 800, 600);

    primaryStage.setScene(loginScene);
    primaryStage.show();
}

private void showSignUpPage() {
    primaryStage.setTitle("Sign Up Page");

    Image signUpImage = new Image("rrs//image_1.jpg");
    ImageView signUpImageView = new ImageView(signUpImage);

TextField nameField = new TextField();
nameField.setPromptText("Enter your name");

TextField emailField = new TextField();
emailField.setPromptText("Enter your email");

TextField ageField = new TextField();
ageField.setPromptText("Enter your age");

TextField genderField = new TextField();
genderField.setPromptText("Enter your gender");

PasswordField passwordField = new PasswordField();
passwordField.setPromptText("Enter your password");

TextField favDishField = new TextField();
favDishField.setPromptText("Enter your favorite dish");

    Button signUpButton = new Button("Sign Up");
    signUpButton.setStyle("-fx-font-size: 18; -fx-background-color: #4682B4; -fx-text-fill: #FFFFFF; -fx-padding: 10 20; -fx-border-radius: 5px;");
    signUpButton.setOnAction(event -> performSignUp(
            nameField.getText(), emailField.getText(), ageField.getText(),
            genderField.getText(), passwordField.getText(), favDishField.getText()));

    Hyperlink goToLoginLink = new Hyperlink("Already have an account? Login");
    goToLoginLink.setStyle("-fx-font-size: 14;");
    goToLoginLink.setOnAction(event -> showLoginPage());

    VBox signUpLayout = new VBox(10,
            new Label("Name:"), nameField,
            new Label("Email:"), emailField,
            new Label("Age:"), ageField,
            new Label("Gender:"), genderField,
            new Label("Password:"), passwordField,
            new Label("Favorite Dish:"), favDishField,
            signUpButton, goToLoginLink);
    signUpLayout.setPadding(new Insets(100));
    signUpLayout.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7); -fx-padding: 110; -fx-background-radius: 1000;");

    StackPane stackPane = new StackPane(signUpImageView, signUpLayout);
    
    FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), signUpLayout);
    fadeTransition.setFromValue(0);
    fadeTransition.setToValue(1);
    fadeTransition.play();

    Scene signUpScene = new Scene(stackPane, 800, 600);

    primaryStage.setScene(signUpScene);
    primaryStage.show();
}

private void performLogin(String email, String password) {
    try {
        validateLoginFields(email, password);
        if (isUserAuthenticated(email, password)) {
            System.out.println("Login successful!");
            showHomePage(email);
        } else {
            System.out.println("Invalid email or password.");
            showInvalidLoginAlert();
        }
    } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
    }
}

private void showInvalidLoginAlert() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Invalid Login");
    alert.setHeaderText(null);
    alert.setContentText("Invalid email or password. Please try again.");
    alert.showAndWait();
}

    private void validateLoginFields(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("Email and password are required.");
        }
    }
    
private void showHomePage(String email) {
    primaryStage.setTitle("Welcome, Food Explorer!");

    Label welcomeLabel = new Label("Welcome back, " + getUsernameFromEmail(email) + "!");
    welcomeLabel.setStyle("-fx-font-size: 24; -fx-font-weight: bold; -fx-text-fill: Red");

    Button goToRecommendationButton = new Button("Discover Personalized Recommendations");
    styleButton(goToRecommendationButton);
    goToRecommendationButton.setOnAction(event -> showPreferencesPage(email));

    Button showRecipesButton = new Button("Explore Top Recipes by Rating");
    styleButton(showRecipesButton);
    showRecipesButton.setOnAction(event -> showRecipesByRating(email));
    
    Button addFavoriteDishButton = new Button("Add Favorite Dish");
    styleButton(addFavoriteDishButton);
    addFavoriteDishButton.setOnAction(event -> showAddFavoriteDishDialog(email));
    
    Button addRecipeButton = new Button("Add Your Recipe");
    styleButton(addRecipeButton);
    addRecipeButton.setOnAction(event -> showAddRecipeDialog(email));

    Button settingsButton = new Button("Settings");
    styleButton(settingsButton);
    settingsButton.setOnAction(event -> showSettings(email));

        GridPane gridPane = new GridPane();
    gridPane.setVgap(20); 
    gridPane.setHgap(30); 
    gridPane.setAlignment(Pos.CENTER);
    gridPane.addRow(0, goToRecommendationButton, showRecipesButton);
    gridPane.addRow(1, addFavoriteDishButton, addRecipeButton);
    gridPane.addRow(2, settingsButton);

    VBox homeLayout = new VBox(20, welcomeLabel, gridPane);
    homeLayout.setAlignment(Pos.CENTER);
    homeLayout.setStyle("-fx-background-color: transparent; -fx-padding: 40;");

    StackPane stackPane = new StackPane(homeLayout);

    Image backgroundImage = new Image("rrs/image_1.jpg");
    BackgroundImage background = new BackgroundImage(backgroundImage,
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
    stackPane.setBackground(new Background(background));

    Scene homeScene = new Scene(stackPane, 800, 600);

    TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), welcomeLabel);
    translateTransition.setFromX(-200);
    translateTransition.setToX(0);
    translateTransition.play();

    addFadeTransition(welcomeLabel);
    addTranslateTransition(goToRecommendationButton);
    addScaleTransition(showRecipesButton);
    addTranslateTransition(addFavoriteDishButton);
    addFadeTransition(addRecipeButton);
    addTranslateTransition(settingsButton);

    primaryStage.setScene(homeScene);
    primaryStage.show();

}

private void styleButton(Button button) {
    button.setStyle("-fx-font-size: 18; -fx-background-color: #4682B4; -fx-text-fill: #FFFFFF; -fx-padding: 10 20; -fx-border-radius: 5px;");
    button.setMinWidth(100); 
    button.setMaxWidth(Double.MAX_VALUE); 
    button.setOnAction(event -> {

    });
}

 private void showAddRecipeDialog(String email) {
        Stage addRecipeStage = new Stage();
        addRecipeStage.setTitle("Add Your Recipe");
        

        TextField dishNameField = new TextField();
        TextArea ingredientsArea = new TextArea();
        TextField durationField = new TextField();
        TextField budgetField = new TextField();
        TextArea proceduresArea = new TextArea();
        TextField cuisineField = new TextField();

        Button submitRecipeButton = new Button("Submit Recipe");
        submitRecipeButton.setStyle("-fx-font-size: 14; -fx-padding: 8 16; -fx-background-color: #4682B4; -fx-text-fill: white;");
        submitRecipeButton.setOnAction(event -> {
            saveRecipeToDatabase(email, dishNameField.getText(), ingredientsArea.getText(),
                    durationField.getText(), budgetField.getText(), proceduresArea.getText(), cuisineField.getText());
            addRecipeStage.close();
        });

        VBox addRecipeLayout = new VBox(10,
                createLabelWithPrompt("Dish Name", dishNameField),
                createLabelWithPrompt("Ingredients", ingredientsArea),
                createLabelWithPrompt("Duration", durationField),
                createLabelWithPrompt("Budget", budgetField),
                createLabelWithPrompt("Procedures", proceduresArea),
                createLabelWithPrompt("Cuisine", cuisineField),
                submitRecipeButton);
        addRecipeLayout.setAlignment(Pos.CENTER);
        addRecipeLayout.setPadding(new Insets(20));
        addRecipeLayout.setStyle("-fx-background-color: #f0f0f0; -fx-background-radius: 10;");

        Scene addRecipeScene = new Scene(addRecipeLayout, 600, 800);
        addRecipeStage.setScene(addRecipeScene);
        addRecipeStage.show();
    }

private VBox createLabelWithPrompt(String labelText, Control control) {
    Label label = new Label(labelText);
    label.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");
    
    VBox labeledControl = new VBox(5, label, control);
    labeledControl.setAlignment(Pos.CENTER);

    return labeledControl;
}


private void saveRecipeToDatabase(String email, String dishName, String ingredients,String duration, String budget, String procedures, String cuisine) {
    String url = "jdbc:mysql://localhost:3306/RRS";
    String user = "root";
    String pass = "Bharath@22";

    try (Connection conn = DriverManager.getConnection(url, user, pass)) {
        int userId = getUserIdByEmail(conn, email);

String insertQuery = "INSERT INTO Recipe (Dish, Ingredients, Duration, Budget, Procedures, Cuisine) VALUES (?, ?, ?, ?, ?, ?)";
try (PreparedStatement pstmt = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
    pstmt.setString(1, dishName);
    pstmt.setString(2, ingredients);
    pstmt.setString(3, duration);
    pstmt.setString(4, budget);
    pstmt.setString(5, procedures);
    pstmt.setString(6, cuisine);
    pstmt.executeUpdate();

    ResultSet generatedKeys = pstmt.getGeneratedKeys();
    if (generatedKeys.next()) {
        int recipeId = generatedKeys.getInt(1);
    }

        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private void showSettings(String email) {
    Button feedbackButton = new Button("Provide Feedback");
    styleButton(feedbackButton);
    feedbackButton.setOnAction(event -> showFeedbackDialog(email));

    Button historyButton = new Button("View History");
    styleButton(historyButton);
    historyButton.setOnAction(event -> showUserHistory(email));

    Button logoutButton = new Button("Logout");
    styleButton(logoutButton);
    logoutButton.setOnAction(event -> showWelcomePage());
    
    Button BackButton = new Button("Back to Home Page");
    styleButton(BackButton);
    BackButton.setOnAction(event -> showHomePage(email));

    VBox settingsLayout = new VBox(20, feedbackButton, historyButton, logoutButton,BackButton);
    settingsLayout.setAlignment(Pos.CENTER);
    settingsLayout.setStyle("-fx-background-color: transparent; -fx-padding: 40;");

    StackPane stackPane = new StackPane(settingsLayout);

    Image backgroundImage = new Image("rrs/image_1.jpg"); 
    BackgroundImage background = new BackgroundImage(backgroundImage,
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
            BackgroundSize.DEFAULT);
    stackPane.setBackground(new Background(background));
    
    addFadeTransition(feedbackButton);
    addTranslateTransition(historyButton);
    addScaleTransition(logoutButton);
    addFadeTransition(BackButton);

    Scene homeScene = new Scene(stackPane, 800, 600);
    
    primaryStage.setScene(homeScene);
    primaryStage.show();
}

private void showAddFavoriteDishDialog(String email) {
    Stage addDishStage = new Stage();
    addDishStage.setTitle("Add Favorite Dish");

    Label addDishLabel = new Label("Enter your favorite dish:");
    TextField dishTextField = new TextField();
    Button submitDishButton = new Button("Submit");

    submitDishButton.setOnAction(event -> {
        String favoriteDish = dishTextField.getText();
        saveFavoriteDishToDatabase(email, favoriteDish);
        addDishStage.close();
    });

    VBox addDishLayout = new VBox(10, addDishLabel, dishTextField, submitDishButton);
    addDishLayout.setAlignment(Pos.CENTER);
    addDishLayout.setPadding(new Insets(20));

    Image backgroundImage = new Image("rrs//image_1.jpg"); 
    BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
    BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
    addDishLayout.setBackground(new Background(background));

    addDishLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");
    dishTextField.setStyle("-fx-font-size: 14;");
    submitDishButton.setStyle(
            "-fx-font-size: 14; " +
            "-fx-background-color: #4682B4; " +
            "-fx-text-fill: white; " +
            "-fx-padding: 8px 16px;"
    );

    Scene addDishScene = new Scene(addDishLayout, 300, 200);
    addDishStage.setScene(addDishScene);

    addDishStage.show();
}

private void saveFavoriteDishToDatabase(String email, String favoriteDish) {
    String url = "jdbc:mysql://localhost:3306/RRS";
    String user = "root";
    String pass = "Bharath@22";

    try (Connection conn = DriverManager.getConnection(url, user, pass)) {
        int userId = getUserIdByEmail(conn, email);

        String selectQuery = "SELECT fav_dish FROM Users WHERE id = ?";
        try (PreparedStatement selectStmt = conn.prepareStatement(selectQuery)) {
            selectStmt.setInt(1, userId);

            try (ResultSet resultSet = selectStmt.executeQuery()) {
                if (resultSet.next()) {
                    updateFavoriteDish(conn, userId, favoriteDish);
                } else {
                    insertFavoriteDish(conn, userId, favoriteDish);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private void updateFavoriteDish(Connection conn, int userId, String favoriteDish) throws SQLException {

    String updateQuery = "UPDATE Users SET fav_dish = ? WHERE id = ?";
    try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
        updateStmt.setString(1, favoriteDish);
        updateStmt.setInt(2, userId);

        int rowsAffected = updateStmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Favorite dish updated successfully");
        } else {
            System.out.println("Failed to update favorite dish");
        }
    }
}

private void insertFavoriteDish(Connection conn, int userId, String favoriteDish) throws SQLException {

    String insertQuery = "INSERT INTO Users (id, fav_dish) VALUES (?, ?)";
    try (PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
        insertStmt.setInt(1, userId);
        insertStmt.setString(2, favoriteDish);

        int rowsAffected = insertStmt.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Favorite dish inserted successfully");
        } else {
            System.out.println("Failed to insert favorite dish");
        }
    }
}

public String getUsernameFromEmail(String email) {
        String username = null;

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String query = "SELECT name FROM users WHERE email = ?";
            
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, email);

                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        username = resultSet.getString("name");
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return username;
    }

    private void showUserHistory(String email) {
        List<String> userHistory = getUserHistory(email);

        TextArea historyTextArea = new TextArea();
        historyTextArea.setEditable(false);
        for (String historyEntry : userHistory) {
            historyTextArea.appendText(historyEntry + "\n");
        }

        VBox historyLayout = new VBox(20, createHistoryLabel(), historyTextArea);
        historyLayout.setAlignment(Pos.CENTER);
        historyLayout.setPadding(new Insets(20));
        historyLayout.setStyle("-fx-background-color: #f0f0f0; -fx-background-radius: 10;");

        Scene historyScene = new Scene(historyLayout, 400, 300);

        Stage historyStage = new Stage();
        historyStage.setTitle("User History");
        historyStage.setScene(historyScene);
        historyStage.show();
    }

    private Label createHistoryLabel() {
        Label historyLabel = new Label("User History");

        historyLabel.setStyle("-fx-font-size: 18; -fx-font-weight: bold; -fx-text-fill: #333;");

        return historyLabel;
    }

private int getUserId(String email) {
    int userId = -1; 
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/RRS", "root", "Bharath@22")) {
        String query = "SELECT id FROM users WHERE email = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    userId = resultSet.getInt("id");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return userId;
}

private List<String> getUserHistory(String email) {
    int userId = getUserId(email);
    List<String> userHistory = new ArrayList<>();

    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
        String query = "SELECT activity, login_time, logout_time FROM userhistory WHERE user_id = ? ORDER BY login_time DESC";
        
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, userId);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    String activity = resultSet.getString("activity");
                    Timestamp loginTime = resultSet.getTimestamp("login_time");
                    Timestamp logoutTime = resultSet.getTimestamp("logout_time");
                    String entry = activity + " at " + loginTime.toString();
                    
                    if (logoutTime != null) {
                        entry += " - Logout at " + logoutTime.toString();
                    } else {
                        updateUserLogoutTime(userId);
                    }
                    
                    userHistory.add(entry);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return userHistory;
}

private void updateUserLogoutTime(int userId) {
    try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
        String updateQuery = "UPDATE userhistory SET logout_time = CURRENT_TIMESTAMP WHERE user_id = ? AND logout_time IS NULL";
        try (PreparedStatement updateStatement = conn.prepareStatement(updateQuery)) {
            updateStatement.setInt(1, userId);
            updateStatement.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


private boolean isUserAuthenticated(String email, String password) {
    boolean isAuthenticated = false;

    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/RRS", "root", "Bharath@22")) {
        String query = "SELECT id FROM users WHERE email = ? AND password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    isAuthenticated = true;

                    int userId = resultSet.getInt("id");
                    insertLoginHistory(userId, "Login");
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return isAuthenticated;
}

private void insertLoginHistory(int userId, String activity) {
    try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/RRS", "root", "Bharath@22")) {
        String insertQuery = "INSERT INTO userhistory (user_id, activity) VALUES (?, ?)";
        try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
            insertStatement.setInt(1, userId);
            insertStatement.setString(2, activity);
            insertStatement.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

     private void showFeedbackDialog(String email) {
        Stage feedbackStage = new Stage();
        feedbackStage.setTitle("Feedback");

        Label feedbackLabel = new Label("Provide your feedback:");
        TextArea feedbackTextArea = new TextArea();
        feedbackTextArea.setWrapText(true);
        Button submitFeedbackButton = new Button("Submit Feedback");

        submitFeedbackButton.setOnAction(event -> {
            String feedbackText = feedbackTextArea.getText();
            saveFeedbackToDatabase(email, feedbackText);
            feedbackStage.close();
        });

        VBox feedbackLayout = new VBox(10, feedbackLabel, feedbackTextArea, submitFeedbackButton);
        feedbackLayout.setAlignment(Pos.CENTER);
        feedbackLayout.setPadding(new Insets(20));

        Image backgroundImage = new Image("rrs//image_1.jpg"); 
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, backgroundSize);
        feedbackLayout.setBackground(new Background(background));

        Scene feedbackScene = new Scene(feedbackLayout, 400, 300);

        feedbackStage.setTitle("Feedback");
        feedbackStage.setScene(feedbackScene);

        feedbackLabel.setStyle("-fx-font-size: 16; -fx-font-weight: bold;");
        submitFeedbackButton.setStyle(
                "-fx-font-size: 14; " +
                "-fx-background-color: #4682B4; " +
                "-fx-text-fill: white; " +
                "-fx-padding: 8px 16px;"
        );

        feedbackLayout.setStyle("-fx-background-radius: 10; -fx-border-color: #ccc; -fx-border-width: 1px;");

        feedbackStage.show();
    }
          
private void saveFeedbackToDatabase(String email, String feedbackText) {
    String url = "jdbc:mysql://localhost:3306/RRS";
    String user = "root";
    String pass = "Bharath@22";

    try (Connection conn = DriverManager.getConnection(url, user, pass)) {

        int userId = getUserIdByEmail(conn, email);

        String insertQuery = "INSERT INTO Feedback (user_id, Feedback) VALUES (?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
            pstmt.setInt(1, userId);
            pstmt.setString(2, feedbackText);
            pstmt.executeUpdate();
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private int getUserIdByEmail(Connection conn, String email) throws SQLException {

    String selectQuery = "SELECT id FROM Users WHERE email = ?";
    try (PreparedStatement pstmt = conn.prepareStatement(selectQuery)) {
        pstmt.setString(1, email);
        try (ResultSet resultSet = pstmt.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        }
    }
    return -1; 
}
    
    private void showRecipesByRating(String email) {
    primaryStage.setTitle("Recipes by Rating");

    List<Recipe> recipes = getRecipesByRating();

    VBox recipesLayout = new VBox(10);
    recipesLayout.setAlignment(Pos.CENTER);
    recipesLayout.setPadding(new Insets(20));
    recipesLayout.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7); -fx-padding: 20; -fx-background-radius: 10;");

    for (Recipe recipe : recipes) {
        
        VBox recipeBox = createRecipeBox(recipe);
        recipesLayout.getChildren().add(recipeBox);
    }

    Button goToHomeButton = new Button("Back to Home Page");
    goToHomeButton.setOnAction(event -> showHomePage(email));
    recipesLayout.getChildren().add(goToHomeButton);

    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(recipesLayout);
    scrollPane.setFitToWidth(true);
    scrollPane.setFitToHeight(true);

    Scene recipesScene = new Scene(scrollPane, 800, 600);
    primaryStage.setScene(recipesScene);
}

private List<Recipe> getRecipesByRating() {
    
    List<Recipe> recipes = new ArrayList<>();

    String url = "jdbc:mysql://localhost:3306/RRS";
    String user = "root";
    String pass = "Bharath@22";

    try (Connection conn = DriverManager.getConnection(url, user, pass)) {
        String query = "SELECT * FROM Recipe r INNER JOIN ratings ra ON r.Recipe_id = ra.Recipe_id ORDER BY ra.Ratings DESC";

        try (PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet resultSet = pstmt.executeQuery()) {

            while (resultSet.next()) {
                Recipe recipe = new Recipe(
                        resultSet.getString("dish"),
                        resultSet.getString("cuisine"),
                        resultSet.getString("ingredients"),
                        resultSet.getString("Duration"),
                        resultSet.getString("budget"),
                        resultSet.getString("procedures"),
                        resultSet.getDouble("ratings")
                );
                recipes.add(recipe);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return recipes;
}

private VBox createRecipeBox(Recipe recipe) {

    VBox recipeBox = new VBox(15);
    recipeBox.setAlignment(Pos.CENTER);
    recipeBox.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-padding: 10;");

    Label dishNameLabel = new Label("Dish Name: " + recipe.getDishName());
    dishNameLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2196F3;");
    Label cuisineLabel = new Label("Cuisine: " + recipe.getCuisine());
    Label durationLabel = new Label("Time: " + recipe.getDuration());
    Label budgetLabel = new Label("Budget: " + recipe.getBudget());
    Label ratingLabel = new Label("Rating: " + recipe.getRating());

    Button detailsButton = new Button("View Details");
    detailsButton.setOnAction(event -> showDishDetails(recipe.getDishName(), recipe.getCuisine(), recipe.getIngredients(), recipe.getProcedure()));

    detailsButton.setStyle("-fx-font-size: 14; -fx-padding: 8 16; -fx-background-color: #2196F3; -fx-text-fill: white;");

    recipeBox.getChildren().addAll(dishNameLabel, cuisineLabel, durationLabel, budgetLabel, ratingLabel, detailsButton);

    return recipeBox;
}

public class Recipe {
    private String dishName;
    private String cuisine;
    private String ingredients;
    private String duration;
    private String budget;
    private String procedure;
    private double rating;

    public Recipe(String dishName, String cuisine, String ingredients, String duration, String budget, String procedure, double rating) {
        this.dishName = dishName;
        this.cuisine = cuisine;
        this.ingredients = ingredients;
        this.duration = duration;
        this.budget = budget;
        this.procedure = procedure;
        this.rating = rating;
    }

    public String getDishName() {
        return dishName;
    }

    public String getCuisine() {
        return cuisine;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getDuration() {
        return duration;
    }

    public String getBudget() {
        return budget;
    }

    public String getProcedure() {
        return procedure;
    }

    public double getRating() {
        return rating;
    }
}

    
   private void showPreferencesPage(String email) {
        primaryStage.setTitle("Preferences Page");
        outputTextArea = new TextArea();
        outputTextArea.setEditable(false);

        try {
            String url = "jdbc:mysql://localhost:3306/RRS";
            String user = "root";
            String pass = "Bharath@22";

            try (Connection conn = DriverManager.getConnection(url, user, pass)) {
                String query = "SELECT fav_dish FROM Users WHERE email = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setString(1, email);

                    try (ResultSet resultSet = pstmt.executeQuery()) {
                        if (resultSet.next()) {
                            String favDish = resultSet.getString("fav_dish");
                            System.out.println("User's Favorite Dish: " + favDish);

                            displayMatchingDishes(email, favDish);
                        } else {
                            System.out.println("User not found.");
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayMatchingDishes(String email, String favDish) {
    try {
        String url = "jdbc:mysql://localhost:3306/RRS";
        String user = "root";
        String pass = "Bharath@22";

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            String query = "SELECT * FROM Recipe WHERE dish LIKE ? OR cuisine LIKE ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, "%" + favDish + "%");
                pstmt.setString(2, "%" + favDish + "%");

                try (ResultSet resultSet = pstmt.executeQuery()) {
                    ScrollPane scrollPane = new ScrollPane();
                    VBox preferencesLayout = new VBox(10);
                    preferencesLayout.setAlignment(Pos.CENTER);
                    preferencesLayout.setPadding(new Insets(20));
                    preferencesLayout.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7); -fx-padding: 20; -fx-background-radius: 10;");
                    scrollPane.setContent(preferencesLayout);
                    scrollPane.setFitToWidth(true);
                    scrollPane.setFitToHeight(true);

                    HBox searchBox = new HBox(10);
                  
                   
                    TextField searchField = new TextField();
                    searchField.setPromptText("Search for dishes");
                    
                    searchField.setOnKeyPressed(event -> {
                        if (event.getCode().equals(KeyCode.ENTER)) {
                            String searchQuery = searchField.getText();
                            displayMatchingDishes(email, favDish, searchQuery);
                        }
                    });

                    Button searchButton = new Button("Search");
                    searchButton.setOnAction(event -> {
                        String searchQuery = searchField.getText();
                        displayMatchingDishes(email, favDish, searchQuery);
                    });

                    searchBox.getChildren().addAll(searchField, searchButton);
                    preferencesLayout.getChildren().add(searchBox);

                    while (resultSet.next()) {
                        String dishName = resultSet.getString("dish");
                        String cuisine = resultSet.getString("cuisine");
                        String ingredients = resultSet.getString("ingredients");
                        String duration = resultSet.getString("Duration");
                        String budget = resultSet.getString("budget");
                        String procedure = resultSet.getString("procedures");
                        double rating = getDishRating(dishName, cuisine);

                        VBox dishBox = createDishBox(dishName, cuisine, ingredients, duration, budget, procedure, rating);
                        preferencesLayout.getChildren().add(dishBox);
                    }

Button backToHomeButton = new Button("Back to Home Page");
backToHomeButton.setStyle("-fx-font-size: 14;");
backToHomeButton.setAlignment(Pos.TOP_RIGHT);
backToHomeButton.setOnAction(event -> showHomePage(email));

VBox headerBox = new VBox(backToHomeButton);
headerBox.setAlignment(Pos.TOP_RIGHT);
preferencesLayout.getChildren().add(headerBox);

                    Scene preferencesScene = new Scene(scrollPane, 800, 600);
                    primaryStage.setScene(preferencesScene);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    private void displayMatchingDishes(String email, String favDish, String searchQuery) {
        try {
            String url = "jdbc:mysql://localhost:3306/RRS";
            String user = "root";
            String pass = "Bharath@22";

            try (Connection conn = DriverManager.getConnection(url, user, pass)) {
                String query = "SELECT * FROM Recipe WHERE (dish LIKE ? OR cuisine LIKE ?) AND dish <> ?";
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setString(1, "%" + searchQuery + "%");
                    pstmt.setString(2, "%" + searchQuery + "%");
                    pstmt.setString(3, favDish);

                    try (ResultSet resultSet = pstmt.executeQuery()) {
                        VBox preferencesLayout = new VBox(10);
                        preferencesLayout.setAlignment(Pos.CENTER);
                        preferencesLayout.setPadding(new Insets(20));
                        preferencesLayout.setStyle("-fx-background-color: rgba(255, 255, 255, 0.7); -fx-padding: 20; -fx-background-radius: 10;");

                                     HBox searchBox = new HBox(10);
                   
                        TextField searchField = new TextField();
                        searchField.setPromptText("Search for dishes");
                        searchField.setOnKeyPressed(event -> {
                            if (event.getCode().equals(KeyCode.ENTER)) {
                                String newSearchQuery = searchField.getText();
                                displayMatchingDishes(email, favDish, newSearchQuery);
                            }
                        });

                        Button searchButton = new Button("Search");
                        searchButton.setOnAction(event -> {
                            String newSearchQuery = searchField.getText();
                            displayMatchingDishes(email, favDish, newSearchQuery);
                        });

                           searchBox.getChildren().addAll(searchField, searchButton);
                    preferencesLayout.getChildren().add(searchBox);

                        while (resultSet.next()) {
                            String dishName = resultSet.getString("dish");
                            String cuisine = resultSet.getString("cuisine");
                            String ingredients = resultSet.getString("ingredients");
                            String duration = resultSet.getString("Duration");
                            String budget = resultSet.getString("budget");
                            String procedure = resultSet.getString("procedures");
                            double rating = getDishRating(dishName,cuisine);

                            VBox dishBox = createDishBox(dishName, cuisine, ingredients, duration, budget, procedure, rating);
                            preferencesLayout.getChildren().add(dishBox);
                        }
                        
                        ScrollPane scrollPane = new ScrollPane();
                        scrollPane.setContent(preferencesLayout);
                        scrollPane.setFitToWidth(true);
                        scrollPane.setFitToHeight(true);
                        Scene preferencesScene = new Scene(scrollPane, 800, 600);
                        primaryStage.setScene(preferencesScene);

                        outputTextArea.setText(""); 
                        outputTextArea.setDisable(true); 
                        
Button backToHomeButton = new Button("Back to Home Page");
backToHomeButton.setStyle("-fx-font-size: 14;");
backToHomeButton.setAlignment(Pos.TOP_RIGHT);
backToHomeButton.setOnAction(event -> showHomePage(email));

VBox headerBox = new VBox(backToHomeButton);
headerBox.setAlignment(Pos.TOP_RIGHT);
preferencesLayout.getChildren().add(headerBox);

                        primaryStage.setScene(preferencesScene);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    

private VBox createDishBox(String dishName, String cuisine, String ingredients, String duration, String budget, String procedure, double rating) {
    VBox dishBox = new VBox(15);
    dishBox.setAlignment(Pos.CENTER);
    dishBox.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-padding: 10;");

    Label dishNameLabel = new Label("Dish Name: " + dishName);
    dishNameLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #2196F3;");
    Label cuisineLabel = new Label("Cuisine: " + cuisine);
    Label durationLabel = new Label("Time: " + duration);
    Label budgetLabel = new Label("Budget: " + budget);
    Label ratingLabel = new Label("Rating: " + rating);

    Button detailsButton = new Button("View Details");
    detailsButton.setOnAction(event -> showDishDetails(dishName, cuisine, ingredients, procedure));

    detailsButton.setStyle("-fx-font-size: 14; -fx-padding: 8 16; -fx-background-color: #2196F3; -fx-text-fill: white;");
    
    dishBox.getChildren().addAll(dishNameLabel, cuisineLabel, durationLabel, budgetLabel, ratingLabel, detailsButton);

    return dishBox;
}

private void showDishDetails(String dishName, String cuisine, String ingredients, String procedure) {
    Stage detailsStage = new Stage();
    detailsStage.setTitle(dishName + " Details");

    Button playIngredientsButton = new Button("Play Ingredients");
    playIngredientsButton.setOnAction(event -> playText(ingredients));


    Button playProcedureButton = new Button("Play Procedure");
    playProcedureButton.setOnAction(event -> playText(procedure));


    VBox detailsLayout = new VBox(20);
    detailsLayout.setAlignment(Pos.CENTER);
    detailsLayout.setPadding(new Insets(20));
    detailsLayout.setStyle("-fx-background-color: #f0f0f0; -fx-background-radius: 10;");

    Label titleLabel = new Label("Details for " + dishName);
    titleLabel.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

    TextArea ingredientsTextArea = new TextArea(ingredients);
    ingredientsTextArea.setEditable(false);
    ingredientsTextArea.setWrapText(true);
    ingredientsTextArea.setMaxHeight(150);
    ingredientsTextArea.setStyle("-fx-background-color: #e0e0e0; -fx-font-size: 14;");

    TextArea procedureTextArea = new TextArea(procedure);
    procedureTextArea.setEditable(false);
    procedureTextArea.setWrapText(true);
    procedureTextArea.setMaxHeight(150);
    procedureTextArea.setStyle("-fx-background-color: #e0e0e0; -fx-font-size: 14;");

    TextField ratingInput = new TextField();
    ratingInput.setPromptText("Enter your rating");
    ratingInput.setMaxWidth(200);
    
    

    Button submitRatingButton = new Button("Submit Rating");
    submitRatingButton.setOnAction(event -> submitRating(dishName, cuisine, ratingInput.getText(), detailsStage));
    submitRatingButton.setStyle("-fx-font-size: 16; -fx-padding: 8 16; -fx-background-color: #4caf50; -fx-text-fill: white;");

    Button closeButton = new Button("Close");
    closeButton.setOnAction(event -> detailsStage.close());
    closeButton.setStyle("-fx-font-size: 16; -fx-padding: 8 16; -fx-background-color: #d9534f; -fx-text-fill: white;");

  detailsLayout.getChildren().addAll(
        titleLabel, 
        createLabel("Ingredients:"), 
        ingredientsTextArea, 
        playIngredientsButton,  
        createLabel("Procedure:"), 
        procedureTextArea, 
        playProcedureButton,
        createLabel("Your Rating:"), 
        ratingInput, 
        submitRatingButton, 
        closeButton
    );

            Scene detailsScene = new Scene(detailsLayout, 400, 500);
            detailsStage.setScene(detailsScene);
            detailsStage.show();
   

}
private void playText(String text) {
    Stage playStage = new Stage();
    playStage.setTitle("Play Text");

    StackPane playLayout = new StackPane();
    playLayout.setStyle("-fx-background-color: #f0f0f0; -fx-padding: 20;");

    Text textNode = createStyledText(text);
    playLayout.getChildren().add(textNode);

    Scene playScene = new Scene(playLayout, 400, 300);
    playStage.setScene(playScene);
    playStage.show();
}

private Text createStyledText(String text) {
    Text styledText = new Text(text);
    styledText.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16));
    styledText.setFill(Color.BLACK);
    styledText.setWrappingWidth(350);

    return styledText;
}



private double getDishRating(String dishName, String cuisine) {
    try {
        String url = "jdbc:mysql://localhost:3306/RRS";
        String user = "root";
        String pass = "Bharath@22";

        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            String query = "SELECT AVG(r.Ratings) AS avg_rating " +
                           "FROM Ratings r " +
                           "WHERE r.Recipe_id IN (SELECT Recipe_id FROM Recipe WHERE dish = ? AND cuisine = ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, dishName);
                pstmt.setString(2, cuisine);

                try (ResultSet resultSet = pstmt.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getDouble("avg_rating");
                    }
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return 0.0;  
}

private void submitRating(String dishName, String cuisineName, String rating, Stage detailsStage) {
    try {
        double ratingValue = Double.parseDouble(rating);
        if (ratingValue >= 1 && ratingValue <= 5) {  
            saveRatingToDatabase(dishName, cuisineName, ratingValue);
            System.out.println("Rating submitted successfully!");
            detailsStage.close();  // Close the details stage after submitting rating
        } else {
            System.out.println("Invalid rating. Please enter a value between 1 and 5.");
        }
    } catch (NumberFormatException e) {
        System.out.println("Invalid rating format. Please enter a numeric value.");
    }
}

private void saveRatingToDatabase(String dishName, String cuisineName, double rating) {
    String url = "jdbc:mysql://localhost:3306/RRS";
    String user = "root";
    String pass = "Bharath@22";

    try (Connection conn = DriverManager.getConnection(url, user, pass)) {
  
        String getCuisineIdQuery = "SELECT Cuisine_id FROM Cuisine WHERE Cuisine_name = ?";
        try (PreparedStatement cuisineIdPstmt = conn.prepareStatement(getCuisineIdQuery)) {
            cuisineIdPstmt.setString(1, cuisineName);

            try (ResultSet cuisineIdResultSet = cuisineIdPstmt.executeQuery()) {
                if (cuisineIdResultSet.next()) {
                    int cuisineId = cuisineIdResultSet.getInt("Cuisine_id");

                    String getRecipeIdQuery = "SELECT Recipe_id FROM Recipe WHERE Dish = ? AND Cuisine_id = ?";
                    try (PreparedStatement recipeIdPstmt = conn.prepareStatement(getRecipeIdQuery)) {
                        recipeIdPstmt.setString(1, dishName);
                        recipeIdPstmt.setInt(2, cuisineId);

                        try (ResultSet recipeIdResultSet = recipeIdPstmt.executeQuery()) {
                            if (recipeIdResultSet.next()) {
                                int recipeId = recipeIdResultSet.getInt("Recipe_id");

                                String saveRatingQuery = "INSERT INTO Ratings (Recipe_id, Cuisine_id, Ratings) VALUES (?, ?, ?)";
                                try (PreparedStatement pstmt = conn.prepareStatement(saveRatingQuery)) {
                                    pstmt.setInt(1, recipeId);
                                    pstmt.setInt(2, cuisineId);
                                    pstmt.setDouble(3, rating);
                                    pstmt.executeUpdate();
                                }
                            }
                        }
                    }
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

private List<Recipe> getRecipesByRatingAndCuisine(String cuisineName) {
    List<Recipe> recipes = new ArrayList<>();

    String url = "jdbc:mysql://localhost:3306/RRS";
    String user = "root";
    String pass = "Bharath@22";

    try (Connection conn = DriverManager.getConnection(url, user, pass)) {
        String query = "SELECT r.*, ra.Ratings FROM Recipe r " +
                       "JOIN Ratings ra ON r.Recipe_id = ra.Recipe_id " +
                       "JOIN Cuisine cu ON r.Cuisine_id = cu.Cuisine_id " +
                       "WHERE cu.Cuisine_name = ? " +
                       "ORDER BY ra.Ratings DESC";

        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, cuisineName);

            try (ResultSet resultSet = pstmt.executeQuery()) {
                while (resultSet.next()) {
                    Recipe recipe = new Recipe(
                            resultSet.getString("Dish"),
                            resultSet.getString("Cuisine_name"),
                            resultSet.getString("Ingredients"),
                            resultSet.getString("Duration"),
                            resultSet.getString("Budget"),
                            resultSet.getString("Procedures"),
                            resultSet.getDouble("Ratings")
                    );
                    recipes.add(recipe);
                }
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return recipes;
}


private Label createLabel(String text) {
    Label label = new Label(text);
    label.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
    return label;
}

    private void performSignUp(String name, String email, String age, String gender, String password, String favDish) {
        try {
            validateSignUpFields(name, email, age, gender, password, favDish);
            saveUserToDatabase(name, email, age, gender, password, favDish);
            System.out.println("User information saved successfully!");
            showLoginPage();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void validateSignUpFields(String name, String email, String age, String gender, String password, String favDish) {
        if (name.isEmpty() || email.isEmpty() || age.isEmpty() || gender.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("All fields are required.");
        }

        try {
            int ageValue = Integer.parseInt(age);
            if (ageValue < 0) {
                throw new IllegalArgumentException("Age must be a non-negative number.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Age must be a valid number.");
        }

        if (!gender.equals("Male") && !gender.equals("Female") && !gender.equals("Other")) {
            throw new IllegalArgumentException("Invalid gender.");
        }

    }

   private void saveUserToDatabase(String name, String email, String age, String gender, String password, String favDish) {
    String url = "jdbc:mysql://localhost:3306/RRS";
    String user = "root";
    String pass = "Bharath@22";
    
    try (Connection conn = DriverManager.getConnection(url, user, pass)) {
 
        String userQuery = "INSERT INTO Users (name, email, age, gender, password, fav_dish) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement userPstmt = conn.prepareStatement(userQuery, Statement.RETURN_GENERATED_KEYS)) {
            userPstmt.setString(1, name);
            userPstmt.setString(2, email);
            userPstmt.setString(3, age);
            userPstmt.setString(4, gender);
            userPstmt.setString(5, password);
            userPstmt.setString(6, favDish);
            
            int userRowsAffected = userPstmt.executeUpdate();
            
            if (userRowsAffected > 0) {
       
                try (ResultSet generatedKeys = userPstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int userId = generatedKeys.getInt(1);
       
                        String favDishQuery = "INSERT INTO Favorites (id, fav_dish) VALUES (?, ?)";
                        try (PreparedStatement favDishPstmt = conn.prepareStatement(favDishQuery)) {
                            favDishPstmt.setInt(1, userId);
                            favDishPstmt.setString(2, favDish);
                            
                            int favDishRowsAffected = favDishPstmt.executeUpdate();
                            
                            if (favDishRowsAffected > 0) {
                                System.out.println("User and favorite dish saved successfully!");
                            } else {
                                System.out.println("Failed to save favorite dish.");
                            }
                        }
                    }
                }
            } else {
                System.out.println("Failed to save user.");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public static void main(String[] args) {
        launch(args);
    }
}