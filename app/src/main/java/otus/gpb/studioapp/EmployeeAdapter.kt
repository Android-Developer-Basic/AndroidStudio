package otus.gpb.studioapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import otus.gpb.mylibrary.Employee


class EmployeeAdapter : ListAdapter<Employee, EmployeeAdapter.Holder>(FlowerDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder = Holder(
        LayoutInflater.from(parent.context).inflate(R.layout.viewholder_employee, parent, false)
    )

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    class Holder(itemView: View) : ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.name)
        private val salary: TextView = itemView.findViewById(R.id.salary)

        fun bind(employee: Employee) {
            name.text = employee.name
            salary.text = employee.salary.toString()
        }
    }
}

object FlowerDiffCallback : DiffUtil.ItemCallback<Employee>() {
    override fun areItemsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Employee, newItem: Employee): Boolean {
        return oldItem == newItem
    }
}