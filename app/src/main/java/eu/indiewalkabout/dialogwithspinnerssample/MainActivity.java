package eu.indiewalkabout.dialogwithspinnerssample;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }




    // ---------------------------------------------------------------------------------------------
    //                                          MENU STUFF
    // ---------------------------------------------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.open_dialog:
                showDialog();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void showDialog(){
        AlertDialog.Builder  builder = new AlertDialog.Builder(MainActivity.this);
        View view = getLayoutInflater().inflate(R.layout.dialog_with_spinners, null);
        builder.setTitle("Spinners");

        // spinner 1
        final Spinner spinner_01 = (Spinner) view.findViewById(R.id.spinner_01);
        ArrayAdapter<String> adapter_01 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.num_list));
        adapter_01.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_01.setAdapter(adapter_01);


        // spinner 2
        final Spinner spinner_02 = (Spinner) view.findViewById(R.id.spinner_02);
        ArrayAdapter<String> adapter_02 = new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_spinner_item,
                getResources().getStringArray(R.array.letter_list));
        adapter_02.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner_02.setAdapter(adapter_02);


        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!spinner_01.getSelectedItem().toString().equalsIgnoreCase("Choose a number : ")){
                    Toast.makeText(MainActivity.this,
                            spinner_01.getSelectedItem().toString(),
                            Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }

                if(!spinner_02.getSelectedItem().toString().equalsIgnoreCase("Choose a letter : ")) {
                    Toast.makeText(MainActivity.this,
                            spinner_02.getSelectedItem().toString(),
                            Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });


        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.show();

    }

}
