'use strict'

const START_CHAR_CODE = 1072;
const END_CHAR_CODE = 1103;

const form = document.querySelector(".form");
const inputForMessage = form.querySelector(".from__input_type_message");
const inputForOffset = form.querySelector(".form__input_type_offset");
const radiosForMode = form.querySelectorAll(".form__radio");
const checkboxes = form.querySelectorAll('.form__checkbox');
const output = form.querySelector(".form__output")

let alphabet = [];
let mode = "encrypt";
let offset = 0;
let message = ""

// Init
for (let i = START_CHAR_CODE; i <= END_CHAR_CODE; i++) {
  alphabet.push(String.fromCharCode(i));
}

// Functions
function transformation() {
  let result;
  result = message.split("");
  if (mode === "encrypt") {
    result = result.map((symbol) => {
      if (symbol === " ") {
        return symbol;
      } else {
        let newIndex = alphabet.indexOf(symbol) + offset;
        if (newIndex >= alphabet.length) {
          newIndex -= alphabet.length;
        }
        return alphabet[newIndex];
      }
    })
  } else {
    result = result.map((symbol) => {
      if (symbol === " ") {
        return symbol;
      } else {
        let newIndex = alphabet.indexOf(symbol) - offset;
        if (newIndex < 0) {
          newIndex += alphabet.length;
        }
        return alphabet[newIndex];
      }
    })
  }

  output.textContent = result.join("")
}

function addSymbol(symbol) {
  if (symbol === "ё") {
    const index = alphabet.indexOf("е");
    alphabet.splice(index + 1, 0, symbol);
  } else {
    alphabet.push(symbol);
    alphabet.sort();
  }
}

function removeSymbol(symbol) {
  const index = alphabet.indexOf(symbol);
  alphabet.splice(index, 1)
}

function handleChange(e) {
  const value = e.target.value.toLowerCase().trim();

  switch (e.target.name) {
    case "message":
      message = value;
      break;
    case "offset":
      offset = Number(value);
      break;
    default:
      break;
  }

  transformation();
}

function handleSelected(e) {
  mode = e.target.value;
  transformation();
}

function handleChecked(e) {
  const { target: { checked, value}} = e;
  if (checked) {
    addSymbol(value);
  }
  else {
    removeSymbol(value);
  }
  transformation()
}

// Event listeners
inputForMessage.addEventListener("change", handleChange);
inputForOffset.addEventListener('change', handleChange);

radiosForMode.forEach((input) => {
  input.addEventListener("change", handleSelected);
})

checkboxes.forEach((input) => {
  input.addEventListener("change", handleChecked);
})
