package com.example.myappforuniversity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
public class UseAdapter extends RecyclerView.Adapter<UseAdapter.Viewholder> {
    Context chatActivity;
    ArrayList<Users> usersArrayList;
    public UseAdapter(chatActivity chatActivity, ArrayList<Users> usersArrayList) {
        this.chatActivity=chatActivity; this.usersArrayList=usersArrayList;
    }
    @NonNull
    @NotNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(chatActivity).inflate(R.layout.item_user,parent,false);
        return new Viewholder(view); }
    @Override
    public void onBindViewHolder(@NonNull @NotNull Viewholder holder, int position) {
        Users users=usersArrayList.get(position);
        holder.t1.setText(users.name);
        holder.t2.setText(users.roll);
        Picasso.get().load(users.image).into(holder.c1);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(chatActivity, "add", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(chatActivity,chat2Activity.class);
                intent.putExtra("name",users.getName()); intent.putExtra("ReceiverImage",users.getImage());
                intent.putExtra("Uid",users.getId());
                chatActivity.startActivity(intent); }}); }
    @Override
    public int getItemCount()
    { return usersArrayList.size(); }
    class Viewholder extends RecyclerView.ViewHolder {
        CircleImageView c1; TextView t1,t2;
        public Viewholder(@NonNull @NotNull View itemView) {
            super(itemView);
            c1=itemView.findViewById(R.id.profile_image2);
            t1=itemView.findViewById(R.id.itemuser1); t2=itemView.findViewById(R.id.itemuser2); }}}
