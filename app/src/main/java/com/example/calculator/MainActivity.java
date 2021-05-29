package com.example.calculator;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.calculator.chenzhaoxuan.DiscountFragment;
import com.example.calculator.chenzhaoxuan.GeneralFragment;

public class MainActivity extends AppCompatActivity {
    private GeneralFragment generalFragment = new GeneralFragment();
    private FragmentManager fManager;
    private DiscountFragment discountFragment = new DiscountFragment();
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initGeneral();
    }

    private void init() {
        tv_title = (TextView)findViewById(R.id.title);
        findViewById(R.id.option).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupMenu(findViewById(R.id.option));
            }
        });
        fManager = getSupportFragmentManager();
    }

    private void initGeneral() {
        tv_title.setText("通用计算器");
        FragmentTransaction fTransaction = fManager.beginTransaction();
        fTransaction.replace(R.id.container, generalFragment).commit();
    }

    //弹出按钮框
    private void showPopupMenu(final View view) {
        final PopupMenu popupMenu = new PopupMenu(this, view);
        //menu 布局
        popupMenu.getMenuInflater().inflate(R.menu.menu, popupMenu.getMenu());
        //点击事件
        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_menu_general:
                    initGeneral();
                    break;
                case R.id.nav_menu_discount:
                    initDiscount();
                    break;
                default:
                    break;
            }
            return false;
        });
        //显示菜单，不要少了这一步
        popupMenu.show();
    }

    private void initDiscount() {
        tv_title.setText("折扣计算器");
        FragmentTransaction fTransaction = fManager.beginTransaction();
        fTransaction.replace(R.id.container, discountFragment).commit();
    }
}