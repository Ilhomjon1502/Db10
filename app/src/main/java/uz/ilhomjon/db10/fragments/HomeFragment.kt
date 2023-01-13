package uz.ilhomjon.db10.fragments

import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.ilhomjon.db10.R
import uz.ilhomjon.db10.adapters.RvAction
import uz.ilhomjon.db10.adapters.RvAdapter
import uz.ilhomjon.db10.databinding.FragmentHomeBinding
import uz.ilhomjon.db10.db.MyDbHelper
import uz.ilhomjon.db10.models.MyContact

class HomeFragment : Fragment(), RvAction {
    val binding: FragmentHomeBinding by lazy { FragmentHomeBinding.inflate(layoutInflater) }
    private lateinit var rvAdapter: RvAdapter
    private lateinit var myDbHelper: MyDbHelper
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        setHasOptionsMenu(true)

        myDbHelper = MyDbHelper(binding.root.context)
        rvAdapter = RvAdapter(myDbHelper.getAllContacts() as ArrayList, this)
        binding.rv.adapter = rvAdapter

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.my_option_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        findNavController().navigate(R.id.addEditFragment, bundleOf("isEdit" to false))
        return super.onOptionsItemSelected(item)
    }

    override fun menuClicked(myContact: MyContact, position: Int, imageView: ImageView) {
        val popupMenu = PopupMenu(context, imageView)
        popupMenu.inflate(R.menu.menu_popup_item)

        popupMenu.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_delete->{
                    Toast.makeText(context, "O'chiramiz", Toast.LENGTH_SHORT).show()
                    myDbHelper.deleteContact(myContact)
                    rvAdapter.list.remove(myContact)
                    rvAdapter.notifyItemRemoved(position)
                }
                R.id.menu_edit->{
                    Toast.makeText(context, "Menu edit", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.addEditFragment, bundleOf("isEdit" to true, "keyMyContact" to myContact))
                }
            }
            true
        }

        popupMenu.show()
    }

}