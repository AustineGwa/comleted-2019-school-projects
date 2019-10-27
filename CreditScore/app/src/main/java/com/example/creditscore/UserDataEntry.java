package com.example.creditscore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.creditscore.UtilityFunc.CreditScoreCalc;
import com.example.creditscore.models.UserCreditData;
import com.google.firebase.auth.FirebaseAuth;

public class UserDataEntry extends AppCompatActivity {
    EditText salary,currentDebt, numLoans, lastLoanPaid;
    Button calculateLoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_data_entry);

        salary = findViewById(R.id.tx_salary);
        currentDebt = findViewById(R.id.tx_current_debt);
        numLoans = findViewById(R.id.num_text);
        lastLoanPaid = findViewById(R.id.num_last_loan_paid);

        calculateLoan = findViewById(R.id.bt_calculate);
    }

    @Override
    protected void onStart() {
        super.onStart();

        calculateLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mSalary = Integer.parseInt(salary.getText().toString());
                int mCurrentDebt = Integer.parseInt(salary.getText().toString());
                int mNumLoans = Integer.parseInt(numLoans.getText().toString());
                int mLastLoanPaid = Integer.parseInt(lastLoanPaid.getText().toString());


                UserCreditData userCreditData = new UserCreditData(mSalary,mCurrentDebt,mNumLoans,mLastLoanPaid);
                CreditScoreCalc creditScoreCalc = new CreditScoreCalc();
                int creditScore = creditScoreCalc.calculateCreditScore( userCreditData);
                String rating = creditScoreCalc.getRating(creditScore);

                Intent intent = new Intent(getApplicationContext(),CreditScoreView.class);
                intent.putExtra("rating", rating);

                String score = String.valueOf(creditScore);
                intent.putExtra("score",score);
                startActivity(intent);

               // Toast.makeText(getApplicationContext(),"Credit Score "+creditScore + " Rating "+rating, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.nav_logout){
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),Splash.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
