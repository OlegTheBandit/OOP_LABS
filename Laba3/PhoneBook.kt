enum class ContactType(contactType:String) {
    MOBILE_PHONE("Mobile phone"),
    WORK_PHONE("Work phone"),
    HOME_PHONE("Home phone"),
    EMAIL("E-mail")
}

data class Person (private var name:String,private var surname: String) {
    fun getName():String{
        return this.name
    }
    fun getSurname():String{
        return this.surname
    }
}

data class Contact (private var type: ContactType,private var value: String) {
    fun getType():ContactType{
        return this.type
    }
    fun getValue():String{
        return this.value
    }

}


class PhoneBook {
    private var contactsList = HashMap<Person, MutableList<Contact>>()

    fun addContact (name: String, surname: String, type: ContactType, value: String){

        if(value == "" || name == "" || surname == "")
            throw IllegalArgumentException ("Some data is not entered")

        val newPerson = Person(name, surname)

        if(contactsList.containsKey(newPerson)) {
            contactsList.getValue(newPerson).map {
                if (it.getType() == type) {
                    throw Exception("${name} ${surname}'s contact already has a ${type}. " +
                            "You can only edit it")
                }
            }
            val newContact = Contact(type, value)
            contactsList.getValue(newPerson).add(newContact)
        }
        else {
            val newPerson = Person(name, surname)
            val newContact = mutableListOf<Contact>(Contact(type, value))
            contactsList.put(newPerson, newContact)
        }
    }

    fun addInfoToContact(name: String, surname: String, type: ContactType, value: String){
        if(value == "" || name == "" || surname == "")
            throw  IllegalArgumentException("Some data is not entered")

        val thisPerson = Person(name, surname)

        if(contactsList.containsKey(thisPerson)){
            /*if(contactsList.getValue(thisPerson).map{it.getType()} != type) {
                contactsList.getValue(thisPerson).add(Contact(type, value))
            }
            else{
                throw Exception ("${name} ${surname}'s contact already has a ${type}. " +
                        "You can only edit it")
            }*/
            contactsList.getValue(thisPerson).map {
                if (it.getType() == type) {
                    throw Exception("${name} ${surname}'s contact already has a ${type}. " +
                            "You can only edit it")
                }
            }
            val newContact = Contact(type, value)
            contactsList.getValue(thisPerson).add(newContact)
        }
        else {
            throw RuntimeException ("Contact does not exist")

        }
    }

    fun editContact(name: String, surname: String, type: ContactType, newValue: String){

        if(name == "" || surname == "")
            throw IllegalArgumentException ("Some data is not entered")

        val editablePerson = Person(name,surname)

        if(contactsList.containsKey(editablePerson)) {
            contactsList.getValue(editablePerson).removeIf{it.getType() == type}
            if(!(newValue == "")){
                contactsList.getValue(editablePerson).add(Contact(type, newValue))
            }
        }
        else{
            throw RuntimeException ("Contact does not exist")
        }
    }

    fun removeContact(name: String, surname: String) {

        if(name == "" || surname == "")
            throw IllegalArgumentException ("Some data is not entered")

        val removablePerson = Person(name, surname)

        if(contactsList.containsKey(removablePerson)){
            contactsList.remove(removablePerson)
        }
        else
            throw RuntimeException ("${name} ${surname}'s contact doesn't exist")

    }

    fun contactInfo(key: Person):String {
        if(!contactsList.containsKey(key))
            throw RuntimeException ("${key.getName()} ${key.getSurname()}'s contact doesn't exist")
        var info = "Contact: ${key.getName()} ${key.getSurname()}"
        contactsList.getValue(key).forEach(){
            info += "\n${it.getType()}: ${it.getValue()}"
        }
        return info
    }

    fun find(substring: String): List<String>{
        val findedContacts = ArrayList<String>()

        for((key, value) in contactsList){
            if(("${key.getName()} + ${key.getSurname()}").toLowerCase().contains(substring.toLowerCase())){
                findedContacts.add(this.contactInfo(key))

                continue
            }

            for(i in 0..contactsList.getValue(key).size-1) {
                if(contactsList.getValue(key)[i].getValue().toLowerCase().contains(substring.toLowerCase())) {
                    findedContacts.add(this.contactInfo(key))
                    break
                }
            }
        }
           /* for(value in contactsList.getValue(currentKey))
            {
                if(value.toString().toLowerCase().contains(substring.toLowerCase())) {
                    findedContacts.add(this.contactInfo(key.toString()))
                    continue;
                }
            }*/
        return findedContacts
    }
}