package com.sisotik.note.model.api;

import com.sisotik.note.model.response.NoteResponse;
import com.sisotik.note.model.response.OneNoteResponse;
import com.sisotik.note.model.response.ResultItem;
import com.sisotik.note.model.response.StatusResponse;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("/get/all/11318025")
    Call<NoteResponse> getAllNote();

    @GET("/get/{idNote}")
    Call<OneNoteResponse> getOneResponse(@Path("idNote") String idNote);

    @Headers("Content-Type:application/json")
    @PUT("/put/{idNote}")
    Call<StatusResponse> putOneNote(@Path("idNote") ResultItem note);

    @DELETE("/del/{idNote}")
    Call<StatusResponse> deleteOneNote(@Path("idNote") String idNote);

    @Headers("Content-Type:application/json")
    @POST("/post/11318025")
    Call<StatusResponse> postOneNote(ResultItem note);
}
