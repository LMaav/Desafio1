package view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.unisanta.AppLista.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import model.User
import model.UserDAOImpl

class MainActivity : AppCompatActivity() {

    private val dao = UserDAOImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edtNome = findViewById<EditText>(R.id.edt_Nome)
        val edtSenha = findViewById<EditText>(R.id.edt_Senha)
        val btnLogin = findViewById<Button>(R.id.btn_Login)
        val fabCadastrar = findViewById<FloatingActionButton>(R.id.fab_Lista)

        btnLogin.setOnClickListener {
            val nomeDigitado = edtNome.text.toString()
            val catDigitada = edtSenha.text.toString()

            val usuarioExiste = dao.getUsers().any {
                it.name.equals(nomeDigitado, ignoreCase = true) && it.senha == catDigitada
            }

            if (usuarioExiste) {
                Toast.makeText(this, "Login Sucesso! Bem-vindo $nomeDigitado", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ListaActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Erro: Usuário ou senha inválidos", Toast.LENGTH_SHORT).show()
            }
        }

        fabCadastrar.setOnClickListener {
            val nome = edtNome.text.toString()
            val cat = edtSenha.text.toString()

            if (nome.isNotEmpty() && cat.isNotEmpty()) {
                dao.postUser(User(nome, cat))
                Toast.makeText(this, "Novo usuário cadastrado localmente!", Toast.LENGTH_SHORT).show()
                edtNome.text.clear()
                edtSenha.text.clear()
            }
        }
    }
}