package edu.uga.cs.billformeal;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * MainActivity is the main activity for the BillForMeal app.  It allows the user to enter the
 * total cost of a meal and the number of diners.  The user can then select a tip percentage and
 * the app will calculate the tip amount per diner. The user can also enter a custom tip percentage
 * and the app will calculate the tip amount per diner for that percentage.
 */
public class MainActivity extends AppCompatActivity {

    private EditText totalCostEditText;
    private EditText numberOfDinersEditText;
    private TextView computedValueTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        totalCostEditText = findViewById(R.id.totalCostEditText);
        numberOfDinersEditText = findViewById(R.id.numberOfDinersEditText);

        Button tip10Button = findViewById(R.id.tip10Button);
        Button tip15Button = findViewById(R.id.tip15Button);
        Button tip20Button = findViewById(R.id.tip20Button);

        computedValueTextView = findViewById(R.id.computedValueTextView);

        // Set onClickListeners for tip buttons
        tip10Button.setOnClickListener(v -> calculateAndDisplayTip(10));
        tip15Button.setOnClickListener(v -> calculateAndDisplayTip(15));
        tip20Button.setOnClickListener(v -> calculateAndDisplayTip(20));
    }

    // Calculate the tip based on the given percentage and update the UI
    private void calculateAndDisplayTip(int tipPercentage) {
        try {
            double totalCost = Double.parseDouble(totalCostEditText.getText().toString());
            int numberOfDiners = Integer.parseInt(numberOfDinersEditText.getText().toString());

            double tipAmount = (totalCost / numberOfDiners) * (tipPercentage / 100.0);
            double roundedTipAmount = Math.round(tipAmount * 100.0) / 100.0; // Round to nearest penny

            @SuppressLint("DefaultLocale") String formattedTip =
                    String.format("$%.2f", roundedTipAmount);

            computedValueTextView.setText(formattedTip);
        } catch (NumberFormatException e) {
            computedValueTextView.setText(getString(R.string.invalid));
        }
    }

}
