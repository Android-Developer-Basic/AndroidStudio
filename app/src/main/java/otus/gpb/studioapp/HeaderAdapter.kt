package otus.gpb.studioapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import otus.gpb.mylibrary.Employee

class HeaderAdapter : RecyclerView.Adapter<HeaderAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(
        LayoutInflater.from(parent.context).inflate(R.layout.viewholder_header, parent, false)
    )

    override fun getItemCount(): Int = 1

    override fun onBindViewHolder(holder: Holder, position: Int) = Unit

    class Holder(itemView: View) : ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.name)
        private val salary: TextView = itemView.findViewById(R.id.salary)

        init {
            name.text = itemView.context.getString(R.string.name)
            salary.text = itemView.context.getString(R.string.salary)
        }
    }
}