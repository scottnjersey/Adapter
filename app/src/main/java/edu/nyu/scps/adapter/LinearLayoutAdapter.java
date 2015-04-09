package edu.nyu.scps.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class LinearLayoutAdapter extends BaseAdapter {
    private Context context;
    private List<ResolveInfo> list;

    public LinearLayoutAdapter(Context context) {
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
        LinearLayout linearLayout;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            linearLayout = (LinearLayout)inflater.inflate(R.layout.row, null);
        } else {
            linearLayout = (LinearLayout)convertView;
        }

        ResolveInfo resolveInfo = list.get(position);
        ActivityInfo activityInfo = resolveInfo.activityInfo;
        PackageManager packageManager = context.getPackageManager();

        ImageView imageView = (ImageView)linearLayout.findViewById(R.id.imageView);
        Drawable drawable = activityInfo.loadIcon(packageManager);
        imageView.setImageDrawable(drawable);

        TextView textView = (TextView)linearLayout.findViewById(R.id.textView);
        CharSequence label = activityInfo.loadLabel(packageManager);
        textView.setText(label + "\n" + activityInfo.name);
        return linearLayout;
    }
}
