package com.example.baith1;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baith1.model.Tour;

import java.util.ArrayList;
import java.util.List;

public class TourAdapter extends RecyclerView.Adapter<TourAdapter.TourViewHolder>{
    private Context context;
    private List<Tour> listBackup;
    private List<Tour> mList;
    private TourItemListener mTourItem;
    public TourAdapter(Context context) {
        this.context = context;
        mList=new ArrayList<>();
        listBackup=new ArrayList<>();
    }
    public List<Tour> getBackup(){
        return listBackup;
    }
    public void setmList(List<Tour> mList){
        this.mList=mList;
    }
    public void filterList(List<Tour> filterlist){
        mList=filterlist;
        notifyDataSetChanged();
    }
    public void setClickListener(TourItemListener mTourItem){
        this.mTourItem=mTourItem;
    }

    @NonNull
    @Override
    public TourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new TourViewHolder(view);
    }
    public Tour getitem(int p){
        return mList.get(p);
    }
    @Override
    public void onBindViewHolder(@NonNull TourViewHolder holder, int position) {
        Tour tour=mList.get(position);
        if(tour==null) return ;
        holder.img.setImageResource(tour.getImg());
        holder.tvName.setText(tour.getTour());
        holder.tvDescrible.setText(tour.getTimeLine());
        holder.btRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Notifi");
                builder.setMessage("Delete "+ tour.getTour()+" ?");
                builder.setIcon(R.drawable.xemay);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listBackup.remove(position);
                        mList.remove(position);
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });
    }
    public void add(Tour c){
        listBackup.add(c);
        mList.add(c);
        notifyDataSetChanged();
    }
    public  void update(int p,Tour c){
        listBackup.set(p,c);
        mList.set(p,c);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mList!=null) return mList.size();
        return 0;
    }

    public class TourViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView img;
        private TextView tvName,tvDescrible;
        private Button btRemove;
        public TourViewHolder(@NonNull View view) {
            super(view);
            img=view.findViewById(R.id.img);
            tvName=view.findViewById(R.id.txtName);
            tvDescrible=view.findViewById(R.id.txtDescribe);
            btRemove=view.findViewById(R.id.btRemove);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mTourItem!=null){
                mTourItem.onItemClick(view,getAdapterPosition());
            }
        }
    }
    public interface TourItemListener{
        void onItemClick(View view , int p);

    }

}
