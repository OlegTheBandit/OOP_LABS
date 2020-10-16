enum class ContactType(contactType:String)
{
    MOBILE_PHONE("Mobile phone"),
    WORK_PHONE("Work phone"),
    HOME_PHONE("Home phone"),
    EMAIL("E-mail")
}

class PhoneBook {
    private var contactsList = HashMap<String,  HashMap<ContactType, String>>()


    fun addContact (name: String, surname: String, type: ContactType, value: String){
        if(value == "")
            throw Exception ("Данные ${type} не введены")

        if(contactsList.containsKey(name + " " + surname)){
            if(!contactsList.getValue(name + " " + surname).containsKey(type)) {
                contactsList.getValue(name + " " + surname).put(type, value)
            }
            else{
                throw Exception ("У контакта ${name} ${surname} уже существует поле ${type}. " +
                        "Вы можете только редактировать его")
            }
        }
        else {
            var newContact= hashMapOf<ContactType, String>(type to value)
            contactsList.put(name + " " + surname, newContact)
        }
    }

    fun addInfoToContact(name: String, surname: String, type: ContactType, value: String){
        if(value == "")
            throw Exception ("Данные ${type} не введены")

        if(contactsList.containsKey(name + " " + surname)){
            if(!contactsList.getValue(name + " " + surname).containsKey(type)) {
                contactsList.getValue(name + " " + surname).put(type, value)
            }
            else{
                throw Exception ("У контакта ${name} ${surname} уже существует поле ${type}. " +
                        "Вы можете только редактировать его")
            }
        }
        else {
            throw Exception ("Контакт не существует.")

        }
    }

    fun editContact(name: String, surname: String, type: ContactType, newValue: String){

        if(contactsList.containsKey(name + " " + surname)) {
            if(newValue == ""){
                contactsList.getValue(name + " " + surname).remove(type)
            }
            else {
                contactsList.getValue(name + " " + surname).put(type, newValue)
            }
        }
        else{
            throw Exception ("Контакт не существует.")
        }
    }

    fun removeContact(name: String, surname: String) {
        if(contactsList.containsKey(name + " " + surname)){
            contactsList.remove(name + " " + surname);
        }
        else
            throw Exception ("Контакт ${name} ${surname} не существует в списке")

    }

    fun contactInfo(key: String):String {
        if(!contactsList.containsKey(key))
            throw Exception ("Контакт ${key} не существует в списке")
        var info: String = "Contact: ${key}"
        for((key, value) in contactsList.getValue(key)){

            info += "\n${key}: $value"
        }
        return info
    }

    fun find(substring: String): List<String>{
        var findedContacts = ArrayList<String>()

        for((key, value) in contactsList){
            var currentKey: String = key
            if(key.toString().toLowerCase().contains(substring.toLowerCase())){
                findedContacts.add(this.contactInfo(key))

                continue;
            }
            for((innetKey, innerValue) in value)
            {
                if(innerValue.toString().toLowerCase().contains(substring.toLowerCase())) {
                    findedContacts.add(this.contactInfo(key))
                    break
                }
            }
           /* for(value in contactsList.getValue(currentKey))
            {
                if(value.toString().toLowerCase().contains(substring.toLowerCase())) {
                    findedContacts.add(this.contactInfo(key.toString()))
                    continue;
                }
            }*/
        }


        return findedContacts
    }
}