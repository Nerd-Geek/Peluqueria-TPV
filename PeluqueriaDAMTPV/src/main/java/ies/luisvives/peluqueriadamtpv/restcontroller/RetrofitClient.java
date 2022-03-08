package ies.luisvives.peluqueriadamtpv.restcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitClient {
    private static Retrofit instance;

    private RetrofitClient() {

    }

    public static Retrofit getClient(String url) {
        if (instance == null) {
            ObjectMapper objectMapper =
                    new ObjectMapper()
                            .registerModule(new JavaTimeModule())
                            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            instance = new Retrofit.Builder()
                    .baseUrl(url)
//                    .addConverterFactory(GsonConverterFactory.create())
                    .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                    .build();
        }
        return instance;
    }
}
