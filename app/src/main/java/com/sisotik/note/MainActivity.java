package com.sisotik.note;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.sisotik.note.model.response.ResultItem;
import com.sisotik.note.view.adapter.NotesAdapter;
import com.sisotik.note.viewmodel.NotesViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ResultItem> noteList = new ArrayList<>();
    private NotesViewModel mViewModel;
    private RecyclerView rvNote;
    private NotesAdapter mAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvNote = findViewById(R.id.rvNotes);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        mAdapter = new NotesAdapter(noteList);
        rvNote.setLayoutManager(new LinearLayoutManager(this));
        rvNote.setAdapter(mAdapter);

        mViewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(NotesViewModel.class);

        swipeRefreshLayout.setRefreshing(true);
        setupData();

        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            setupData();
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