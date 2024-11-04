package ru.netology.yandexmaps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.netology.yandexmaps.dto.Place
import ru.netology.yandexmaps.R
import ru.netology.yandexmaps.databinding.PlaceItemBinding

class PlacesAdapter( //Класс адаптер для списка мест
    private val listener: Listener, //Для обработки событий, связанных с элементами списка.
) : ListAdapter<Place, PlacesAdapter.PlacesViewHolder>(DiffCallback) {

    interface Listener {
        //Метод, вызываемый при щелчке на элементе списка.
        //В качестве параметра принимает объект Place, соответствующий выбранному элементу.
        fun onClick(place: Place)
        fun onDelete(place: Place) //Для удаления элемента списка.
        fun onEdit(place: Place) //Для редактирования элемента списка.
    }
//Метод onCreateViewHolder вызывается, когда требуется создать новый объект PlacesViewHolder для отображения элемента списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlacesViewHolder {
        val binding = PlaceItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false //Не добавлять автоматически.
        )

        val holder = PlacesViewHolder(binding)

        with(binding) {
            root.setOnClickListener {
                val place = getItem(holder.adapterPosition)
                listener.onClick(place)
            }
            menu.setOnClickListener {
                PopupMenu(root.context, it).apply {
                    inflate(R.menu.place_menu)

                    setOnMenuItemClickListener { item ->
                        val place = getItem(holder.adapterPosition)
                        when (item.itemId) {
                            R.id.delete -> {
                                listener.onDelete(place)
                                true
                            }
                            R.id.edit -> {
                                listener.onEdit(place)
                                true
                            }
                            else -> false
                        }
                    }

                    show()
                }
            }
        }

        return holder
    }

    override fun onBindViewHolder(holder: PlacesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PlacesViewHolder( //ViewHolder для элементов списка мест.
        private val binding: PlaceItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        //Для привязки данных места к элементам пользовательского интерфейса внутри ViewHolder.
        fun bind(place: Place) {
            with(binding) {
//свойство title.text элемента пользовательского интерфейса устанавливается в значение name из объекта Place.
                title.text = place.name
            }
        }
    }

    object DiffCallback : DiffUtil.ItemCallback<Place>() {
        override fun areItemsTheSame(oldItem: Place, newItem: Place): Boolean =
            //Если идентификаторы совпадают, метод возвращает true, в противном случае - false.
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Place, newItem: Place): Boolean =
            oldItem == newItem
    }
}
