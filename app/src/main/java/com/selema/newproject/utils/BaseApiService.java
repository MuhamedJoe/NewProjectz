package com.selema.newproject.utils;

import com.selema.newproject.Messages.MessageList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface BaseApiService {


    @FormUrlEncoded
    @POST("login")
    Call<Login_Response> loginRequest(@Field("id") String phone,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("regist")
    Call<ResponseBody> registerRequest(@Field("email") String email,
                                       @Field("password") String password,
                                       @Field("frist") String fristname,
                                       @Field("last") String lastname,
                                       @Field("home") String home,
                                       @Field("phoneNumber") String phone,
                                       @Field("current") String currentaddress,
                                       @Field("bio") String bio);

    @FormUrlEncoded
    @POST("search")
    Call<Search_response> SearchRequest(@Field("key") String key);

    @FormUrlEncoded
    @POST("add-message")
    Call<ResponseBody> RequestMoney(@Field("senderID") String senderId,
                                       @Field("reciverID") String reciverId,
                                       @Field("message-time") String messageTime,
                                       @Field("message-content") String messageContent,
                                       @Field("requested-money") String requestedMoney);
    @FormUrlEncoded
    @POST("get-messages")
    Call<MessageList> GetMessages(@Field("id") String id);

}
