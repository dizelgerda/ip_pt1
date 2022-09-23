"""
    CAESAR cipher
"""


def unicode_cipher_caesar(message, step, upper, lower, all_letters):
    result = ''
    for i in range(len(message)):
        char = message[i]
        if char.isalpha():
            if char.isupper():
                result += chr((ord(char) + step - upper) % all_letters + upper)
            else:
                result += chr((ord(char) + step - lower) % all_letters + lower)
        else:
            result += char
    return result


def caesar_cipher_alphabetically(message, step, alphabet):
    result = ''
    alphabet_lower = alphabet.lower()
    for i in message:
        temp = i.upper()
        if temp.isalpha() and temp in alphabet:
            if i.isupper():
                result += alphabet[alphabet.find(i) + step]
            else:
                result += alphabet_lower[alphabet_lower.find(i) + step]
        else:
            result += i

    return result


def distribution(message, lang, step, choose):
    if lang == 'EN':
        return unicode_cipher_caesar(message, step, 65, 97, 26)
    elif lang == 'RU':
        if choose == 1:
            alphabet = 'АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ'
            return caesar_cipher_alphabetically(message, step, alphabet)
        elif choose == 2:
            return unicode_cipher_caesar(message, step, 1040, 1072, 32)
        elif choose == 3:
            alphabet = 'АБВГДЕЁЖЗИКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ'
            return caesar_cipher_alphabetically(message, step, alphabet)
        elif choose == 4:
            alphabet = 'АБВГДЕЖЗИКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ'
            return caesar_cipher_alphabetically(message, step, alphabet)


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    while True:
        print("Введите язык английский - EN\n"
              "            или русский - RU\n"
              "Если хотите выйти из прораммы введит - exit")
        lang = input(">> ")
        choose = 0
        if lang == 'RU':
            print("Введите 1, если хотите использовать всю Кирилицу\n"
              "Введите 2, если хотите использовать Кирилицу без Ё\n"
              "Введите 3, если хотите использовать Кирилицу без Й\n"
              "Введите 4, если хотите использовать Кирилицу без Ё и Й\n"
              )
            choose = int(input(">> "))
        elif lang == "exit":
            break

        print("Если Вы хотите ШИФРОВАТЬ текст введите положительный шаг,"
              " если ДЕШИФРОВАТЬ отрицательный шаг")
        step = int(input("Введите шаг:\n>> "))
        message = input("Веедите текст :\n>> ")
        print("\t\t\tРезльтат:")
        print(">> ",  distribution(message, lang, step, choose))
