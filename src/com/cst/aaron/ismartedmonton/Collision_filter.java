package com.cst.aaron.ismartedmonton;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.Spinner;

public class Collision_filter extends Fragment implements OnClickListener, OnItemSelectedListener{

	private CheckedTextView checkedTextView_mvci,checkedTextView_cad,checkedTextView_fatal_injury,checkedTextView_fatal_major,checkedTextView_odds;
	private Spinner spinner_division;
	private Button button_ok;
	private int spinner_position=0;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView=inflater.inflate(R.layout.collision_filter, container, false);
		checkedTextView_mvci=(CheckedTextView) rootView.findViewById(R.id.checkedTextView_mvci);
		checkedTextView_cad=(CheckedTextView)rootView.findViewById(R.id.checkedTextView_cad);
		checkedTextView_fatal_injury=(CheckedTextView)rootView.findViewById(R.id.checkedTextView_fatal_injury);
		checkedTextView_fatal_major=(CheckedTextView)rootView.findViewById(R.id.checkedTextView_fatal_major);
		checkedTextView_odds=(CheckedTextView)rootView.findViewById(R.id.checkedTextView_odds);
		
		checkedTextView_mvci.setOnClickListener(this);
		checkedTextView_cad.setOnClickListener(this);
		checkedTextView_fatal_injury.setOnClickListener(this);
		checkedTextView_fatal_major.setOnClickListener(this);
		checkedTextView_odds.setOnClickListener(this);
		
		spinner_division=(Spinner)rootView.findViewById(R.id.spinner_division);
		ArrayAdapter< CharSequence> adapter_spinner=ArrayAdapter.createFromResource(getActivity(), R.array.collision_filter_spinner, android.R.layout.simple_spinner_dropdown_item);
		adapter_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_division.setAdapter(adapter_spinner);
		spinner_division.setOnItemSelectedListener(this);
		
		button_ok=(Button)rootView.findViewById(R.id.Collision_filter_ok_button);
		button_ok.setOnClickListener(this);
		
		return rootView;
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.checkedTextView_cad:
		case R.id.checkedTextView_fatal_injury:
		case R.id.checkedTextView_fatal_major:
		case R.id.checkedTextView_mvci:
		case R.id.checkedTextView_odds:
			CheckedTextView checkedTextView=(CheckedTextView)v;
			if (checkedTextView.isChecked()) {
				checkedTextView.setChecked(false);
			}
			else {
				checkedTextView.setChecked(true);
			}
			break;
		case R.id.Collision_filter_ok_button:
			saveSettings();
		//	FragmentTransaction fTransaction= getActivity().getFragmentManager().beginTransaction();
			FragmentTransaction fTransaction=getActivity().getSupportFragmentManager().beginTransaction();
			Collision_chart fragment=new Collision_chart();
			fTransaction.replace(R.id.parent_relativelayout, fragment);
			fTransaction.commit();
			break;
		default:
			break;
		}
	}

	public void saveSettings(){
		SharedPreferences sharedPreferences=getActivity().getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor editor=sharedPreferences.edit();
		editor.putBoolean("mvci", checkedTextView_mvci.isChecked());
		editor.putBoolean("cad", checkedTextView_cad.isChecked());
		editor.putBoolean("fatal_injury", checkedTextView_fatal_injury.isChecked());
		editor.putBoolean("fatal_major", checkedTextView_fatal_major.isChecked());
		editor.putBoolean("odds", checkedTextView_odds.isChecked());
		editor.putInt("division", spinner_position);
		editor.commit();
	}
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// TODO Auto-generated method stub
		//	String itemString =parent.getItemAtPosition(position).toString();
		spinner_position=position;
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
}
