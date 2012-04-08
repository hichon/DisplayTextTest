package com.example.displaytext.test;

import com.example.displaytext.DisplayTextActivity;
import com.example.displaytext.R;
import com.example.displaytext.Text;

import android.test.ActivityInstrumentationTestCase2;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DisplayTextActivityTest extends
		ActivityInstrumentationTestCase2<DisplayTextActivity> {
	private DisplayTextActivity activity;
	private EditText inputLine;
	private Button addButton;
	private Button clearButton;
	private TextView displayText;

	private String addButtonString;
	private String clearButtonString;

	public DisplayTextActivityTest() {
		super("com.example.displaytext", DisplayTextActivity.class);
	}

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		activity = getActivity();
		inputLine = (EditText) activity.findViewById(R.id.inputLine);
		addButton = (Button) activity.findViewById(R.id.addButton);
		clearButton = (Button) activity.findViewById(R.id.clearButton);
		displayText = (TextView) activity.findViewById(R.id.displayText);

		addButtonString = activity.getString(R.string.add_button);
		clearButtonString = activity.getString(R.string.clear_button);
	}

	// 初期状態のテスト
	public void testPrecondition() {
		// inputLineの初期値は空文字列とする。
		assertEquals(inputLine.getText().toString(), "");

		// addButtonの表示文字列がリソースと一致する。
		assertEquals(addButton.getText().toString(), addButtonString);

		// addButtonの初期状態はDisableとする。
		assertFalse(addButton.isEnabled());

		// clearButtonの表示文字列がリソースと一致する。
		assertEquals(clearButton.getText().toString(), clearButtonString);

		// clearButtonの初期状態はDisableとする。
		assertFalse(clearButton.isEnabled());

		// displayTextの初期値は空文字列とする。
		assertEquals(displayText.getText().toString(), "");
	}

	// inputLineに値を入力し、addButtonをクリックする。
	public void testEnabledAddButton() {
		// inputLineにフォーカスを移動する。
		getActivity().runOnUiThread(new Runnable() {
			public void run() {
				inputLine.requestFocus();
			}
		});
		getInstrumentation().waitForIdleSync();

		// addButtonはDisableとする。
		assertFalse(addButton.isEnabled());

		// inputLineに"test"と入力する。
		sendKeys(KeyEvent.KEYCODE_T);
		sendKeys(KeyEvent.KEYCODE_E);
		sendKeys(KeyEvent.KEYCODE_S);
		sendKeys(KeyEvent.KEYCODE_T);
		getInstrumentation().waitForIdleSync();

		// inputLineに"test"が設定される。
		assertEquals(inputLine.getText().toString(), "test");

		// addButtonはEnableとなる。
		assertTrue(addButton.isEnabled());

		// addButtonをクリックする。
		getActivity().runOnUiThread(new Runnable() {
			public void run() {
				addButton.performClick();
			}
		});
		getInstrumentation().waitForIdleSync();

		// inputLineが空となる。
		assertEquals(inputLine.getText().toString(), "");

		// addButtonがDisableとなる。
		assertFalse(addButton.isEnabled());

		// clearButtonがEnableとなる。
		assertTrue(clearButton.isEnabled());

		// displayTextに"test"が設定される。
		assertEquals(displayText.getText().toString(), "test");
	}
}
