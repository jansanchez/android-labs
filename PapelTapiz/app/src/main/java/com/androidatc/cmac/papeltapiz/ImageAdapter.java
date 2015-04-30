package com.androidatc.cmac.papeltapiz;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.app.ActionBar.LayoutParams;

/**
 * Created by Instructor on 26/03/2015.
 */
public class ImageAdapter extends BaseAdapter {
public Integer[] miniaturas= {R.drawable.pic_1,R.drawable.pic_2,
        R.drawable.pic_3,R.drawable.pic_4,
        R.drawable.pic_5,R.drawable.pic_6,R.drawable.pic_7,
        R.drawable.pic_8,R.drawable.pic_9,R.drawable.pic_10};

private Context mContext;

    public ImageAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return miniaturas.length;
    }

    @Override
    public Object getItem(int position) {
        return miniaturas[position];
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(int position, View convertView,
                        ViewGroup parent) {
        ImageView imagen = new ImageView(mContext);
        imagen.setImageResource(miniaturas[position]);
        imagen.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imagen.setLayoutParams(new GridView.
                LayoutParams(LayoutParams.WRAP_CONTENT,200));
        return imagen;
    }
}
