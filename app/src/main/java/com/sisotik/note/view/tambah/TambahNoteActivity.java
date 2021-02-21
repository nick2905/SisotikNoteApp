package com.sisotik.note.view.tambah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.sisotik.note.MainActivity;
import com.sisotik.note.R;
import com.sisotik.note.model.response.ResultItem;
import com.sisotik.note.viewmodel.NotesViewModel;

public class TambahNoteActivity extends AppCompatActivity {

    private NotesViewModel mViewModel;
    private EditText editTextTitle, editTextDesc;
    private Button btnTambah;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_note);
        editTextTitle = findViewById(R.id.editTextTitle);
        editTextDesc = findViewById(R.id.editTextDescNote);
        btnTambah = findViewById(R.id.btnTambah);
        progressBar = findViewById(R.id.progressBar);

        mViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(NotesViewModel.class);
        progressBar.setVisibility(View.GONE);
        btnTambah.setOnClickListener(v -> {
            if (isEmpty()) {
                Toast.makeText(this, "Harap isi data yang kosong", Toast.LENGTH_SHORT).show();
            } else {
                sendData();
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
        progressBar.setVisibility(View.VISIBLE);
        ResultItem note = new ResultItem();
        note.setTitleNote(editTextTitle.getText().toString());
        note.setDescNote(editTextDesc.getText().toString());
        Toast.makeText(this, editTextTitle.getText().toString(), Toast.LENGTH_SHORT).show();

        mViewModel.postOneNote(note).observe(this, response -> {
            if (response.isSuccess()) {
                progressBar.setVisibility(View.GONE);
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(this, "Sepertinya ada kesalahan..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}