(function () {
    // "use strict";
  
    window.CaesarCode = function (input) {
      let alphabet = "АБВГДЕЁЖЗИӢКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ".toLowerCase(), // полный алфавит
        //let alphabet = "АБВГДЕЖЗИӢКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ".toLowerCase(), // без Ё
        //let alphabet = "АБВГДЕЁЖЗИКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ".toLowerCase(), // без Й
        //let alphabet = "АБВГДЕЖЗИКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ".toLowerCase(), // без Й и без Ё
  
        shiftAmount,
        shiftedAlphabet = "",
        coddedMessage = "",
        originalMessage , // типо строка
        otherCharacters = "-=~\"'#$%&*^:<>?/...!{(|)}.1234567890, "; // не нужно сдвигать вообще ДАЖЕ ПРОБЕЛ
  
      if (typeof input === "object") {
        shiftAmount = input.shift;
        originalMessage = input.msg.toLowerCase();
      } else {
        // если не обьект
        return;
      }
  
      if (typeof originalMessage  === "string" || typeof (originalMessage  + "") === "string") {
        // текстовая ли это строка - число + это тоже будет string
  
        shiftedAlphabet = alphabet.slice(shiftAmount); // метод строки, вырезаем кусочек строки (шаг)
  
        shiftedAlphabet += alphabet.slice(0, shiftAmount);
  
        shiftedAlphabet += otherCharacters;
        alphabet += otherCharacters; // заменяются одинакого
  
        // цикл от длины строки
        for (let i = 0; i < originalMessage.length; i++) {
          let number = alphabet.indexOf(originalMessage [i]);
  
          coddedMessage += shiftedAlphabet[number];
        }
      } else {
        // если это НЕ строка, выход
        return;
      }
  
      return coddedMessage; // выводим шифр
    };
  })();
  
  document.getElementById("text").addEventListener("input", function () {
    let itsValue = this.value;
    document.getElementById("output").textContent = CaesarCode({
      msg: itsValue,
      shift: document.getElementById("shift").value,
    });
  });
  document.getElementById("shift").addEventListener("keyup", function () {
    let itsValue = this.value;
    document.getElementById("output").textContent = CaesarCode({
      msg: document.getElementById("text").value,
      shift: itsValue,
    });
  });
  
  document.getElementById("output").textContent = CaesarCode({
    msg: document.getElementById("text").value,
    shift: document.getElementById("shift").value,
  });