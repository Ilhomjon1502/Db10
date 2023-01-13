package uz.ilhomjon.db10.db

import uz.ilhomjon.db10.models.MyContact

interface MyDbHelperInterface {

    fun addContact(myContact: MyContact)
    fun getAllContacts():List<MyContact>
}