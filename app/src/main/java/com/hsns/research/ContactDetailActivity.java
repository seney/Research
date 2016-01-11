package com.hsns.research;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ContactDetailActivity extends AppCompatActivity {
    public final static String ID = "ID";
    public Contact mContact;
    private View mCircle;


    private TextView mName, mPhone, mCity, mEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);

        mContact = Contact.getItem(getIntent().getIntExtra(ID, 0));
        onCreateViewHolder();
        displayValues();
    }

    private void displayValues() {
        mName.setText(mContact.get(Contact.Field.NAME));
        GradientDrawable bgShape = (GradientDrawable) mCircle.getBackground();
        bgShape.setColor(Color.parseColor(mContact.get(Contact.Field.COLOR)));
        mPhone.setText(mContact.get(Contact.Field.PHONE));
        mEmail.setText(mContact.get(Contact.Field.EMAIL));
        mCity.setText(mContact.get(Contact.Field.CITY));
    }

    private void onCreateViewHolder() {
        mName = (TextView) findViewById(R.id.DETAILS_name);
        mPhone = (TextView) findViewById(R.id.DETAILS_phone);
        mCity = (TextView) findViewById(R.id.DETAILS_city);
        mCircle = findViewById(R.id.DETAILS_circle);
        mEmail = (TextView) findViewById(R.id.DETAILS_email);
    }
}
