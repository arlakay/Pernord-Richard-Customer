package com.ilm.mydrinks.api.services;

import com.ilm.mydrinks.model.Claim;
import com.ilm.mydrinks.model.ClubResponse;
import com.ilm.mydrinks.model.MyBottleResponse;
import com.ilm.mydrinks.model.NotificationResponse;
import com.ilm.mydrinks.model.Profile;
import com.ilm.mydrinks.model.Registration;

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

    //Get All Club
    @GET("club/getAllClub")
    Call<ClubResponse> getClubList();

    //My Bottle
    @FormUrlEncoded
    @POST("customer/myBottle")
    Call<MyBottleResponse> myBottle(@Field("customer_code") String customer_code);

    //View Profile
    @FormUrlEncoded
    @POST("customer/viewProfile")
    Call<Profile> viewProfile(@Field("customer_code") String customer_code);

    //Edit Profile
//    @FormUrlEncoded
//    @POST("customer/updateProfile")
//    Call<Profile> editProfile(@Field("customer_code ")String a,
//                              @Field("first_name ")String a,
//                              @Field("last_name")String a,
//                              @Field("birth_date")String a,
//                              @Field("gender")String a,
//                              @Field("phone ")String a,
//                              @Field("street/address")String a,
//                              @Field("city_id")String a,
//                              @Field("country_id")String a,
//                              @Field("picture ")String a,
//                              );

    //Claim Bottle
    @FormUrlEncoded
    @POST("customer/claim")
    Call<Claim> claimBottle(@Field("serial_number") String serial_number,
                            @Field("customer_code") String customer_code);

    //Get Country

    //Get City by Country

    //GetMessage
    @GET("message/getAll")
    Call<NotificationResponse> getMessage();
}
