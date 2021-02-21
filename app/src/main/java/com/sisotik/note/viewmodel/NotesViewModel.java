package com.sisotik.note.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sisotik.note.model.api.ApiInterface;
import com.sisotik.note.model.api.RetrofitInstance;
import com.sisotik.note.model.response.NoteResponse;
import com.sisotik.note.model.response.OneNoteResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotesViewModel extends ViewModel {
    private ApiInterface apiInterface;

    private MutableLiveData<NoteResponse> notes;

    public MutableLiveData<NoteResponse> getAllNotes() {
        this.notes = getAll();
        return this.notes;
    }

    private MutableLiveData<NoteResponse> getAll() {
        final MutableLiveData<NoteResponse> refferPojoMutable = new MutableLiveData<>();
        apiInterface = RetrofitInstance.getClientRetrofit().create(ApiInterface.class);
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
                refferPojoMutable.setValue(null);
            }
        });
        return refferPojoMutable;
    }


}
