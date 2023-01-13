package uz.ilhomjon.db10.models

class MyContact {
    var id:Int? = null
    var name:String? = null
    var number:String? = null

    constructor(name: String?, number: String?) {
        this.name = name
        this.number = number
    }

    constructor(id: Int?, name: String?, number: String?) {
        this.id = id
        this.name = name
        this.number = number
    }

    override fun toString(): String {
        return "MyContact(id=$id, name=$name, number=$number)"
    }


}