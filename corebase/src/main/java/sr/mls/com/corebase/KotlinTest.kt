package sr.mls.com.corebase


data class User(var name: String = "mor", var age: Int = 29)

fun main(args: Array<String>) {
    val user = User()
    with(user) {
        age = 25
        name = "david"
    }
    print(user)
}

fun test() {
    var mood = "I am sad"
    run {
        val mood = "I am happy"
        println(mood) // I am happy
    }
    println(mood)  // I am sad
}


class KotlinTest {
}