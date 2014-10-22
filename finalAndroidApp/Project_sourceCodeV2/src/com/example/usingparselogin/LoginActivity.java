package com.example.usingparselogin;

import java.util.Locale;

import com.example.usingparselogin.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class LoginActivity extends Activity {
    // Declare Variables
    Button loginbutton;
    Button signup;
    String usernametxt;
    String passwordtxt;
    EditText password;
    EditText username;
    Button fgtpwd;
 
    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Get the view from main.xml
        setContentView(R.layout.loginsignup);
        // Locate EditTexts in main.xml
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
 
        // Locate Buttons in main.xml
        loginbutton = (Button) findViewById(R.id.login);
        signup = (Button) findViewById(R.id.signup);
        fgtpwd= (Button)findViewById(R.id.btn_ForgetPass);
        // Login Button Click Listener
        loginbutton.setOnClickListener(new OnClickListener() {
 
            public void onClick(View arg0) {
            	attemptLogin();
            }
        });
       
        signup.setOnClickListener(new  OnClickListener(){
        	public void onClick(View arg0) {
        		Intent in =  new Intent(LoginActivity.this,SignUp.class);
				startActivity(in);
            }
        });
        
        fgtpwd.setOnClickListener(new  OnClickListener(){
        	public void onClick(View arg0) {
        		Intent in =  new Intent(LoginActivity.this,ForgetParsePassword.class);
				startActivity(in);
            }
        });
        
    }
    
        
        public void attemptLogin() {

    		clearErrors();

                // Retrieve the text entered from the EditText
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();
 
                boolean cancel = false;
        		View focusView = null;

        		// Check for a valid password.
        		if (TextUtils.isEmpty(passwordtxt)) {
        			password.setError(getString(R.string.error_field_required));
        			focusView = password;
        			cancel = true;
        		} else if (password.length() < 4) {
        			password.setError(getString(R.string.error_invalid_password));
        			focusView =password;
        			cancel = true;
        		}

        		// Check for a valid email address.
        		if (TextUtils.isEmpty(usernametxt)) {
        			username.setError(getString(R.string.error_field_required));
        			focusView = username;
        			cancel = true;
        		}

        		if (cancel) {
        			// There was an error; don't attempt login and focus the first
        			// form field with an error.
        			focusView.requestFocus();
        		} else {
        			// perform the user login attempt.
        			login(usernametxt.toLowerCase(Locale.getDefault()), passwordtxt);
        		}
        }
        	
    	private void login(String lowerCase, String password) {
    		// TODO Auto-generated method stub
    		ParseUser.logInInBackground(lowerCase, password, new LogInCallback() {
    			@Override
    			public void done(ParseUser user, ParseException e) {
    				// TODO Auto-generated method stub
    				if(e == null)
    					loginSuccessful();
    				else
    					loginUnSuccessful();
    			}
    		});

    	}

    			protected void loginSuccessful() {
    				// TODO Auto-generated method stub
    				Intent in =  new Intent(LoginActivity.this,MainActivity.class);
    				startActivity(in);
    			}
    			protected void loginUnSuccessful() {
    				// TODO Auto-generated method stub
    				Toast.makeText(getApplicationContext(), "", Toast.LENGTH_SHORT).show();
    				showAlertDialog(LoginActivity.this,"Login", "Username or Password is invalid.", false);
    			}

    			private void clearErrors(){
    				username.setError(null);
    				password.setError(null);
    		}
    			
    			@SuppressWarnings("deprecation")
    			public void showAlertDialog(Context context, String title, String message, Boolean status) {
    				AlertDialog alertDialog = new AlertDialog.Builder(context).create();

    				// Setting Dialog Title
    				alertDialog.setTitle(title);

    				// Setting Dialog Message
    				alertDialog.setMessage(message);

    				// Setting alert dialog icon
    				alertDialog.setIcon(R.drawable.fail);

    				// Setting OK Button
    				alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
    					public void onClick(DialogInterface dialog, int which) {
    					}
    				});

    				// Showing Alert Message
    				alertDialog.show();
    			}
    		}
    
 
   /*                         
        // Sign up Button Click Listener
        signup.setOnClickListener(new OnClickListener() {
 
            public void onClick(View arg0) {
                // Retrieve the text entered from the EditText
                usernametxt = username.getText().toString();
                passwordtxt = password.getText().toString();
 
                // Force user to fill up the form
                if (usernametxt.equals("") && passwordtxt.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "Please complete the sign up form",
                            Toast.LENGTH_LONG).show();
 
                } else {
                    // Save new user data into Parse.com Data Storage
                    ParseUser user = new ParseUser();
                    user.setUsername(usernametxt);
                    user.setPassword(passwordtxt);
                    user.signUpInBackground(new SignUpCallback() {
                        public void done(ParseException e) {
                            if (e == null) {
                                // Show a simple Toast message upon successful registration
                                Toast.makeText(getApplicationContext(),
                                        "Successfully Signed up, please log in.",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        "Sign up Error", Toast.LENGTH_LONG)
                                        .show();
                            }
                        }
                    });
                }
 
            }
        });
 
    }
}
        */