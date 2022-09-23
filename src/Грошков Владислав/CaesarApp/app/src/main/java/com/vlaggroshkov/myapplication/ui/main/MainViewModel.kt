package com.vlaggroshkov.myapplication.ui.main

import androidx.lifecycle.ViewModel
import com.vlaggroshkov.myapplication.utils.StringUtils

class MainViewModel : ViewModel() {
    private val alphabetEnArray = "abcdefghijklmnopqrstuvwxyz"
    private var alphabetRuArray = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"

    fun encryptString(
        stringToEncrypt: ArrayList<String>,
        useEnglishAlphabet: Boolean,
        step: Int,
        isYON: Boolean,
        isEON: Boolean,
    ): String {
        changeRuAlphabetIfNeeded(isYON, isEON)
        return if (useEnglishAlphabet) {
            getEncryptedOrDecryptedString(stringToEncrypt, step, alphabetEnArray, true)
        } else {
            getEncryptedOrDecryptedString(stringToEncrypt, step, alphabetRuArray, true)
        }
    }

    fun decryptString(
        stringToEncrypt: ArrayList<String>,
        useEnglishAlphabet: Boolean,
        step: Int,
        isYON: Boolean,
        isEON: Boolean
    ): String {
        changeRuAlphabetIfNeeded(isYON, isEON)
        return if (useEnglishAlphabet) {
            getEncryptedOrDecryptedString(stringToEncrypt, step, alphabetEnArray, false)
        } else {
            getEncryptedOrDecryptedString(stringToEncrypt, step, alphabetRuArray, false)
        }
    }

    private fun changeRuAlphabetIfNeeded(isYON: Boolean, isEON: Boolean) {
        if (isYON && isEON) {
            alphabetRuArray = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя"
        }
        else if (!isYON && !isEON) {
            alphabetRuArray = "абвгдежзиклмнопрстуфхцчшщъыьэюя"
        }
        else {
            if (!isYON) {
                alphabetRuArray = "абвгдеёжзиклмнопрстуфхцчшщъыьэюя"
            }
            if (!isEON) {
                alphabetRuArray = "абвгдежзийклмнопрстуфхцчшщъыьэюя"
            }

        }
    }

    private fun getEncryptedOrDecryptedString(
        stringToEncrypt: ArrayList<String>,
        step: Int,
        alphabet: String,
        toEncrypt: Boolean
    ): String {
        var resultString = ""
        var newStep = step
        if (!toEncrypt)
            newStep *= -1
        val lengthAlphabet = alphabet.length
        for (symbol in stringToEncrypt) {
            if (symbol.isNotEmpty()) {
                var index = (StringUtils.getIndex(alphabet, symbol[0]) + newStep) % lengthAlphabet
                if (index < 0)
                    index += lengthAlphabet
                resultString += alphabet[index]
            }
        }
        return resultString
    }
}