package com.example.clickerapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int clickCount = 0;
    private int pointsPerClick = 1;
    private int totalPoints = 0;
    private TextView scoreText;
    private TextView clicksText;
    private Button clickButton;
    private Button upgradeButton;
    private int upgradeLevel = 1;
    private int upgradeCost = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreText = findViewById(R.id.scoreText);
        clicksText = findViewById(R.id.clicksText);
        clickButton = findViewById(R.id.clickButton);
        upgradeButton = findViewById(R.id.upgradeButton);

        updateDisplay();

        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickCount++;
                totalPoints += pointsPerClick;
                updateDisplay();
            }
        });

        upgradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalPoints >= upgradeCost) {
                    totalPoints -= upgradeCost;
                    upgradeLevel++;
                    pointsPerClick = upgradeLevel;
                    upgradeCost = (int)(upgradeCost * 1.5);
                    updateDisplay();
                }
            }
        });
    }

    private void updateDisplay() {
        scoreText.setText("Очки: " + totalPoints);
        clicksText.setText("Кликов: " + clickCount);
        upgradeButton.setText("Улучшить (" + upgradeCost + " очков)\nУровень: " + upgradeLevel + " | +" + pointsPerClick + " за клик");
        
        if (totalPoints < upgradeCost) {
            upgradeButton.setEnabled(false);
            upgradeButton.setAlpha(0.5f);
        } else {
            upgradeButton.setEnabled(true);
            upgradeButton.setAlpha(1.0f);
        }
    }
}