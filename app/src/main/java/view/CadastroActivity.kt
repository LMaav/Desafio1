package view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.unisanta.AppLista.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import model.Destino
import model.DestinoDAOImpl

class CadastroActivity : AppCompatActivity() {

    private val dao = DestinoDAOImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Novo Destino"

        val edtNome = findViewById<EditText>(R.id.edt_nome_destino)
        val edtRegiao = findViewById<EditText>(R.id.edt_regiao_destino)
        val edtUrl = findViewById<EditText>(R.id.edt_url_destino)
        val btnSalvar = findViewById<Button>(R.id.btn_salvar_destino)

        btnSalvar.setOnClickListener {
            val nome = edtNome.text.toString()
            val regiao = edtRegiao.text.toString()
            val url = edtUrl.text.toString()

            if (nome.isNotEmpty() && regiao.isNotEmpty()) {
                val novoDestino = Destino(nome, regiao, if (url.isEmpty()) "https://www.google.com" else url)
                dao.postDestino(novoDestino)

                Toast.makeText(this, "Destino salvo!", Toast.LENGTH_SHORT).show()
                finish() // Volta para a tela anterior (Lista)
            } else {
                Toast.makeText(this, "Preencha Nome e Região", Toast.LENGTH_SHORT).show()
            }
        }
        val btnVoltar = findViewById<FloatingActionButton>(R.id.fab_Voltar)
        btnVoltar.setOnClickListener {
            val intent = Intent(this, ListaActivity::class.java)
            startActivity(intent)
            true
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}