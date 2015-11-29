package com.jikexueyuan.game2048;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

public class Card extends FrameLayout {

	public Card(Context context) {
		super(context);

		label = new TextView(context);

		LayoutParams lp = new LayoutParams(-1, -1);
		label.setTextSize(32);
		addView(label, lp);

		setNum(0);
	}

	private int num;

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
