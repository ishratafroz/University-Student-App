package com.example.myappforuniversity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.example.myappforuniversity.chat2Activity.rImage;
import static com.example.myappforuniversity.chat2Activity.sImage;

public class MessagesAdapter extends RecyclerView.Adapter {
    Context context; ArrayList<Messages>messagesArrayList;
    int ITEM_SEND=1;
    int ITEM_RECIVE=2;
//Messages messages=new Messages();
public MessagesAdapter(Context context,ArrayList<Messages>messagesArrayList) {
    this.context = context; this.messagesArrayList=messagesArrayList;
}
    @NonNull
    @NotNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        if(viewType==ITEM_SEND)
        {
            View view= LayoutInflater.from(context).inflate(R.layout.sender_layout_item,parent,false);
            return  new SenderViewHolder(view);
        } else {View view= LayoutInflater.from(context).inflate(R.layout.reciever_layout_item,parent,false);
            return  new ReciverViewHolder(view); }
    }@Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {
        Messages messages=messagesArrayList.get(position); if(holder.getClass()==SenderViewHolder.class)
        {
            SenderViewHolder viewHolder=(SenderViewHolder)holder;
            viewHolder.txtMessage.setText(messages.getMessage());
            Picasso.get().load(sImage).into(viewHolder.circleImageView);
        } else {
            ReciverViewHolder viewHolder=(ReciverViewHolder)holder;
            viewHolder.txtMessage.setText(messages.getMessage());
            Picasso.get().load(rImage).into(viewHolder.circleImageView);
        }
    }@Override
    public int getItemCount() {
    try{
        return messagesArrayList.size();
    }catch(Exception e) {
     return 0;
    } }
    @Override
    public int getItemViewType(int position) {
        try {
            Messages messages=messagesArrayList.get(position);
            if(FirebaseAuth.getInstance().getCurrentUser().getUid().equals(messages.getSenderId()))
            { return  ITEM_SEND; }
            else { return ITEM_RECIVE; }
        } catch (Exception e)
        {
         return 0;
        } }
    class  SenderViewHolder extends RecyclerView.ViewHolder {
        CircleImageView circleImageView; TextView txtMessage;
        public SenderViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);circleImageView=itemView.findViewById(R.id.profile_image4);
            txtMessage=itemView.findViewById(R.id.sendermg1);
        }
    }
class  ReciverViewHolder extends RecyclerView.ViewHolder {CircleImageView circleImageView; TextView txtMessage;
    public ReciverViewHolder(@NonNull @NotNull View itemView) {
        super(itemView); circleImageView=itemView.findViewById(R.id.profile_image4);
        txtMessage=itemView.findViewById(R.id.sendermg1);
    }
}

}
