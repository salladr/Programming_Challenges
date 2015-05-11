#!/usr/bin/python

values = map( int, raw_input().split() )

average = float( sum( values ) ) / len( values )
variance = float( sum( [ ( x - average )**2 for x in values ] ) ) / len( values )
standard_dev = round( variance**0.5, 4 )

print standard_dev
