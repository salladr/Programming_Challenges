#!/usr/bin/python
import string

word = raw_input()

new_word = []

vowels = ["a", "e", "i", "o", "u", "y"]
punc = set(string.punctuation)
spc = set(string.whitespace)

for letter in word:
        new_word.append(letter)
        if letter.lower() not in vowels and letter not in punc and letter not in spc:
                str = "o" + letter.lower()
                new_word.append(str)

print ''.join(new_word)
