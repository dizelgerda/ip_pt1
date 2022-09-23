#include <cmath>
#include <iostream>

struct Key {
  int n;
  int x;
};

std::pair<int, int> extendGcd(int a, int b) {
  if (b == 0)
    return {1, 0};

  const auto [x1, y1] = extendGcd(b, a % b);
  int x = y1;
  int y = x1 - (a / b) * y1;
  return {x, y};
}

std::pair<Key, Key> generateKeys(int p, int q) {
  const int n = p * q;
  const int fn = (p - 1) * (q - 1);
  const int e = 7;
  auto [x, y] = extendGcd(e, fn);

  if (x < 0)
    x += fn;

  const int d = x;
  return {{n, e}, {n, d}};
}

int applyKey(int c, Key key) {
  c = c - 'a';
  const int offset = static_cast<int>(std::pow(c, key.x)) % key.n;
  return 'a' + offset;
}

std::string applyKey(const std::string& text, Key key) {
  std::string result;
  for (const char c : text)
    result += static_cast<char>(applyKey(c, key));

  return result;
}

int main()
{
  const std::string testStr = "Hello World!\n";
  const auto [publicKey, privateKey] = generateKeys(3, 11);
  const std::string encryptedText = applyKey(testStr, publicKey);
  std::cout << testStr << std::endl;
  std::cout << encryptedText << std::endl;
  std::cout << applyKey(encryptedText, privateKey) << std::endl;
}
