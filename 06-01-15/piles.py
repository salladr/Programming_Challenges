#!/usr/bin/python

size, logs = input(), input()
grid = [ map( int, raw_input().split() ) for i in range( size ) ]
min_pile = min( [ min( grid[i] ) for i in range( size ) ] )

while logs > 0:
	for i, row in enumerate( grid ):
		for j, val in enumerate( row ):
			if val == min_pile:
				grid[i][j] += 1
				logs -= 1
				if logs == 0: break
		if logs == 0: break
	min_pile += 1

for row in grid:
	for val in row:
		print val,
	print ""
