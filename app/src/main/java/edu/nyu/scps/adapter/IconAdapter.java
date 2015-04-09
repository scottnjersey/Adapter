package edu.nyu.scps.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;


public class IconAdapter extends BaseAdapter {
    private Context context;
    private List<ResolveInfo> list;

    public IconAdapter(Context context) {
        this.context = context;
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager packageManager = context.getPackageManager();
        list = packageManager.queryIntentActivities(intent, 0);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
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
        } else {
            imageView = (ImageView)convertView;
        }

        ResolveInfo resolveInfo = list.get(position);
        ActivityInfo activityInfo = resolveInfo.activityInfo;
        PackageManager packageManager = context.getPackageManager();
        Drawable drawable = activityInfo.loadIcon(packageManager);
        imageView.setImageDrawable(drawable);
        return imageView;
    }
}
