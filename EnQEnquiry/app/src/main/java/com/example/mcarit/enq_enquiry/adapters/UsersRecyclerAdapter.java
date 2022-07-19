package com.example.mcarit.enq_enquiry.adapters;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mcarit.enq_enquiry.R;
import com.example.mcarit.enq_enquiry.model.User;

import java.util.List;

/**
 * Created by lalit on 10/10/2016.
 */

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.UserViewHolder> {

    private List<User> listUsers;

    public UsersRecyclerAdapter(List<User> listUsers) {
        this.listUsers = listUsers;
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler item view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_recycler, parent, false);

        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.textViewFileno.setText(listUsers.get(position).getFileno());
        holder.textViewMsg.setText(listUsers.get(position).getMsg());
        holder.textViewPhone.setText(listUsers.get(position).getPhone());
        holder.textViewDate.setText(listUsers.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        Log.v(UsersRecyclerAdapter.class.getSimpleName(),""+listUsers.size());
        return listUsers.size();
    }


    /**
     * ViewHolder class
     */
    public class UserViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewFileno;
        public TextView textViewMsg;
        public TextView textViewPhone;
        public TextView textViewDate;


        public UserViewHolder(View view) {
            super(view);
            textViewFileno = (TextView) view.findViewById(R.id.textViewFileno);
            textViewMsg = (TextView) view.findViewById(R.id.textViewMsg);
            textViewPhone = (TextView) view.findViewById(R.id.textViewPhone);
            textViewDate = (TextView) view.findViewById(R.id.textViewDate);
        }
    }


}
