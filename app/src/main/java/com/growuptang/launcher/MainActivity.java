package com.growuptang.launcher;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends Activity {
    private List<ResolveInfo> mApps;
    private GridView mGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mApps = getPackageManager().queryIntentActivities(mainIntent, 0);

        setContentView(R.layout.activity_main);
        mGrid = (GridView) findViewById(R.id.grid_view);
        mGrid.setAdapter(new AppsAdapter());

        mGrid.setOnItemClickListener(listener);
    }

    private AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            ResolveInfo info = mApps.get(position);

            //该应用的包名
            String pkg = info.activityInfo.packageName;
            //应用的主activity类
            String cls = info.activityInfo.name;

            ComponentName componet = new ComponentName(pkg, cls);

            Intent intent = new Intent();
            intent.setComponent(componet);
            startActivity(intent);
        }
    };


    public class AppsAdapter extends BaseAdapter implements ListAdapter {

        public AppsAdapter() {
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView iv_app_icon;
            TextView textView;

            if (convertView == null) {
                iv_app_icon = new ImageView(MainActivity.this);
                textView = new TextView(MainActivity.this);

                iv_app_icon.setScaleType(ImageView.ScaleType.FIT_CENTER);
                iv_app_icon.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

//                textView.setGravity(Gravity.CENTER);
//                textView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            } else {
                iv_app_icon = (ImageView) convertView;
//                textView = (TextView) convertView;
            }

            ResolveInfo info = mApps.get(position);
            iv_app_icon.setImageDrawable(info.activityInfo.loadIcon(getPackageManager()));
//            textView.setText(info.loadLabel(getPackageManager()));
//
            return iv_app_icon;
//            return convertView;
        }

        public final int getCount() {
            return mApps.size();
        }

        public final Object getItem(int position) {
            return mApps.get(position);
        }

        public final long getItemId(int position) {
            return position;
        }
    }
}