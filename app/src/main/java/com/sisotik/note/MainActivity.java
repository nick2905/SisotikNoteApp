package com.sisotik.note;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sisotik.note.model.response.ResultItem;
import com.sisotik.note.view.adapter.NotesAdapter;
import com.sisotik.note.view.tambah.TambahNoteActivity;
import com.sisotik.note.viewmodel.NotesViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ResultItem> noteList = new ArrayList<>();
    private NotesViewModel mViewModel;
    private RecyclerView rvNote;
    private NotesAdapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // initialisation
        rvNote = findViewById(R.id.rvNotes);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        fab = findViewById(R.id.fab);

        mAdapter = new NotesAdapter(noteList, this);
        rvNote.setLayoutManager(new LinearLayoutManager(this));
        rvNote.setAdapter(mAdapter);

        mViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(NotesViewModel.class);

        swipeRefreshLayout.setRefreshing(true);
        setupData();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            setupData();
        });

        fab.setOnClickListener(v -> {
            startActivity(new Intent(this, TambahNoteActivity.class));
        });
    }

    private void setupData() {
        mViewModel.getAllNotes().observe(this, notes -> {
            noteList.clear();
            noteList.addAll(notes.getResult());
            mAdapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
        });
    }
}