#!/usr/bin/python
import string, sys

word = raw_input()

consonants = set('bcdfghjklmnpqrstvwxz')

for letter in word:
        sys.stdout.write(letter)
        if letter.lower() in consonants:
                sys.stdout.write('o')
                sys.stdout.write(letter.lower())

sys.stdout.write('\n')
sys.stdout.flush()
