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
		setColumnCount(4);// ���ó�4��
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
	 * ��ӿ�Ƭ
	 */
	private void addCards(int cardwidth, int cardheight) {
		Card c;

		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				c = new Card(getContext());
				// c.setNum(2);
				addView(c, cardwidth, cardheight);

				cardsMap[x][y] = c;
			}
		}
	}

	/*
	 * ��������
	 */
	private void addRandomNum() {
		emptyPopints.clear();// ���

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
	 * ������Ϸ
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
		addRandomNum();
		addRandomNum();
		addRandomNum();
		addRandomNum();
	}

	private void swipeLeft() {
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				for (int x1 = x + 1; x1 < 4; x1++) {
					if (cardsMap[x1][y].getNum() > 0) {
						if (cardsMap[x][y].getNum() <= 0) {
							cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
							cardsMap[x1][y].setNum(0);

							x--;
							break;
						} else if (cardsMap[x][y].equals(cardsMap[x1][y])) {
							cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
							cardsMap[x1][y].setNum(0);
							break;
						}
					}
				}
			}
		}
	}

	private void swipeRight() {
		for (int y = 0; y < 4; y++) {
			for (int x = 3; x >= 0; x--) {
				for (int x1 = x - 1; x1 >= 0; x1--) {
					if (cardsMap[x1][y].getNum() > 0) {
						if (cardsMap[x][y].getNum() <= 0) {
							cardsMap[x][y].setNum(cardsMap[x1][y].getNum());
							cardsMap[x1][y].setNum(0);

							x++;
							break;
						} else if (cardsMap[x][y].equals(cardsMap[x1][y])) {
							cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
							cardsMap[x1][y].setNum(0);
							break;
						}
					}
				}
			}
		}
	}

	private void swipeUp() {
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				for (int y1 = y + 1; y1 < 4; y1++) {
					if (cardsMap[x][y1].getNum() > 0) {
						if (cardsMap[x][y].getNum() <= 0) {
							cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
							cardsMap[x][y1].setNum(0);

							y--;
							break;
						} else if (cardsMap[x][y].equals(cardsMap[x][y1])) {
							cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
							cardsMap[x][y1].setNum(0);
							break;
						}
					}
				}
			}
		}
	}

	private void swipeDown() {
		for (int x = 0; x < 4; x++) {
			for (int y = 3; y >= 0; y--) {
				for (int y1 = y - 1; y1 >= 0; y1--) {
					if (cardsMap[x][y1].getNum() > 0) {
						if (cardsMap[x][y].getNum() <= 0) {
							cardsMap[x][y].setNum(cardsMap[x][y1].getNum());
							cardsMap[x][y1].setNum(0);

							y++;
							break;
						} else if (cardsMap[x][y].equals(cardsMap[x][y1])) {
							cardsMap[x][y].setNum(cardsMap[x][y].getNum() * 2);
							cardsMap[x][y1].setNum(0);
							break;
						}
					}
				}
			}
		}
	}

	private Card[][] cardsMap = new Card[4][4];

	private List<Point> emptyPopints = new ArrayList<Point>(); // �յ�ļ���
}
