package edu.nyu.scps.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


public class DogAdapter extends BaseAdapter {
    private Context context;
    private Resources resources;

    private int[] thumb = {
            R.drawable.sample_thumb_0,
            R.drawable.sample_thumb_1,
            R.drawable.sample_thumb_2,
            R.drawable.sample_thumb_3,
            R.drawable.sample_thumb_4,
            R.drawable.sample_thumb_5,
            R.drawable.sample_thumb_6,
            R.drawable.sample_thumb_7
    };

    public DogAdapter(Context context) {
        this.context = context;
        resources = context.getResources();
    }

    @Override
    public int getCount() {
        return thumb.length;
    }

    @Override
    public Object getItem(int position) {
        Drawable drawable = resources.getDrawable(thumb[position]);
        return drawable;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setScaleType(ImageView.ScaleType.FIT_START); //left justify

            //Convert 8 dp to pixels.
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            int padding = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, displayMetrics);
            imageView.setPadding(0, padding, 0, padding);
        } else {
            imageView = (ImageView)convertView;
        }

        imageView.setImageResource(thumb[position]);
        return imageView;
    }
}
