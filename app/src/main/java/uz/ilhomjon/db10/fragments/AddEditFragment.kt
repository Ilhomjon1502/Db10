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

class AddEditFragment : Fragment() {
    private var isEdit: Boolean = false
    private var myContact: MyContact? = null

    val binding:FragmentAddEditBinding by lazy { FragmentAddEditBinding.inflate(layoutInflater) }
    private lateinit var myDbHelper: MyDbHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        myDbHelper = MyDbHelper(binding.root.context)

        isEdit = arguments?.getBoolean("isEdit")!!
        if (isEdit){
            myContact = arguments?.getSerializable("keyMyContact") as MyContact
        }

        binding.apply {

            if (isEdit){
                edtName.setText(myContact?.name)
                edtNumber.setText(myContact?.number)
            }

            btnSave.setOnClickListener {
                if (isEdit){
                    myContact?.name = edtName.text.toString()
                    myContact?.number = edtNumber.text.toString()
                    myDbHelper.editContact(myContact!!)
                    Toast.makeText(context, "Edited", Toast.LENGTH_SHORT).show()
                }else{
                    val myContact = MyContact(
                        edtName.text.toString(),
                        edtNumber.text.toString()
                    )
                    myDbHelper.addContact(myContact)
                    Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                }
                findNavController().popBackStack()
            }
        }

        return binding.root
    }
}