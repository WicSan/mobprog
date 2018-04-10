package ch.hslu.mobprog.intentwigdet;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ResolveInfoAdapter extends ArrayAdapter<ResolveInfo> {
    public ResolveInfoAdapter(@NonNull Context context, @NonNull List<ResolveInfo> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ResolveInfo info = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.simple_text, parent, false);
        }

        TextView textView = (TextView) convertView.findViewById(R.id.info);
        textView.setText(info.activityInfo.name);
        return convertView;
    }
}
