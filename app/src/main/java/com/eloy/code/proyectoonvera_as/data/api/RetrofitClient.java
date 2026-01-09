package com.eloy.code.proyectoonvera_as.data.api;

import android.content.Context;
import android.content.SharedPreferences;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "http://10.0.2.2:3000/api/v1/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofit(Context context) {
        if (retrofit == null) {

            SharedPreferences prefs = context.getSharedPreferences("AUTH", Context.MODE_PRIVATE);
            String token = prefs.getString("token", null);

            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {
                        Request request = chain.request();
                        if (token != null) {
                            request = request.newBuilder()
                                    .addHeader("Authorization", "Bearer " + token)
                                    .build();
                        }
                        return chain.proceed(request);
                    }).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
















/*package com.eloy.code.proyectoonvera_as.data.api;

import android.content.Context;
import android.content.SharedPreferences;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://10.0.2.2:3000/api/v1/";
    private static Retrofit retrofit;
    public static Retrofit getRetrofit(Context context) {

        if (retrofit == null) {
            SharedPreferences prefs =context.getSharedPreferences("auth", Context.MODE_PRIVATE);
            String token = prefs.getString("token", null);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(chain -> {
                        Request request = chain.request();
                        if (token != null) {request = request.newBuilder()
                                    .addHeader("Authorization","Bearer " + token)
                                .build();
                        }
                        return chain.proceed(request);
                    }).build();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}*/
