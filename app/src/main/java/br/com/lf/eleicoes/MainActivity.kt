package br.com.lf.eleicoes

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btVotar = findViewById<Button>(R.id.btVotar)
        btVotar.setOnClickListener{
            val rdGroup = findViewById<RadioGroup>(R.id.rdOpcoes)

            if(rdGroup.getCheckedRadioButtonId() == -1){
                //Toast.makeText(this@MainActivity, "Favor selecionar um opção!", Toast.LENGTH_SHORT).show()
                AlertDialog.Builder(this@MainActivity)
                    .setTitle("Ops...")
                    .setMessage("Favor selecionar um opção")
                    .setPositiveButton("OK", {dialog, i -> dialog.dismiss() })
                    .show()
            }else{
                val activityIntent = Intent(this@MainActivity, Activity2::class.java)
                activityIntent.putExtra("paramVoto", rdGroup.getCheckedRadioButtonId().toString())
                startActivity(activityIntent)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        //Limpa os campos
        val rdGroup = findViewById<RadioGroup>(R.id.rdOpcoes)
        rdGroup.clearCheck()

    }
}
