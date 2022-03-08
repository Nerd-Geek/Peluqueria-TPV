package ies.luisvives.peluqueriadamtpv.restcontroller;

import ies.luisvives.peluqueriadamtpv.model.LoginUser;
import ies.luisvives.peluqueriadamtpv.utils.ApplicationProperties;

import java.io.IOException;
import java.util.Objects;

public class APIRestConfig {
    public static final String API_PATH = "rest";
    public static String token;
    private static final String server = "localhost";
    private static final String port = "13169";
    private static final String endpoint = "/";
    private static final String API_URL = "http://" + server + ":" + port + endpoint;

    private APIRestConfig() {
    }

    public static void loginSetToken() {
        LoginUser loginUser = new LoginUser(ApplicationProperties.getInstance().readProperty("login_name")
                ,ApplicationProperties.getInstance().readProperty("login_password"));
        try {
            APIRestConfig.token = "Bearer " + Objects.requireNonNull(Objects.requireNonNull(RetrofitClient.getClient(API_URL).create(RestApiLogin.class).login(loginUser)
                    .execute().body()).getToken());
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static RestAPIAppointments getAppointmentsService() {
        return RetrofitClient.getClient(API_URL).create(RestAPIAppointments.class);
    }

    public static RestApiUsers getUsersService() {
        return RetrofitClient.getClient(API_URL).create(RestApiUsers.class);
    }

    public static RestApiService getServicesService() {
        return RetrofitClient.getClient(API_URL).create(RestApiService.class);
    }
}
