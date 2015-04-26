package io.frontendlabs.wallpaper;

import android.app.ActionBar;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by Administrador on 25/04/2015.
 */
public class ImageAdapter extends BaseAdapter{

    public Integer[] mImagenes = {R.drawable.pic_1, R.drawable.pic_2,
                                    R.drawable.pic_3, R.drawable.pic_4,
                                    R.drawable.pic_5, R.drawable.pic_6,
                                    R.drawable.pic_7, R.drawable.pic_8,
                                    R.drawable.pic_9, R.drawable.pic_10
    };

    private Context mContexto;

    @Override
    public int getCount() {
        return mImagenes.length;
    }

    @Override
    public Object getItem(int position) {
        return mImagenes[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView vista = new ImageView(mContexto);
        vista.setImageResource(mImagenes[position]);
        vista.setScaleType(ImageView.ScaleType.CENTER_CROP);
        vista.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 200));
        return vista;

    }



    public ImageAdapter(Context contexto) {
        mContexto = contexto;
    }



}
