package com.example.myappforuniversity;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.annotations.NotNull;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    View mView; TextView textTitle,textContent,textTime;
    public NoteViewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        mView=itemView; textTitle=mView.findViewById(R.id.layoutnotetitle);
        textTime=mView.findViewById(R.id.layoutnotetime);  textContent=mView.findViewById(R.id.layoutnotecontent);
    }
    public void setNoteTitle(String title) {
        textTitle.setText(title);
    }
    public void setNoteContent(String content) {
        textContent.setText(content);
    }
    public void setNoteTime(String time) {
        textTime.setText(time);
    }
}