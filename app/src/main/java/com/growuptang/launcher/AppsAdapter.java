package com.growuptang.launcher;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.widget.ImageView;

import com.growuptang.launcher.base.CommonAdapter;
import com.growuptang.launcher.base.ViewHolder;

import java.util.List;

public class AppsAdapter extends CommonAdapter<ResolveInfo> {

    private ImageView imageView;
    public AppsAdapter(Context context, List<ResolveInfo> datas, int layoutId) {
        super(context, datas, layoutId);
    }

    @Override
    public void convert(ViewHolder holder, ResolveInfo item, int position) {
        PackageManager pm = null;
        String appName = item.loadLabel(pm).toString();

        holder.setText(R.id.tv_app_name, appName);
//        Picasso.with(mContext).load(String.valueOf(item.activityInfo.loadIcon(pm))).into((ImageView) holder.getView(R.id.iv_app_icon));

        imageView = holder.getView(R.id.iv_app_icon);
//        imageView.setImageDrawable(item.activityInfo.loadIcon(getPackageManager()));
    }
}
