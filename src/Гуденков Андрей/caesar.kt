package caesar

const val RU_32 = "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ"
const val RU_33 = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЬЫЪЭЮЯ"
const val EN_26 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"

fun encrypt(text: String, alphabet: String, key: Int): String {
    return text.map { char ->
        if (char.isLetter()) {
            val currIndex = alphabet.indexOf(char, ignoreCase = true)
            if (currIndex == -1) throw IllegalArgumentException("Текст не соответствует алфавиту")
            val newIndex = (currIndex + key) % alphabet.length
            if (char.isUpperCase()) {
                alphabet.get(newIndex).uppercaseChar()
            } else {
                alphabet.get(newIndex).lowercaseChar()
            }
        } else {
			char
        }
    }.joinToString("")
}

fun decrypt(text: String, alphabet: String, key: Int): String {
    return text.map { char ->
        if (char.isLetter()) {
            val currIndex = alphabet.indexOf(char, ignoreCase = true)
            if (currIndex == -1) throw IllegalArgumentException("Текст не соответствует алфавиту")
            val newIndex = (currIndex - key) % alphabet.length
            if (char.isUpperCase()) {
                alphabet.get(newIndex).uppercaseChar()
            } else {
                alphabet.get(newIndex).lowercaseChar()
            }
        } else {
			char
        }
    }.joinToString("")
}

const val FIRST = "1"
const val SECOND = "2"
const val THIRD = "3"

fun main() {
    println(
        "Выберите алфавит:\n" +
        "1 - Русский, 32 буквы\n" +
        "2 - Русский, 33 буквы\n" +
        "3 - Английский, 26 букв\n" +
        "Другая клавиша - выход"
    )
    val alphabetChoice = readLine()
    val alphabet = when(alphabetChoice) {
        FIRST -> RU_32
        SECOND -> RU_33
        THIRD -> EN_26
        else -> return
    }

    while (true) {
        println(
            "Выберите вариант:\n" +
            "1 - Зашифровать текст\n" +
            "2 - Расшифровать текст\n" +
            "Другая клавиша - выход"
        )

        val funChoice = readLine()
        val function = when(funChoice) {
            FIRST -> ::encrypt
            SECOND -> ::decrypt
            else -> return
        }

        println("Введите текст:")
        val text = readLine()
        println("Вы ввели $text");
        if (text.isNullOrEmpty()) {
            println("Вы ввели пустую строку")
            return
        }

        println("Введите ключ (целое число):")
        var key: Int? = null
        key = readLine()?.toIntOrNull()
        while (key == null) {
            println("Введите ключ (целое число):")
            key = readLine()?.toIntOrNull()
        }

        try {
            val result = function(text, alphabet, key)
            println("Результат: $result")
        } catch(e: IllegalArgumentException) {
            println("Ошибка: ${e.message}")
        }
    }
}