package in.ultraneo.farecalculator9;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;

public class SearchPlaceActivity extends Activity {
private EditText searchtextbox;
private Geocoder geocoder;
private ListView list;
ArrayList<ResultSet> array;
PoplateSearchResultAdapter adapter;
private static final String tag ="SearchPlaceActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_place); 
		searchtextbox = (EditText) findViewById(R.id.searchtextbox);
		list = (ListView) findViewById(R.id.searchresult_listview);
		array = new ArrayList<ResultSet>(); 
		adapter = new PoplateSearchResultAdapter(this, R.layout.resultelement, array);
		list.setAdapter(adapter);
		geocoder = new Geocoder(this);
		searchtextbox.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
				SearchResultThread searchbackthread = new SearchResultThread();
				if(searchbackthread.getStatus()==AsyncTask.Status.RUNNING){
					searchbackthread.cancel(true);
					searchbackthread.execute(s.toString());	
					Log.i(tag, "try to cancel thread");
				}else{
				searchbackthread.execute(s.toString());
				}
				
			}
		});
	}
	
	class SearchResultThread extends AsyncTask<String, ResultSet, Void>{
		
		
		@Override
		protected void onPreExecute() {
			adapter.clear();
			super.onPreExecute();
		}
		
		@Override
		protected void onCancelled() {
			Log.i(tag, "onCancelled is Called right");
			super.onCancelled();
		}
		
		@Override
		protected void onProgressUpdate(ResultSet... values) {
			adapter.add(values[0]);
			super.onProgressUpdate(values);
		}
		
		@Override
		protected Void doInBackground(String... params) {
			try {
				List<Address> address = geocoder.getFromLocationName(params[0], 20);
				Log.i(tag, address.toString());
				//array.clear();
				for (Address address2 : address) {
					ResultSet result = new ResultSet();
					result.setResultSet(address2.getFeatureName(), address2.getCountryName(), address2.getLatitude()+","+address2.getLongitude(), address2.getLatitude(), address2.getLongitude(),address2);
					publishProgress(result);
				}
				
			} catch (IOException e) {
				Log.e(tag, "IO Exception");
			}
			return null;
		}

		
		
	}
	
}
