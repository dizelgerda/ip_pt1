def cesar_cipher():
    dict1='абвгдеёжзийклмнопрстуфхцчшщъыьэюя'
    _key=33
    newWord=''
    key=int(input('введите ключ : '))
    if int(input('если буква ё должна присутствовать в словаре, то введите 1. В противном случае 0 : '))==0:
        dict1=dict1.replace('ё','')
        _key-=1
    if int(input('если буква й должна присутствовать в словаре, то введите 1. В противном случае 0 : '))==0:
        dict1=dict1.replace('й','')
        _key-=1
    if key>_key:
        key=key%_key
    dict1=dict1*2
    word=input('Введите шифруемое слово : ').split()
    dict1=list(dict1)
    print(dict1)
    for letter in word:
        for let in letter:
            newWord+=str(dict1[dict1.index(let)+key])
        newWord+=' '
    print(word) 
    print(newWord)

def cesar_decryptor():
    dict1='абвгдеёжзийклмнопрстуфхцчшщъыьэюя'
    newWord=''
    _key=33
    key=int(input('введите ключ : '))
    if key>33:
        key=key%33
    if int(input('если буква ё должна присутствовать в словаре, то введите 1. В противном случае 0 : '))==0:
        dict1=dict1.replace('ё','')
        _key-=1
    if int(input('если буква й должна присутствовать в словаре, то введите 1. В противном случае 0 : '))==0:
        dict1=dict1.replace('й','')
        _key-=1
    if key>_key:
        key=key%_key
    dict1=dict1*2
    word=input('Введите шифруемое слово : ').split()
    dict1=list(dict1)
    print(dict1)
    for letter in word:
        for let in letter:
            newWord+=str(dict1[dict1.index(let)+33-key])
        newWord+=' '
    print(word) 
    print(newWord)


while(True):
    answer=int(input('для шифровки нажмите 2, для дешифровке 1, для выхода 0 : '))
    if answer==2:
        cesar_cipher()
    elif answer==1:
        cesar_decryptor()
    elif answer==0:
        break

