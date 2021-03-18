package ca.javateacher.tipcalculatorver3s;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

  public static final NumberFormat sFormat = NumberFormat.getCurrencyInstance();
  private TipCalculator mTipCalculator;

  private EditText mBillEditText, mTipEditText;
  private TextView mTipTextView, mTotalTextView;
  private Button mCalculateButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mTipCalculator = new TipCalculator(0.17f, 100.0f);
    mBillEditText = findViewById(R.id.amount_bill);
    mTipEditText = findViewById(R.id.amount_tip_percent);
    mTipTextView = findViewById(R.id.amount_tip);
    mTotalTextView = findViewById(R.id.amount_total);
    mCalculateButton = findViewById(R.id.calculate_button);

    mCalculateButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        calculate();
      }
    });
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    calculate();
  }

  public void calculate() {

    String billString = mBillEditText.getText().toString();
    String tipString = mTipEditText.getText().toString();
    try {
      // convert billString and tipString to floats
      float billAmount = Float.parseFloat(billString);
      int tipPercent = Integer.parseInt(tipString);
      Log.d("calculate", "percent = " + tipPercent);
      // update the Model
      mTipCalculator.setBill(billAmount);
      mTipCalculator.setTip(.01f * tipPercent);
      // ask Model to calculate tip and total amounts
      float tip = mTipCalculator.tipAmount();
      float total = mTipCalculator.totalAmount();
      // update the View with formatted tip and total amounts
      mTipTextView.setText(sFormat.format(tip));
      mTotalTextView.setText(sFormat.format(total));
    } catch (NumberFormatException nfe) {
      Toast.makeText(this, R.string.bad_input, Toast.LENGTH_LONG).show();
    }

  }
}
