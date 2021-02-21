package com.sisotik.note.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sisotik.note.R;
import com.sisotik.note.model.response.ResultItem;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private ArrayList<ResultItem> listNote;

    public NotesAdapter(ArrayList<ResultItem> listAllNote) {
        this.listNote = listAllNote;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        ResultItem note = listNote.get(position);

        holder.txtTitle.setText(note.getTitleNote());
        holder.txtDescription.setText(note.getDescNote());
        holder.itemView.setOnClickListener(v -> {

        });
    }

    @Override
    public int getItemCount() {
        return listNote.size();
    }

    public class NotesViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtDescription;

        public NotesViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription = itemView.findViewById(R.id.txtDesc);
        }
    }
}
