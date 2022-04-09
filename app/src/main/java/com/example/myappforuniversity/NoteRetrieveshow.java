package com.example.myappforuniversity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

public class NoteRetrieveshow extends AppCompatActivity {
    private FirebaseAuth mAuth; private RecyclerView mNotesList; private GridLayoutManager gridLayoutManager;
    FirebaseRecyclerAdapter firebaseRecyclerAdapter;
    private DatabaseReference fNOtesDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_retrieveshow);
        mNotesList=findViewById(R.id.main_notes_list1);
        gridLayoutManager=new GridLayoutManager(this,3, GridLayoutManager.VERTICAL,false);
        mNotesList.setHasFixedSize(true);
        mNotesList.setLayoutManager(gridLayoutManager);
        mAuth=FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null){
            fNOtesDatabase= FirebaseDatabase.getInstance().getReference().child("Notepad")
                    .child(mAuth.getCurrentUser().getUid());
            FirebaseRecyclerOptions<NoteModel> options = new FirebaseRecyclerOptions.
                    Builder<NoteModel>().setQuery(fNOtesDatabase, NoteModel.class).build();
            firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<NoteModel, NoteViewHolder>(options) {
                @Override
                public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_note_layout, parent, false);
                    return new NoteViewHolder(view); }
                @Override
                protected void onBindViewHolder(@NonNull @NotNull NoteViewHolder holder, int position, @NonNull @NotNull NoteModel model) {
                    String noteId = getRef(position).getKey();fNOtesDatabase.child(noteId).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                            String title = snapshot.child("title").getValue().toString();String content = snapshot.child("content").getValue().toString();
                            String timestamp = snapshot.child("timestamp").getValue().toString();
                            holder.setNoteTitle(title);holder.setNoteContent(content);
                            holder.setNoteTime(timestamp); }
                        @Override
                        public void onCancelled(@NonNull @NotNull DatabaseError error) {

                        }
                    });
                }
            }; mNotesList.setAdapter(firebaseRecyclerAdapter);
            ItemTouchHelper itemTouchHelper=new ItemTouchHelper(simpleCallback); itemTouchHelper.attachToRecyclerView(mNotesList);

        } }
    @Override
    protected void onStart() {
        super.onStart();
        firebaseRecyclerAdapter.startListening();}
    @Override
    protected void onStop() {
        super.onStop();
        firebaseRecyclerAdapter.stopListening();
    }
    ItemTouchHelper.SimpleCallback simpleCallback=new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT |ItemTouchHelper.RIGHT) {
        @Override
        public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
            return false; }
        @Override
        public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
 //   int position=viewHolder.getLayoutPosition();

            if(direction==ItemTouchHelper.LEFT || direction==ItemTouchHelper.RIGHT)
                Toast.makeText(NoteRetrieveshow.this,"Deleting notes",Toast.LENGTH_LONG).show();
        }
    };
}