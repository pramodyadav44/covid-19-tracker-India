package com.example.coronago;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<StateItem> stateItems;
    private Context context;

    public MyAdapter(List<StateItem> stateItems, Context context) {
        this.stateItems = stateItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.state_list,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        final StateItem stateItem = stateItems.get(position);
        holder.sName.setText(stateItem.getName());
        holder.stotal.setText(stateItem.getTotal());
        holder.srecov.setText(stateItem.getRecoverd());
        holder.sdeath.setText(stateItem.getDeath());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), holder.sName.getText().toString(), Toast.LENGTH_SHORT).show();
                //i.putExtra("sname",holder.)
                context.startActivity(new Intent(v.getContext(),DistrictActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                        .putExtra("stateName",holder.sName.getText().toString()));


            }
        });
    }


    @Override
    public int getItemCount() {
        return stateItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView sName,stotal,srecov,sdeath;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sName = (TextView) itemView.findViewById(R.id.statename);
            stotal = (TextView) itemView.findViewById(R.id.total);
            srecov = (TextView) itemView.findViewById(R.id.cured);
            sdeath = (TextView) itemView.findViewById(R.id.death);


        }
    }
}
