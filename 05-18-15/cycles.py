#!/usr/bin/python

b = int( raw_input() )
n = raw_input()

# Using Floyd's algorithm for cycle detection

def digit_sum( number ):
	return sum( [ int( digit )**b for digit in str( number ) ] )

tortoise = digit_sum( n )
hare = digit_sum( tortoise )

while tortoise != hare:
	tortoise = digit_sum( tortoise )
	hare = digit_sum( digit_sum( hare ) )

hare = digit_sum( tortoise )
while tortoise != hare:
	print hare,
	hare = digit_sum( hare )

print tortoise
