package com.yjnull.latte_core.net;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by yangya on 2018/7/3.
 *
 */

public interface RestService {

    @GET
    Call<String> get(@Url String url, @QueryMap Map<String, Object> params);

    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String, Object> params);

    @POST
    Call<String> postRaw(@Url String url, @Body RequestBody body);

    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url, @FieldMap Map<String, Object> params);

    @PUT
    Call<String> putRaw(@Url String url, @Body RequestBody body);

    @DELETE
    Call<String> delete(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * get 的变种
     * retrofit 默认的download 会将文件一次性下载到内存里，文件下载完毕后，再统一写入到文件里
     * 问题：当文件过大时，会导致内存溢出，导致 app 闪退或报错
     * 解决：Streaming 注解，边下载边写入文件, 同时注意需异步执行
     */
    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url, @QueryMap Map<String, Object> params);

    /**
     * post 的变种
     */
    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part file);
}
