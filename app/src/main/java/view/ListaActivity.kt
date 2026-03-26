package view

import adapter.DestinoAdapter
import android.content.Intent
import android.os.Bundle
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.unisanta.AppLista.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import model.DestinoDAOImpl

class ListaActivity : AppCompatActivity() {

    private val dao = DestinoDAOImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)

        val rvDestinos = findViewById<RecyclerView>(R.id.rv_Destinos)

        val listaAtualizada = dao.getDestinos().toMutableList()

        rvDestinos.layoutManager = LinearLayoutManager(this)
        rvDestinos.adapter = DestinoAdapter(listaAtualizada)
        val bnvMenu = findViewById<BottomNavigationView>(R.id.bnv_Menu)

        bnvMenu.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.nav_home -> {
                    finish()
                    true
                }
                R.id.nav_add -> {
                    val intent = Intent(this, CadastroActivity::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }
    }
    override fun onResume() {
        super.onResume()
        val rvDestinos = findViewById<RecyclerView>(R.id.rv_Destinos)
        val listaAtualizada = dao.getDestinos().toMutableList()
        rvDestinos.adapter = DestinoAdapter(listaAtualizada)
    }
    private fun abrirPopup(anchor: android.view.View) {
        val popup = PopupMenu(this, anchor)

        popup.menu.add("Livro/Filme")
        popup.menu.add("Alimento")
        popup.menu.add("Ferramenta")
        popup.menu.add("Outro")

        popup.setOnMenuItemClickListener {
            Toast.makeText(this, "Clicou em: ${it.title}", Toast.LENGTH_SHORT).show()
            true
        }

        popup.show()
    }
}