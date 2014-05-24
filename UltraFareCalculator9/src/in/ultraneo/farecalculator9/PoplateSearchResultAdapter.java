package in.ultraneo.farecalculator9;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PoplateSearchResultAdapter extends ArrayAdapter<ResultSet> {
Context context;
int resource;
ArrayList<ResultSet> list;
	public PoplateSearchResultAdapter(Context context, int resource, ArrayList<ResultSet> list) {
		super(context, resource, list);
		this.context = context;
		this.resource = resource;
		this.list = list;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		if(convertView==null){
		LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = inflate.inflate(resource, null);
		}
		TextView head = (TextView) convertView.findViewById(R.id.textView1);
		TextView subhead = (TextView) convertView.findViewById(R.id.textView2);
		TextView location = (TextView) convertView.findViewById(R.id.textView3);
		ResultSet data = list.get(position);
		head.setText(data.getHead());
		subhead.setText(data.getSubhead());
		location.setText(data.getLocation());
		
		return convertView;
	}

}
