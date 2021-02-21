package com.sisotik.note.model.response;

import com.google.gson.annotations.SerializedName;

public class OneNoteResponse {

    @SerializedName("result")
    private ResultItem result;

    @SerializedName("success")
    private boolean success;

    @SerializedName("message")
    private String message;

    public void setResult(ResultItem result) {
        this.result = result;
    }

    public ResultItem getResult() {
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
                "OneNoteResponse{" +
                        "result = '" + result + '\'' +
                        ",success = '" + success + '\'' +
                        ",message = '" + message + '\'' +
                        "}";
    }
}