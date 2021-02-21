package com.sisotik.note.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sisotik.note.model.api.ApiInterface;
import com.sisotik.note.model.api.RetrofitInstance;
import com.sisotik.note.model.response.NoteResponse;
import com.sisotik.note.model.response.OneNoteResponse;
import com.sisotik.note.model.response.ResultItem;
import com.sisotik.note.model.response.StatusResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotesViewModel extends ViewModel {
    private ApiInterface apiInterface = RetrofitInstance.getClientRetrofit().create(ApiInterface.class);

    private MutableLiveData<NoteResponse> notes;
    private MutableLiveData<StatusResponse> status;
    final MutableLiveData<StatusResponse> refferPojoMutable = new MutableLiveData<>();

    public MutableLiveData<NoteResponse> getAllNotes() {
        this.notes = getAllResponse();
        return this.notes;
    }

    public MutableLiveData<StatusResponse> postOneNote(ResultItem note) {
        this.status = postOneNoteResponse(note);
        return this.status;
    }


    private MutableLiveData<NoteResponse> getAllResponse() {
        final MutableLiveData<NoteResponse> refferPojoMutable = new MutableLiveData<>();
        Call<NoteResponse> call = apiInterface.getAllNote();
        call.enqueue(new Callback<NoteResponse>() {
            @Override
            public void onResponse(Call<NoteResponse> call, Response<NoteResponse> response) {
                if (response.body() != null) {
                    {
                        refferPojoMutable.setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<NoteResponse> call, Throwable t) {
            }
        });
        return refferPojoMutable;
    }

    public MutableLiveData<OneNoteResponse> getOneResponse(String idNote) {
        final MutableLiveData<OneNoteResponse> refferPojoMutable = new MutableLiveData<>();
        apiInterface = RetrofitInstance.getClientRetrofit().create(ApiInterface.class);
        Call<OneNoteResponse> call = apiInterface.getOneResponse(idNote);
        call.enqueue(new Callback<OneNoteResponse>() {
            @Override
            public void onResponse(Call<OneNoteResponse> call, Response<OneNoteResponse> response) {
                if (response.body() != null) {
                    refferPojoMutable.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<OneNoteResponse> call, Throwable t) {

            }
        });
        return refferPojoMutable;
    }

    public MutableLiveData<StatusResponse> putOneNoteResponse(String idNote, ResultItem note) {
        Call<StatusResponse> call = apiInterface.putOneNote(idNote, note);
        call.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                if (response.body() != null) {
                    refferPojoMutable.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {

            }
        });
        return refferPojoMutable;
    }

    public MutableLiveData<StatusResponse> postOneNoteResponse(ResultItem note) {
        Call<StatusResponse> call = apiInterface.postOneNote(note);
        call.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                if (response.body() != null) {
                    refferPojoMutable.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {

            }
        });
        return refferPojoMutable;
    }

    public MutableLiveData<StatusResponse> deleteOneNoteResponse(String idNote) {
        Call<StatusResponse> call = apiInterface.deleteOneNote(idNote);
        call.enqueue(new Callback<StatusResponse>() {
            @Override
            public void onResponse(Call<StatusResponse> call, Response<StatusResponse> response) {
                if (response.body() != null) {
                    refferPojoMutable.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<StatusResponse> call, Throwable t) {

            }
        });
        return refferPojoMutable;
    }
}
