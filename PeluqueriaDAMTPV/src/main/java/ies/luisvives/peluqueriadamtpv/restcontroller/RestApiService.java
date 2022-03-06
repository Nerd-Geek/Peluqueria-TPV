package ies.luisvives.peluqueriadamtpv.restcontroller;

import ies.luisvives.peluqueriadamtpv.model.Service;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface RestApiService {

    @GET(APIRestConfig.API_PATH + "/services/")
    Call<List<Service>> serviceGetAll(@Header("Authorization") String token);

    @GET(APIRestConfig.API_PATH + "/services/")
    Call<List<Service>> serviceGetAllWithService_name(@Header("Authorization") String token, @Query("searchQuery") String searchQuery);

    @GET(APIRestConfig.API_PATH + "/services/{name}")
    Call<Service> getByName(@Header("Authorization") String token);

    @GET(APIRestConfig.API_PATH + "/services/{name}")
    Call<Service> getByNameByOrderByPriceAsc(@Header("Authorization") String token);

    @GET(APIRestConfig.API_PATH + "/services/{id}")
    Call<Service> serviceGetById(@Header("Authorization") String token, @Path("id") String id);

    @POST(APIRestConfig.API_PATH + "/services/")
    Call<Service> insertService(@Header("Authorization") String token, @Body Service service);

    @PUT(APIRestConfig.API_PATH + "/services/{id}")
    Call<Service> updateService(@Header("Authorization") String token);

    @DELETE(APIRestConfig.API_PATH + "/services/{id}")
    Call<Service> deleteService(@Header("Authorization") String token, @Path("id") String id);

}
