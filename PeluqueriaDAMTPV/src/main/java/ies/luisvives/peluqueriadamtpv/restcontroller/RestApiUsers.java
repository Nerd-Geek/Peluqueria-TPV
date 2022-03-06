package ies.luisvives.peluqueriadamtpv.restcontroller;

import ies.luisvives.peluqueriadamtpv.model.User;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface RestApiUsers {

    @GET(APIRestConfig.API_PATH + "/users/")
    Call<List<User>> usersGetAll(@Header("Authorization") String token);

    @GET(APIRestConfig.API_PATH + "/users/")
    Call<List<User>> userGetAllWithUser_name(@Header("Authorization") String token, @Query("searchQuery") String searchQuery);

    @GET(APIRestConfig.API_PATH + "/users/{username}")
    Call<User> findByUsername(@Header("Authorization") String token, @Path("username") String username);

    @GET(APIRestConfig.API_PATH + "/users/{id}")
    Call<User> usersGetById(@Header("Authorization") String token, @Path("id") String id);

    @POST(APIRestConfig.API_PATH + "/users/")
    Call<User> insertUsers(@Header("Authorization") String token, @Body User user);

    @PUT(APIRestConfig.API_PATH + "/users/{id}")
    Call<User> updateUsers(@Header("Authorization") String token);

    @DELETE(APIRestConfig.API_PATH + "/users/{id}")
    Call<User> deleteUser(@Header("Authorization") String token, @Path("id") String id);

}
