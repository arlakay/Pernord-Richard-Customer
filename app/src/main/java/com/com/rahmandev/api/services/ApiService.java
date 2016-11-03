package com.com.rahmandev.api.services;

import com.com.rahmandev.model.ClubResponse;
import com.com.rahmandev.model.Registration;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by ILM on 6/29/2016.
 */
public interface ApiService {
    //Register -
    @FormUrlEncoded
    @POST("customer/register")
    Call<Registration> register(@Field("first_name") String first_name,
                                @Field("last_name") String last_name,
                                @Field("customer_code") String customer_code,
                                @Field("email") String email,
                                @Field("password") String password,
                                @Field("phone_number") String phone_number,
                                @Field("birth_date") String birth_date);

    //Login -
    @FormUrlEncoded
    @POST("customer/login")
    Call<Registration> login(@Field("username-or-email") String username_or_email,
                             @Field("password") String password);

    //Change Password -
    @FormUrlEncoded
    @POST("customer/changePassword")
    Call<Registration> changePassword(@Field("email") String email,
                                      @Field("old_password") String old_password,
                                      @Field("new_password") String new_password);

    @GET("club/getAllClub")
    Call<ClubResponse> getClubList();
}
