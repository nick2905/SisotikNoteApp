package com.sisotik.note.view.edit;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.sisotik.note.MainActivity;
import com.sisotik.note.R;
import com.sisotik.note.model.response.ResultItem;
import com.sisotik.note.viewmodel.NotesViewModel;

public class EditNoteActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE = "extra_title";
    public static final String EXTRA_DESC = "extra_desc";
    public static final String EXTRA_ID = "extra_id";

    private EditText editTextTitle, editTextDesc;
    private Button ubahBtn, hapusBtn;
    private SwipeRefreshLayout swipeRefreshLayout;
    private NotesViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDesc = findViewById(R.id.editTextDescNote);
        ubahBtn = findViewById(R.id.btnUbah);
        hapusBtn = findViewById(R.id.btnHapus);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshEdit);

        EditText dummyEditText = new EditText(this);
        dummyEditText.setText(EXTRA_TITLE);
        editTextTitle.setText(dummyEditText.getText().toString());

        dummyEditText.setText(EXTRA_DESC);
        editTextDesc.setText(dummyEditText.getText().toString());

        mViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(NotesViewModel.class);
        swipeRefreshLayout.setRefreshing(false);
        ubahBtn.setOnClickListener(v -> {
            if (isEmpty()) {
                Toast.makeText(this, "Harap isi data yang kosong", Toast.LENGTH_SHORT).show();
            } else {
                sendData();
            }
        });

        hapusBtn.setOnClickListener(v -> {
            if (isEmpty()) {
                Toast.makeText(this, "Harap isi data yang kosong", Toast.LENGTH_SHORT).show();
            } else {
                deleteNote();
            }
        });

    }

    private Boolean isEmpty() {
        if (editTextTitle.getText().toString().isEmpty() || editTextDesc.getText().toString().isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    private void sendData() {
        swipeRefreshLayout.setRefreshing(true);
        ResultItem note = new ResultItem();
        note.setTitleNote(editTextTitle.getText().toString());
        note.setDescNote(editTextDesc.getText().toString());
        mViewModel.putOneNoteResponse(EXTRA_ID, note).observe(this, response -> {
            if (response.isSuccess()) {
                swipeRefreshLayout.setRefreshing(false);
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(this, "Sepertinya ada kesalahan..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteNote() {
        swipeRefreshLayout.setRefreshing(true);
        mViewModel.deleteOneNoteResponse(EXTRA_ID).observe(this, statusResponse -> {
            if (statusResponse.isSuccess()) {
                swipeRefreshLayout.setRefreshing(false);
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(this, "Sepertinya ada kesalahan..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}