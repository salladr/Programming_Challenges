#!/usr/bin/python
# -- coding: utf-8 --
import string, sys

word = raw_input()

exclude = set("aeiouyåöä").union(set(string.punctuation).union(set(string.whitespace)))

for letter in word:
        sys.stdout.write(letter)
        if letter.lower() not in exclude:
                sys.stdout.write('o')
                sys.stdout.write(letter.lower())

sys.stdout.write('\n')
sys.stdout.flush()
