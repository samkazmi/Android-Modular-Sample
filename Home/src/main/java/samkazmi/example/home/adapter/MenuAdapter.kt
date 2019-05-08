package samkazmi.example.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import samkazmi.example.base.RecyclerViewCallback
import samkazmi.example.home.data.Menu
import samkazmi.example.home.databinding.MenuItemBinding

class MenuAdapter(private val callback: RecyclerViewCallback,private var menuList: List<Menu>) : RecyclerView.Adapter<MenuAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        return ViewHolder(
           MenuItemBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        viewHolder.bind(menuList[i])
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    fun setData(menus: List<Menu>) {
        this.menuList = menus
        notifyDataSetChanged()
    }

    inner class ViewHolder internal constructor(private val binding: MenuItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.callback = callback
            binding.executePendingBindings()
        }

        internal fun bind(menu: Menu) {
            binding.position = adapterPosition
            binding.item = menu
            binding.executePendingBindings()
        }
    }
}
