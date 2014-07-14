package com.bignerdranch.android.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {

	private static final String TAG = "QuizActivity";
	private static final String KEY_INDEX = "index";
	
	private Button mTrueButton;
	private Button mFalseButton;
	private Button mNextButton;
	private TextView mQuestionTextView;
	private TrueFalse[] mQuestionBank = new TrueFalse[] {
		new TrueFalse(R.string.question_oceans, true),
		new TrueFalse(R.string.question_mideast, false),
		new TrueFalse(R.string.question_africa, false),
		new TrueFalse(R.string.question_americas, true),
		new TrueFalse(R.string.question_asia, true),
	};
	private int mCurrentIndex = -1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_quiz);
		Log.d(TAG, "onCreate(Bundle) called");
		
		mTrueButton = (Button)findViewById(R.id.true_button);
		mFalseButton = (Button)findViewById(R.id.false_button);
		mNextButton = (Button)findViewById(R.id.next_button);
		mQuestionTextView = (TextView)findViewById(R.id.question_text_view);
		
		if(savedInstanceState != null){
			mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, -1);
		}
		
		updateQuestion();	
		
		mTrueButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Toast.makeText(QuizActivity.this, R.string.incorrect, Toast.LENGTH_LONG).show();
				checkAnswer(true);
				
			}
		});
		
		mFalseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Toast.makeText(QuizActivity.this, R.string.correct, Toast.LENGTH_LONG).show();
				checkAnswer(false);
			}
		});
		
		mNextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				updateQuestion();
			}
		});
		
	}
	
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState){
		super.onSaveInstanceState(savedInstanceState);
		Log.i(TAG, "onSaveInstanceState");
		Log.d(TAG, Integer.toString(mCurrentIndex));
		savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}
	
	private void updateQuestion(){
		mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
		mQuestionTextView.setText(mQuestionBank[mCurrentIndex].getQuestion());
	}
	
	private void checkAnswer(boolean userPressedTrue){
		if(mQuestionBank[mCurrentIndex].isTrueQuestion() && userPressedTrue)
			Toast.makeText(QuizActivity.this, R.string.correct, Toast.LENGTH_SHORT).show();
		else if(!mQuestionBank[mCurrentIndex].isTrueQuestion() && !userPressedTrue)
			Toast.makeText(QuizActivity.this, R.string.correct, Toast.LENGTH_SHORT).show();
		else
			Toast.makeText(QuizActivity.this, R.string.incorrect, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	 public void onStart() {
	 super.onStart();
	 Log.d(TAG, "onStart() called");
	 }
	 @Override
	 public void onPause() {
	 super.onPause();
	 Log.d(TAG, "onPause() called");
	 }
	 @Override
	 public void onResume() {
	 super.onResume();
	 Log.d(TAG, "onResume() called");
	 }
	 @Override
	 public void onStop() {
	 super.onStop();
	 Log.d(TAG, "onStop() called");
	 }
	 
	 @Override
	 public void onDestroy() {
	 super.onDestroy();
	 Log.d(TAG, "onDestroy() called");
	 }


}
