#!/usr/bin/python
import string, sys

word = raw_input()

vowels = ["a", "e", "i", "o", "u", "y"]
punc = set(string.punctuation)
spc = set(string.whitespace)

for letter in word:
        sys.stdout.write(letter)
        if letter.lower() not in vowels and letter not in punc and letter not in spc:
                sys.stdout.write('o')
                sys.stdout.write(letter.lower())

sys.stdout.write('\n')
sys.stdout.flush()
