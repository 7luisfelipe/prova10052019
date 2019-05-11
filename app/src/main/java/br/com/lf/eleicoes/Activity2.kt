package br.com.lf.eleicoes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity;
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_2.*

class Activity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_2)

        val btVotar = findViewById<Button>(R.id.btVotar)
        btVotar.setOnClickListener{
            val rdGroup = findViewById<RadioGroup>(R.id.rdOpcoes)

            if(rdGroup.getCheckedRadioButtonId() == -1){
                val msg = "Obrigado por participar da entrevista. Seu voto foi no candidato: " + intent.getStringExtra("paramVoto")
                Toast.makeText(this@Activity2, msg, Toast.LENGTH_SHORT).show()
            }else{
                //Manda o email
                val addresses = "intensaovotos@teste.com.br";
                val subject = "intensaovotos@teste.com.br";

                val intent = Intent(Intent.ACTION_SENDTO)
                intent.data = Uri.parse("mailto:")
                intent.putExtra(Intent.EXTRA_EMAIL, addresses)
                intent.putExtra(Intent.EXTRA_SUBJECT, subject)

                //Adiciona o voto do eleitor no corpo do email
                intent.putExtra(android.content.Intent.EXTRA_TEXT,
                    intent.getStringExtra("paramVoto"));

                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                }

                val activityIntent = Intent(this@Activity2, MainActivity::class.java)
                startActivity(activityIntent)
            }
        }
    }

    //Limpa os campos
    override fun onStop() {
        super.onStop()
        val edtIdade = findViewById<EditText>(R.id.edtIdade)
        val rgSexo = findViewById<RadioGroup>(R.id.rgSexo)
        val rgEsc = findViewById<RadioGroup>(R.id.rgEscolaridade)
        val rgCasa = findViewById<RadioGroup>(R.id.rgCasaPropria)

        edtIdade.setText("")
        rgSexo.clearCheck()
        rgEsc.clearCheck()
        rgCasa.clearCheck()
    }
}
