package ies.luisvives.peluqueriadamtpv.utils;

import ies.luisvives.peluqueriadamtpv.App;
import ies.luisvives.peluqueriadamtpv.controller.BaseController;
import ies.luisvives.peluqueriadamtpv.model.UserConfiguration;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

public class Util {
    private static ResourceBundle resourceBundle;
    public static final String PACKAGE_DIR = "/ies/luisvives/peluqueriadamtpv/";

    private Util() {
    }

    private static void setResourceBundleLanguage() {
        String[] language = UserConfiguration.getInstance().getActualLanguage().split("_");
        Locale locale = new Locale(language[0], language[1]);
        resourceBundle = ResourceBundle.getBundle(PACKAGE_DIR + "i18n/strings", locale);
    }

    public static Stage getStageWithIcon(Stage stage){
        stage.getIcons().add(new Image(Objects.requireNonNull(
                Util.class.getResourceAsStream(Util.PACKAGE_DIR + "images/logo.png"))));
        return stage;
    }

    public static Parent getParentRoot(String file) throws IOException {
        setResourceBundleLanguage();
        return FXMLLoader.load(Objects.requireNonNull(loadResourceUrl(file)), resourceBundle);
    }

    public static URL loadResourceUrl(String file) throws IOException {
        return Util.class.getResource(PACKAGE_DIR + file + ".fxml");
    }

    public static Optional<Node> fxmlLoaderSetController(String file, BaseController controller) {
        Optional<Node> opt = Optional.empty();
        try {
            FXMLLoader loader = new FXMLLoader(loadResourceUrl(file), resourceBundle);
            loader.setController(controller);
            opt = Optional.of(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return opt;
    }

    public static String getString(String str) {
        if (resourceBundle == null) {
            setResourceBundleLanguage();
        }
        return resourceBundle.getString(str);
    }

    public static Alert popUpAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        Util.getStageWithIcon(stage);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);

        alert.showAndWait();
        return alert;
    }

    public static boolean confirmDeleteAlert(String title, String content) {
        return popUpAlert(title, content, Alert.AlertType.CONFIRMATION).getResult().getButtonData().isDefaultButton();
    }
}
