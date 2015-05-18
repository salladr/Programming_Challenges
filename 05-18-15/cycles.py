#!/usr/bin/python

b = int( raw_input() )
n = raw_input()

# Using Floyd's algorithm for cycle detection

tortoise = sum( [ int( digit )**b for digit in str( n ) ] )
hare = sum( [ int( digit )**b for digit in str( tortoise ) ] )

while tortoise != hare:
	tortoise = sum( [ int( digit )**b for digit in str( tortoise ) ] )
	hare_temp = sum( [ int( digit )**b for digit in str( hare ) ] )
	hare = sum( [ int( digit )**b for digit in str( hare_temp ) ] )

hare = sum( [ int( digit )**b for digit in str( tortoise ) ] )
while tortoise != hare:
	print hare,
	hare = sum( [ int( digit )**b for digit in str( hare ) ] )

print tortoise
