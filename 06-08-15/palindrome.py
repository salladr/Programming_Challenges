#!/usr/bin/python
import sys

for line in sys.stdin:
	steps = 0;
	num = line.rstrip()
	while num != num[::-1]:
		num = str( int( num ) + int( num[::-1] ) )
		steps += 1
		if steps >= 10000: break
	if steps == 1:
		print '{} gets palindromic after {} step: {}'.format( line.rstrip(), steps, num )
	elif steps >= 10000:
		print '{} does not converge! It is a Lychrel number.'.format( line.rstrip() )
	else:
		print '{} gets palindromic after {} steps: {}'.format( line.rstrip(), steps, num )
