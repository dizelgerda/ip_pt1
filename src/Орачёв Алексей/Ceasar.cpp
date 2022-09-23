#include<iostream>
#include<string>
using namespace std;
int main()
{
    string base, type;
    int k;
    cout << "Write base string: ";
    getline(cin, base);
    cout << "Write type of operaion(code/decode): ";
    cin >> type;
    cout << "Write key: ";
    cin >> k;

    // CODE FROM NORMAL TO CESAR

    if (type == "code")
    {
        cout << endl << "Crypted:  ";
        for (int i = 0; i < base.size(); i++)
        {
            if (int(base[i]) >= 65 && int(base[i] <= 90))
                if (int(base[i]) == 90) cout << char(k + 64);
                else cout << char(int(base[i]) + k);
            else if (int(base[i]) >= 97 && int(base[i] <= 122))
                if (int(base[i]) == 122) cout << char(k + 97);
                else cout << char(int(base[i]) + k);
            else cout << base[i];
        }
    }


    if (type == "decode")
    {
        cout << endl << "Encrypted:  ";
        for (int i = 0; i < base.size(); i++)
        {
            if (int(base[i]) >= 65 && int(base[i] <= 90))
                if (int(base[i]) == 65) cout << char(90 - k);
                else cout << char(int(base[i] - k));
            else if (int(base[i]) >= 97 && int(base[i] <= 122))
                if (int(base[i]) == 97) cout << char(122 - k);
                else cout << char(int(base[i] - k));
            else cout << base[i];
        }
    }

    cout << endl;
    return 0;
}
