package com.vlaggroshkov.myapplication.utils

class StringUtils {
    companion object {
        fun getIndex(string: String, substring: Char): Int {
            for (i in string.indices) {
                if (string[i] == substring) {
                    return i
                }
            }
            return -1
        }
    }
}