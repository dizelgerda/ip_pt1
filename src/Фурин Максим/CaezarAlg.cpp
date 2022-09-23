#include <iostream>

#define M 256
#define L 32

using namespace std;

int main()
{
	setlocale(LC_ALL, "rus");
	char mas[M] = { "АОИНХУСРРГВ ЫОЩХУЗЕД РПЕРКТЭ" };
	int i, k, j, x;
	x = 0;
	j = 1;
	k = 1;
	while (j <= L)
	{
		cout << "\n" << "j = " << j << "\n";
		for (i = 0; i < strlen(mas); i++)
		{
			x = mas[i];
			if ((mas[i] >= 'а') && (mas[i] <= 'я'))
			{
				if (mas[i] + k <= 'я')
					if ((mas[i] == 'й') || (mas[i] == 'и'))
						x = mas[i] + k + 1;
					else x = mas[i] + k;
				else
					x = mas[i] + k - L;
			}
			if ((mas[i] >= 'А') && (mas[i] <= 'Я'))
			{
				if (mas[i] + k <= 'Я')
					if ((mas[i] == 'Й') || (mas[i] == 'И'))
						x = mas[i] + k + 1;
					else x = mas[i] + k;
				else
					x = mas[i] + k - L;
			}
			mas[i] = x;
			cout << mas[i];
		}
		j++;
	}

	return 0;
}