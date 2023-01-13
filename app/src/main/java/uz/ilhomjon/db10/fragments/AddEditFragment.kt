package uz.ilhomjon.db10.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import uz.ilhomjon.db10.R
import uz.ilhomjon.db10.databinding.FragmentAddEditBinding
import uz.ilhomjon.db10.db.MyDbHelper
import uz.ilhomjon.db10.models.MyContact

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddEditFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddEditFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    val binding:FragmentAddEditBinding by lazy { FragmentAddEditBinding.inflate(layoutInflater) }
    private lateinit var myDbHelper: MyDbHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        myDbHelper = MyDbHelper(binding.root.context)

        binding.apply {
            btnSave.setOnClickListener {
                val myContact = MyContact(
                    edtName.text.toString(),
                    edtNumber.text.toString()
                )
                myDbHelper.addContact(myContact)
                Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                findNavController().popBackStack()
            }
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddEditFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddEditFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}