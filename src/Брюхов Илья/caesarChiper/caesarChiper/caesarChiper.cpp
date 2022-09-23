#include <iostream>
#include <locale>
#include <string>
#include <vector>
#include <windows.h>

enum class Language { EN, RU };

const std::vector enAlphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                                'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                                's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

const std::vector ruAlphabet = {'à', 'á', 'â', 'ã', 'ä', 'å', '¸', 'æ', 'ç',
                                'è', 'é', 'ê', 'ë', 'ì', 'í', 'î', 'ï', 'ð',
                                'ñ', 'ò', 'ó', 'ô', 'õ', 'ö', '÷', 'ø', 'ù',
                                'ú', 'û', 'ü', 'ý', 'þ', 'ÿ'};

std::string toString(Language l) {
  switch (l) {
  case Language::EN:
    return "en";
  case Language::RU:
    return "rus";
  }

  return "";
}

Language toLanguage(const std::string &lang) {
  if (lang == "en")
    return Language::EN;
  if (lang == "rus")
    return Language::RU;

  throw std::runtime_error("Unknown locale: " + lang);
}

struct LocaleSettings {
  Language language;
  bool useIo;
  bool useIi;
  std::vector<char> alphabet;
};

LocaleSettings makeLocalSettings(const std::string &language, bool useIo,
                                 bool useIi) {
  LocaleSettings settings;

  settings.language = toLanguage(language);
  settings.useIo = useIo;
  settings.useIi = useIi;

  if (settings.language == Language::EN)
    settings.alphabet = enAlphabet;
  else if (settings.language == Language::RU) {
    settings.alphabet = ruAlphabet;
    if (!useIi) {
      const auto iiIt = std::find(std::begin(settings.alphabet),
                                  std::end(settings.alphabet), 'é');
      settings.alphabet.erase(iiIt);
    }
    if (!useIi) {
      const auto ioIt = std::find(std::begin(settings.alphabet),
                                  std::end(settings.alphabet), '¸');
      settings.alphabet.erase(ioIt);
    }
  } else
    throw std::runtime_error("Unknown locale: " + language);

  return settings;
}

template <typename ForwardIt>
int calculateIndex(ForwardIt first, ForwardIt last, int size, int key) {
  const int offset = std::distance(first, last) + key;
  const int index = offset < 0 ? -1 * (std::abs(offset) % size) : offset % size;
  return index < 0 ? size + index : index;
}

char encryptSymbol(char c, int key, const LocaleSettings &settings) {

  const bool isLower =
      std::islower(c, std::locale(toString(settings.language)));
  c = tolower(c);

  const auto symbolIt = std::ranges::find(std::begin(settings.alphabet),
                                          std::end(settings.alphabet), c);
  if (symbolIt == std::end(settings.alphabet))
    return c;

  const int index = calculateIndex(std::begin(settings.alphabet), symbolIt,
                                   settings.alphabet.size(), key);

  return isLower ? settings.alphabet[index] : toupper(settings.alphabet[index]);
}

std::string makeCaesar(const std::string &text, int key,
                       const LocaleSettings &settings) {
  std::string encryptedText;
  for (const char c : text)
    encryptedText += encryptSymbol(c, key, settings);

  return encryptedText;
}

void setLocalization() {
  std::setlocale(LC_ALL, "rus");
  SetConsoleCP(1251);
  SetConsoleOutputCP(1251);
}

void runCaesar(std::istream &in = std::cin, std::ostream &out = std::cout) {
  setLocalization();

  out << "Enter language (en/rus): ";
  std::string language;
  in >> language;

  out << "Enter text: ";
  std::string text;
  in >> text;

  out << "Enter key: ";
  int key;
  in >> key;

  bool useIo = false;
  bool useIi = false;

  if (toLanguage(language) == Language::RU) {
    out << "Use ¸?(y/n) ";
    char answer;
    in >> answer;
    useIo = answer == 'y';

    out << "Use é?(y/n) ";
    in >> answer;
    useIi = answer == 'y';
  }
  const LocaleSettings locSettings = makeLocalSettings(language, useIo, useIi);

  out << makeCaesar(text, key, locSettings);
}

int main() {
  runCaesar();
  return 0;
}
