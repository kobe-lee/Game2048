package com.jikexueyuan.game2048;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Point;
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
		setColumnCount(4);// 设置成4列
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
		
		startGame();
	}
	
	/*
	 * 添加卡片
	 */
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
	
	/*
	 * 添加随机数
	 */
	private void addRandomNum() {
		emptyPopints.clear();// 清空

		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (cardsMap[x][y].getNum() <= 0) {
					emptyPopints.add(new Point(x, y));
				}
			}
		}
		Point p = emptyPopints.remove((int) (Math.random() * emptyPopints
				.size()));
		cardsMap[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
	}
	
	/*
	 * 启动游戏
	 */
	private void startGame() {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				cardsMap[x][y].setNum(0);
			}
		}
		
		addRandomNum();
		addRandomNum();
		addRandomNum();
		addRandomNum();
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

	private List<Point> emptyPopints = new ArrayList<Point>(); // 空点的集合
}
