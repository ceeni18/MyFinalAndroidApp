package com.example.usingparselogin;

import com.example.pedometer_sample_project.Pedometer;
import com.parse.ParseUser;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
 
public class Welcome extends Activity {
 
    // Declare Variable
    Button logout;
    Button pedo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from singleitemview.xml
        setContentView(R.layout.welcome);
 
        // Retrieve current user from Parse.com
        ParseUser currentUser = ParseUser.getCurrentUser();
 
        // Convert currentUser into String
        String struser = currentUser.getUsername().toString();
 
        // Locate TextView in welcome.xml
        TextView txtuser = (TextView) findViewById(R.id.txtuser);
 
        // Set the currentUser String into TextView
        txtuser.setText("You are logged in as " + struser);
 
        // Locate Button in welcome.xml
        logout = (Button) findViewById(R.id.logout);
        pedo=(Button)findViewById(R.id.bEnterPedometer);
        // Logout Button Click Listener
        pedo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in =  new Intent(Welcome.this, Pedometer.class);
				startActivity(in);
			}
		});
        
        
        
        logout.setOnClickListener(new OnClickListener() {
 
            public void onClick(View arg0) {
                // Logout current user
                ParseUser.logOut();
                finish();
            }
        });
    }
}