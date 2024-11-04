package ru.netology.yandexmaps.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import ru.netology.yandexmaps.R
import ru.netology.yandexmaps.viewmodel.MapViewModel
import ru.netology.yandexmaps.dto.Place

class AddPlaceDialog : DialogFragment() {

    companion object { //Объект-компаньон для класса AddPlaceDialog.
        //Ключи для сохранения и извлечения данных из аргументов фрагмента.
        private const val ID_KEY = "ID_KEY"
        private const val LAT_KEY = "LAT_KEY"
        private const val LONG_KEY = "LONG_KEY"
        fun newInstance(lat: Double, long: Double, id: Long? = null) = AddPlaceDialog().apply {
            arguments = bundleOf(LAT_KEY to lat, LONG_KEY to long, ID_KEY to id)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val viewModel by viewModels<MapViewModel>()
        val view = AppCompatEditText(requireContext())
        return AlertDialog.Builder(requireContext())
            .setView(view)
            .setTitle(getString(R.string.enter_name))
            .setPositiveButton(android.R.string.ok) { _, _ ->
                val text = view.text?.toString()?.takeIf { it.isNotBlank() } ?: run {
                    Toast.makeText(requireContext(), "Name is empty", Toast.LENGTH_SHORT).show()
                    return@setPositiveButton
                }
                viewModel.insertPlace(
                    Place(
                        id = requireArguments().getLong(ID_KEY),
                        lat = requireArguments().getDouble(LAT_KEY),
                        long = requireArguments().getDouble(LONG_KEY),
                        name = text,
                    )
                )
            }
            .create()
    }
}