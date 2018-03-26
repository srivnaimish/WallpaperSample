package com.example.mentorship.view;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.MenuItem;
import android.view.ViewAnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.mentorship.AppConstants;
import com.example.mentorship.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements Toolbar.OnMenuItemClickListener {

    @BindView(R.id.search_edit)
    EditText editText;
    @BindView(R.id.search)
    ImageButton search;
    @BindView(R.id.search_box)
    CardView cardView;
    @BindView(R.id.main_background)
    ConstraintLayout background;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() > 0) {
                    if (search.getVisibility() == View.GONE) {
                        search.startAnimation(AppConstants.generateFadeInAnimator());
                        search.setVisibility(View.VISIBLE);
                    }
                } else {
                    search.startAnimation(AppConstants.generateFadeOutAnimator());
                    search.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openResultsActivity(view, editText.getText().toString());
            }
        });

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    public void openResultsActivity(View view, final String query) {
        View v = getWindow().getCurrentFocus();
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
        Intent intent = new Intent(this, ListActivity.class);
        intent.putExtra("SearchQuery", query);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void suggestionClicked(View view) {
        switch (view.getId()) {
            case R.id.cat1:
                openResultsActivity(view, "Sports");
                break;
            case R.id.cat2:
                openResultsActivity(view, "Buildings");
                break;
            case R.id.cat3:
                openResultsActivity(view, "Nature");
                break;
            case R.id.cat4:
                openResultsActivity(view, "Wildlife");
                break;
            case R.id.cat5:
                openResultsActivity(view, "Travel");
                break;
            case R.id.cat6:
                openResultsActivity(view, "Cars");
                break;
        }
    }
}
