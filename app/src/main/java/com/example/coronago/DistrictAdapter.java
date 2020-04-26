package com.example.coronago;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DistrictAdapter extends RecyclerView.Adapter<DistrictAdapter.ViewHolder> {

    private List<DistrictItem> districtItems;
    private Context context;

    public DistrictAdapter(List<DistrictItem> districtItems, Context context) {
        this.districtItems = districtItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.district_list,parent,false);
        return new DistrictAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final DistrictItem districtItem = districtItems.get(position);
        holder.dName.setText(districtItem.getName());
        holder.dTotal.setText(districtItem.getTotal());
        holder.dRecov.setText(districtItem.getRecoverd());
        holder.dDeath.setText(districtItem.getDeath());
    }

    @Override
    public int getItemCount() {
        return districtItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView dName,dTotal,dRecov,dDeath;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            dName = (TextView) itemView.findViewById(R.id.distName);
            dTotal = (TextView) itemView.findViewById(R.id.disTotal);
            dRecov = (TextView) itemView.findViewById(R.id.distCured);
            dDeath = (TextView) itemView.findViewById(R.id.distDeath);
        }
    }
}
