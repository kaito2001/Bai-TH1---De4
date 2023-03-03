package com.example.baith1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.baith1.model.Tour;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TourAdapter.TourItemListener,SearchView.OnQueryTextListener{
    private Spinner sp;
    private RecyclerView recyclerView;
    private TourAdapter adapter;
    private EditText eTour,eTimeLine;
    private Button btAdd,btUpdate;
    private int pcurr;
    private SearchView searchView;
    private     int[] imgs={R.drawable.xemay,R.drawable.oto,R.drawable.maybay};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        initView();
        adapter=new TourAdapter(this);
        LinearLayoutManager manager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);
        searchView.setOnQueryTextListener(this);




        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tour tour = new Tour();
                String i=sp.getSelectedItem().toString();
                String tourA=eTour.getText().toString();
                String timeLine=eTimeLine.getText().toString();
                int img=R.drawable.oto;

                try {
                    img =imgs[Integer.parseInt(i)];

                    tour.setImg(img);
                    tour.setTour(tourA);
                    tour.setTimeLine(timeLine);
                    adapter.add(tour);
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Nhap",Toast.LENGTH_SHORT).show();
                }

            }
        });
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tour tour = new Tour();
                String i=sp.getSelectedItem().toString();
                String name=eTour.getText().toString();
                String des=eTimeLine.getText().toString();
                int img=R.drawable.xemay;
                double price=0;
                try {
                    img =imgs[Integer.parseInt(i)];
                    tour.setImg(img);
                    tour.setTour(name);
                    tour.setTimeLine(des);
                    adapter.update(pcurr,tour);
                    btAdd.setEnabled(true);
                    btUpdate.setEnabled(false);
                }catch (NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Please, re enter !",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initView(){
        sp=findViewById(R.id.img);
        SpinnerAdapter adapter=new SpinnerAdapter(this);
        sp.setAdapter(adapter);
        recyclerView=findViewById(R.id.recycleView);
        eTour=findViewById(R.id.name);
        eTimeLine=findViewById(R.id.describe);
        btAdd=findViewById(R.id.btAdd);
        btUpdate=findViewById(R.id.btUpdate);
        btUpdate.setEnabled(false);
        searchView=findViewById(R.id.search);
    }

    @Override
    public void onItemClick(View view, int p) {
        btAdd.setEnabled(false);
        btUpdate.setEnabled(true);
        pcurr=p;
        Tour tour=adapter.getitem(p);
        int img= tour.getImg();
        int ps=0;
        for(int i=0;i<imgs.length;i++){
            if(img==imgs[i]){
                ps=i;
                break;
            }
        }
        eTour.setText(tour.getTour());
        eTimeLine.setText(tour.getTimeLine());
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        filter(s);
        return false;
    }

    private void filter(String s) {
        List<Tour> filterlist=new ArrayList<>();
        for(Tour i:adapter.getBackup()){
            if(i.getTour().toLowerCase(Locale.ROOT).contains(s.toLowerCase())){
                filterlist.add(i);
            }
        }
        if(filterlist.isEmpty()){
            Toast.makeText(this,"Data not found",Toast.LENGTH_SHORT).show();
        }
        else {
            adapter.filterList((filterlist));
        }
    }
}