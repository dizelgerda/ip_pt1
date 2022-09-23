OP = {
    '+': lambda x, y, z: (x + y) % z,
    '-': lambda x, y, z: (x - y) % z,
}


def caesar_cipher(text, shift, encryption=True, letter_y=True, letter_yo=True):
    text = text.lower()
    if letter_y and letter_yo:
        alphabet = 'абвгдеёжзийклмнопрстуфхцчшщъыьэюя'
    elif letter_y:
        alphabet = 'абвгдежзийклмнопрстуфхцчшщъыьэюя'
    elif letter_yo:
        alphabet = 'абвгдеёжзиклмнопрстуфхцчшщъыьэюя'
    else:
        alphabet = 'абвгдежзиклмнопрстуфхцчшщъыьэюя'
    if encryption:
        symbol = '+'
    else:
        symbol = '-'
    alph_len = len(alphabet)
    new_text = ''
    for i in range(len(text)):
        if text[i] == ' ':
            new_text += ' '
            continue
        new_text += alphabet[OP[symbol](alphabet.find(text[i]), shift, alph_len)]
    return new_text


if __name__ == '__main__':
    command = 0
    while(command == 0 or command == 1):
        print('Выберите действие:')
        print('- для шифрования введите 0')
        print('- для расшифрования введите 1')
        print('- для завершения работы нажмите любой другой символ')
        command = int(input())
        if command == 0:
            encryption = True
        elif command == 1:
            encryption = False
        else:
            continue
        limit = 31
        print('Учитывать "Ё"? (0 - да, 1 - нет)')
        command = int(input())
        if command == 0:
            letter_yo = True
            limit += 1
        elif command == 1:
            letter_yo = False
        else:
            continue
        print('Учитывать "Й"? (0 - да, 1 - нет)')
        command = int(input())
        if command == 0:
            letter_y = True
            limit += 1
        elif command == 1:
            letter_y = False
        else:
            continue
        shift = -1
        while(shift < 0 or shift > limit):
            print('Ведите ключ:')
            shift = int(input())
        print('Введите текст:')
        text = input()
        print(f'Вывод: {caesar_cipher(text, shift, encryption, letter_y, letter_yo)}')
        print()
