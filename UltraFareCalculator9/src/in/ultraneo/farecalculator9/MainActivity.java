package in.ultraneo.farecalculator9;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
private static final String tag = "MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		EditText source,destination;
		Button findbtn;
		public PlaceholderFragment() {
		}

		@Override
		public void onStart() {
			findbtn = (Button) getActivity().findViewById(R.id.findbtn);
			source = (EditText) getActivity().findViewById(R.id.sourceid);
			destination = (EditText) getActivity().findViewById(R.id.destinationid);
			source.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					clickOnSource(v);
					
				}
			});
			destination.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					clickOnDestination(v);
					
				}
			});
			findbtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Toast.makeText(v.getContext(), "This is a test", Toast.LENGTH_SHORT).show();
					
				}
			});
			super.onStart();
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			
			
			return rootView;
		}
		
		public void clickOnSource(View v){
			Intent searchPlace = new Intent(getActivity(), SearchPlace.class);
			startActivityForResult(searchPlace, ApplicationConstant.SourceRequest);
		}
		public void clickOnDestination(View v){
			Intent searchPlace = new Intent(getActivity(), SearchPlace.class);
			startActivityForResult(searchPlace, ApplicationConstant.DestinationRequest);
		}

		@Override
		public void onActivityResult(int requestCode, int resultCode,
				Intent data) {
			if (resultCode == RESULT_OK) {
				if (requestCode == ApplicationConstant.SourceRequest) {
					source.setText(data.getStringExtra("data"));
				} else if (requestCode == ApplicationConstant.DestinationRequest) {
					destination.setText(data.getStringExtra("data"));
				} else {
					Log.i(tag, "Error in capturing data from onActivityResult");
				}
			} else {
				Toast.makeText(getActivity(),
						R.string.MainActivity_user_cancelled,
						Toast.LENGTH_SHORT).show();
			}
			super.onActivityResult(requestCode, resultCode, data);
		}
		
		
	}
	
	
	

}
