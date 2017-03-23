package com.nineinfosys.andrioddev5.personalloancalculator.Amortization;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;


import com.nineinfosys.andrioddev5.personalloancalculator.R;

import java.util.ArrayList;
import java.util.List;

public class LoanAmortization extends AppCompatActivity {
    RecyclerView recyclerViewAmortization;
    AmortizationAdapter amortizationAdapter;
    int i=1;
    double monthlypayment,monthlyRate,loanAmount,loanPeriod;
    List<AmortizationCalculation.AmortizationResults> results = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_amortization);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       // getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Loan Amortization");

        recyclerViewAmortization=(RecyclerView) findViewById(R.id.recyclerViewAmortization);
        recyclerViewAmortization.setHasFixedSize(true);
        recyclerViewAmortization.setLayoutManager(new GridLayoutManager(this,1));

        //get value through intent from Loancalculator activity
        monthlypayment = getIntent().getExtras().getDouble("Monthlypayment");
        monthlyRate = getIntent().getExtras().getDouble("Rate");
        loanAmount = getIntent().getExtras().getDouble("loanAmount");
        loanPeriod = getIntent().getExtras().getDouble("loanPeriod");

        //calculation method call for amortization
      loanAmortizationCalcualtion();

    }
   // AmortizationCalculation.AmortizationResults item;
    private void loanAmortizationCalcualtion() {

        AmortizationCalculation iA = new AmortizationCalculation(loanAmount, monthlyRate, loanPeriod);
        results = iA.calculateAmortization();

        amortizationAdapter = new AmortizationAdapter(this,results);
        recyclerViewAmortization.setAdapter(amortizationAdapter);




    }
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
           // Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
           // Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }
}
