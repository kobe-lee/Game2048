package com.jikexueyuan.game2048;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

public class GameView extends GridLayout {

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);

		initGameView();
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);

		initGameView();
	}

	public GameView(Context context) {
		super(context);

		initGameView();
	}

	private void initGameView() {
		setColumnCount(4);// ÉèÖÃ³É4ÁÐ
		setBackgroundColor(0xffbbada0);

		setOnTouchListener(new View.OnTouchListener() {

			private float startX;
			private float startY;
			private float offsetX;
			private float offsetY;

			@Override
			public boolean onTouch(View v, MotionEvent event) {

				switch (event.getAction()) {

				case MotionEvent.ACTION_DOWN:

					startX = event.getX();
					startY = event.getY();
					break;

				case MotionEvent.ACTION_UP:

					offsetX = event.getX() - startX;
					offsetY = event.getY() - startY;

					if (Math.abs(offsetX) > Math.abs(offsetY)) {
						if (offsetX < -5) {
							swipeLeft();
						} else if (offsetX > 5) {
							swipeRight();
						}
					} else {
						if (offsetY < -5) {
							swipeUp();
						} else if (offsetY > 5) {
							swipeDown();
						}
					}
					break;
				}

				return true;
			}

		});
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);

		int cardWidth = (Math.min(w, h) - 10) / 4;

		addCards(cardWidth, cardWidth);
	}

	private void addCards(int cardwidth, int cardheight) {
		Card c;

		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				c = new Card(getContext());
				c.setNum(2);
				addView(c, cardwidth, cardheight);

				cardsMap[x][y] = c;
			}
		}
	}

	private void swipeLeft() {

	}

	private void swipeRight() {

	}

	private void swipeUp() {

	}

	private void swipeDown() {

	}

	private Card[][] cardsMap = new Card[4][4];
}
