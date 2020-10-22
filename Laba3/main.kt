fun main()
{
    val phoneBook = PhoneBook()

    phoneBook.addContact("Jhon","Wall", ContactType.MOBILE_PHONE, "+720940320")
    phoneBook.addContact("Jhon","Wall", ContactType.HOME_PHONE, "+14124124451")
    phoneBook.addInfoToContact("Jhon","Wall", ContactType.WORK_PHONE, "1242141")
    phoneBook.editContact("Jhon","Wall", ContactType.EMAIL, "")
    phoneBook.editContact("Jhon","Wall", ContactType.EMAIL, "Jhon_Wall00@gmail.com")
    phoneBook.editContact("Jhon","Wall", ContactType.WORK_PHONE, "911")
    phoneBook.addContact("Damian","Lilard", ContactType.MOBILE_PHONE, "+20312421245")
    phoneBook.addInfoToContact("Damian","Lilard", ContactType.EMAIL, "daimtime_free3throw@yandex.ru")
    phoneBook.addContact("Michael", "Jordan", ContactType.WORK_PHONE, "+23")
    phoneBook.addInfoToContact("Michael", "Jordan", ContactType.MOBILE_PHONE, "+23232323")
    phoneBook.addInfoToContact("Michael", "Jordan", ContactType.EMAIL, "ChicagoBullsWin@gmail.com")
    phoneBook.addContact("Lebron","James", ContactType.MOBILE_PHONE, "+2300023")
    phoneBook.removeContact("Lebron", "James")

    val finding1: List<String> = phoneBook.find("L")
    println("Первый запрос:")
    for(i in 0..finding1.size-1) {
        println(finding1[i])
        println("")
    }
    println("Второй запрос:")
    val finding2: List<String> = phoneBook.find("Ll")
    for(i in 0..finding2.size-1) {
        println(finding2[i])
        println("")
    }
    println("Третий запрос:")
    val finding3: List<String> = phoneBook.find("23")
    for(i in 0..finding3.size-1) {
        println(finding3[i])
        println("")
    }

}
