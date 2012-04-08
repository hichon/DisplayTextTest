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

	// ������Ԃ̃e�X�g
	public void testPrecondition() {
		// inputLine�̏����l�͋󕶎���Ƃ���B
		assertEquals(inputLine.getText().toString(), "");

		// addButton�̕\�������񂪃��\�[�X�ƈ�v����B
		assertEquals(addButton.getText().toString(), addButtonString);

		// addButton�̏�����Ԃ�Disable�Ƃ���B
		assertFalse(addButton.isEnabled());

		// clearButton�̕\�������񂪃��\�[�X�ƈ�v����B
		assertEquals(clearButton.getText().toString(), clearButtonString);

		// clearButton�̏�����Ԃ�Disable�Ƃ���B
		assertFalse(clearButton.isEnabled());

		// displayText�̏����l�͋󕶎���Ƃ���B
		assertEquals(displayText.getText().toString(), "");
	}

	// inputLine�ɒl����͂��AaddButton���N���b�N����B
	public void testEnabledAddButton() {
		// inputLine�Ƀt�H�[�J�X���ړ�����B
		getActivity().runOnUiThread(new Runnable() {
			public void run() {
				inputLine.requestFocus();
			}
		});
		getInstrumentation().waitForIdleSync();

		// addButton��Disable�Ƃ���B
		assertFalse(addButton.isEnabled());

		// inputLine��"test"�Ɠ��͂���B
		sendKeys(KeyEvent.KEYCODE_T);
		sendKeys(KeyEvent.KEYCODE_E);
		sendKeys(KeyEvent.KEYCODE_S);
		sendKeys(KeyEvent.KEYCODE_T);
		getInstrumentation().waitForIdleSync();

		// inputLine��"test"���ݒ肳���B
		assertEquals(inputLine.getText().toString(), "test");

		// addButton��Enable�ƂȂ�B
		assertTrue(addButton.isEnabled());

		// addButton���N���b�N����B
		getActivity().runOnUiThread(new Runnable() {
			public void run() {
				addButton.performClick();
			}
		});
		getInstrumentation().waitForIdleSync();

		// inputLine����ƂȂ�B
		assertEquals(inputLine.getText().toString(), "");

		// addButton��Disable�ƂȂ�B
		assertFalse(addButton.isEnabled());

		// clearButton��Enable�ƂȂ�B
		assertTrue(clearButton.isEnabled());

		// displayText��"test"���ݒ肳���B
		assertEquals(displayText.getText().toString(), "test");
	}
}
