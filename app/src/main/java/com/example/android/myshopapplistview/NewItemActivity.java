package com.example.android.myshopapplistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.math.BigDecimal;

public class NewItemActivity extends AppCompatActivity {

    EditText nameText;
    EditText priceText;
    EditText descriptionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        ///link with layout
        nameText = findViewById(R.id.nameText);
        priceText = findViewById(R.id.priceText);
        descriptionText = findViewById(R.id.descriptionText);



    }

    public void Confirm(View view){
        //build new item
        Item newItem = new Item(new BigDecimal(priceText.getText().toString()), nameText.getText().toString(), descriptionText.getText().toString());

        Intent dataBack = new Intent();

        dataBack.putExtra("itemKey", newItem);
        //when we close activity, say was ok(not error) and give this intent back
        //to anyone waiting for results
        setResult(RESULT_OK, dataBack);

        finish();

    }

    public void Cancel(View view){
        finish();
    }

}
