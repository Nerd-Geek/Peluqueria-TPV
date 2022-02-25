package ies.luisvives.peluqueriadamtpv.restcontroller;

import ies.luisvives.peluqueriadamtpv.model.Service;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface RestApiService {

    @GET("services")
    Call<List<Service>> serviceGetAll();

    @GET("services/{name}")
    Call<?> getByName();

    @GET("services/{name}")
    Call<?> getByNameByOrderByPriceAsc();

    @GET("services/{id}")
    Call<?> serviceGetById(@Path("id") String id);

    @POST("services")
    Call<Service> insertService(@Body Service service);

    @PUT("services/{id}")
    Call<?> updateService();

    @DELETE("services/{id}")
    Call<Service> deleteService(@Path("id") String id);
}