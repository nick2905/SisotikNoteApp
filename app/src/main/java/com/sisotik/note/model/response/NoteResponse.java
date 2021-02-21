package com.sisotik.note.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NoteResponse {

    @SerializedName("result")
    private ArrayList<ResultItem> result;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public void setResult(ArrayList<ResultItem> result) {
        this.result = result;
    }

    public ArrayList<ResultItem> getResult() {
        return result;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return
                "NoteResponse{" +
                        "result = '" + result + '\'' +
                        ",success = '" + success + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }
}