package ies.luisvives.peluqueriadamtpv.restcontroller;


import ies.luisvives.peluqueriadamtpv.model.Appointment;
import ies.luisvives.peluqueriadamtpv.model.User;
import retrofit2.Call;
import retrofit2.http.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface RestAPIAppointments {

    @GET("appointments/")
    Call<List<Appointment>> appointmentsGetAll();

    @GET("appointments/")
    Call<Appointment> appointmentGetAllWithDate(@Query("date") LocalDate date);

    @GET("appointments/")
    Call<List<Appointment>> appointmentGetAllWithUser_Username(@Query("searchQuery") String searchQuery);

    @GET("appointments/")
    Call<List<Appointment>> appointmentFindByDateAndUser_UsernameContainsIgnoreCaseAndService_Id(
            @Query("searchQuery") String searchQuery
            , @Query("date") LocalDate date
            , @Query("service_id") String serviceId);

    @GET("appointments/{id}")
    Call<Appointment> appointmentGetById(@Path("id") String id);

    @POST("appointments/")
    Call<Appointment> insertAppointments(@Body Appointment appointment);

    @PUT("appointments/{id}")
    Call<Appointment> updateAppointments();

    @DELETE("appointments/{id}")
    Call<Appointment> deleteAppointmentById(@Path("id") String id);
}
