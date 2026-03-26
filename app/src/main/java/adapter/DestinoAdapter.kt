package adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.unisanta.AppLista.R
import model.Destino
import model.DestinoDAOImpl
import view.WebActivity

class DestinoAdapter(private var lista: MutableList<Destino>) : RecyclerView.Adapter<DestinoAdapter.ViewHolder>() {

    private val dao = DestinoDAOImpl()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nome: TextView = view.findViewById(R.id.txt_nome_destino)
        val regiao: TextView = view.findViewById(R.id.txt_regiao_destino)
        val btnExplorar: Button = view.findViewById(R.id.btn_explorar)
        val btnExcluir: Button = view.findViewById(R.id.btn_excluir)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val destino = lista[position]
        holder.nome.text = destino.nome
        holder.regiao.text = destino.regiao

        holder.btnExplorar.setOnClickListener {
            val intent = Intent(holder.itemView.context, WebActivity::class.java)
            intent.putExtra("URL_DESTINO", destino.url)
            holder.itemView.context.startActivity(intent)
        }


        holder.btnExcluir.setOnClickListener {
            dao.excluirDestino(position)
            lista = dao.getDestinos().toMutableList()
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = lista.size
}