package com.jikexueyuan.game2048;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView tvScore;

	private int score = 0;

	public MainActivity() {
		mainActivity = this;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		tvScore = (TextView) findViewById(R.id.tvScore);
	}

	public void clearScore() {
		score = 0;
		showScore();
	}

	public void addScore(int s) {
		score += s;
		showScore();
	}

	public void showScore() {
		tvScore.setText(score + "");
	}

	public static MainActivity mainActivity = null;

	public static MainActivity getMainActivity() {
		return mainActivity;
	}
}
