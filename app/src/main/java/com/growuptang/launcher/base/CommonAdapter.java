package com.growuptang.launcher.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;
    protected int mLayoutId;
    protected int mPosition;

    public CommonAdapter(Context context, List<T> datas, int layoutId) {
        super();
        this.mContext = context;
        this.mDatas = datas;
        mLayoutId = layoutId;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
//         Log.d("dataSize", mDatas.size() + "");
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addDatas(List<T> mList) {
        if (mList != null && mList.size() > 0) {
            mDatas.addAll(mList);
        }
        notifyDataSetChanged();
    }

    public void addData(T item) {
        if (item != null) {
            mDatas.add(item);
            notifyDataSetChanged();
        }
    }

    public void refreshDatas(List<T> mList) {
        mDatas.clear();
        if (mList != null && mList.size() > 0) {
            mDatas.addAll(mList);
        }
        notifyDataSetChanged();
    }

    public void clearDatas() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //获取convertView item view
        ViewHolder holder = ViewHolder.get(mContext, convertView, parent, mLayoutId, position);

        convert(holder, getItem(position), position);

        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder, T item, int position);
}