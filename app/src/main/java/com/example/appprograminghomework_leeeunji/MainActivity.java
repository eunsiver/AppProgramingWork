package com.example.appprograminghomework_leeeunji;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView tvName, tvEmail;
    ImageButton button1;
    EditText dlgEdtName, dlgEdtEmail;
    TextView toastText;
    View dialogView, toastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("여러 기능의 앱");
        //
        tvName = (TextView) findViewById(R.id.tvName);
        button1 = (ImageButton) findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                dialogView = (View) View.inflate(MainActivity.this,
                        R.layout.dialog1, null);

                AlertDialog.Builder dlg = new AlertDialog.Builder(
                        MainActivity.this);
                dlg.setTitle("사용자 정보 입력");
                dlg.setView(dialogView);
                dlg.setPositiveButton("확인",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                dlgEdtName = (EditText) dialogView
                                        .findViewById(R.id.dlgEdt1);

                                tvName.setText("반갑습니다. "+dlgEdtName.getText().toString()+"님!");

                                // dlgLayout = null;
                            }
                        });

                dlg.setNegativeButton("취소",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                Toast toast = new Toast(MainActivity.this);

                                toastView = (View) View.inflate(
                                        MainActivity.this,
                                        R.layout.toast1, null);
                                toastText = (TextView) toastView
                                        .findViewById(R.id.toastText1);
                                toastText.setText("취소했습니다");
                                toast.setView(toastView);
                                toast.show();
                            }
                        });
                dlg.show();

            }
        });

        Button videoPlayer=(Button)findViewById(R.id.videoPlayer);
        videoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getApplicationContext(),Video.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"동영상을 선택하였습니다.",Toast.LENGTH_SHORT).show();
            }
        });

        SeekBar seekBar=findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                setBrightness(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        final ArrayList<String> midList=new ArrayList<String>();
        ListView list=(ListView)findViewById(R.id.listV);
        final ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,midList);
        list.setAdapter(adapter);

        final EditText editText=(EditText)findViewById(R.id.edtItem);
        Button btnAdd=(Button)findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                midList.add(editText.getText().toString());
                adapter.notifyDataSetChanged();
                editText.setText("");
            }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                midList.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });

    }
    private void setBrightness(int value){
        if(value<10){
            value=10;
        }
        else if(value>100)value=100;
        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.screenBrightness=(float)value/100;
        getWindow().setAttributes(params);
    }

}