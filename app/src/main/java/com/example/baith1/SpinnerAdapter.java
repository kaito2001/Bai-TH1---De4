package com.example.baith1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class SpinnerAdapter extends BaseAdapter {
    private     int[] imgs={R.drawable.xemay,R.drawable.oto,R.drawable.maybay};
    private Context ct;
    public SpinnerAdapter(Context ct){
        this.ct=ct;
    }

    @Override

    public int getCount() {
        return imgs.length;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View item= LayoutInflater.from(ct).inflate(R.layout.item_anh,viewGroup,false);
        ImageView img=item.findViewById(R.id.img);
        img.setImageResource(imgs[i]);

        return item;
    }
}