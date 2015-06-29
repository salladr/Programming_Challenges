#!/usr/bin/python

words = raw_input().split()
spaces = []

def go_right( word ):
	print ''.join( spaces ) + word
	spaces.extend( [ " " for c in word[:-1] ] )

def go_left( word ):
	for c in word[:-1]:
		spaces.pop(-1)
	print ''.join( spaces ) + word[::-1]
	

for i, word in enumerate( words ):
	if i % 4 == 0:
		if len( word[:-1] ) > len( spaces ):
			go_right( word )
		else:
			go_left( word )

	elif i % 2 == 0:
		go_right( word )

	else:
		if i == len( words ) - 1:
			for c in word[1:]:
				print ''.join( spaces ) + c
		else:
			for c in word[1:-1]:
				print ''.join( spaces ) + c
