package com.jikexueyuan.game2048;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout {

	public Card(Context context) {
		super(context);

		label = new TextView(context);

		LayoutParams lp = new LayoutParams(-1, -1);
		lp.setMargins(10, 10, 0, 0);

		label.setGravity(Gravity.CENTER);
		label.setTextSize(32);
		label.setBackgroundColor(0x33ffffff);

		addView(label, lp);

		setNum(0);
	}

	private int num = 0;

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;

		label.setText(num + "");
	}

	private TextView label;

	public boolean equals(Card card) {
		return getNum() == card.getNum();
	}
}
