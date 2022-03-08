package ies.luisvives.peluqueriadamtpv.restcontroller;

import ies.luisvives.peluqueriadamtpv.model.LoginUser;
import ies.luisvives.peluqueriadamtpv.model.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RestApiLogin {
    @POST(APIRestConfig.API_PATH + "/users/login")
    Call<User> login(@Body LoginUser loginUser);
}
