#!/usr/bin/python
import string

sentence = raw_input().split()

for word in sentence:
	punctuation = [ ( i, p ) for i, p in enumerate( word ) if p in string.punctuation ]
	capitals = [ i for i, c in enumerate( word ) if c.isupper() ]

	for p in string.punctuation:
		word = word.replace( p, "" )

	word = sorted( word.lower() )

	for i, p in punctuation:
		word.insert( i, p )

	word = [ c.upper() if i in capitals else c for i, c in enumerate( word ) ]
	print "".join( word ),
