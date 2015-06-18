#!/usr/bin/python
import json
import os
from collections import defaultdict

class ToDoList:
	def __init__( self, file_name ):
		self.file_name = file_name
		if os.path.isfile( file_name ):
			with open( file_name ) as open_file:
				self.items = json.load( open_file )
		else:
			self.items = defaultdict( list )

	def addItem( self, item, *categories ):
		categories = categories + ( 'TO DO', )
		for c in categories:
			if item not in self.items[ c.upper() ]:
				self.items[ c.upper() ].append( item )

	def updateItem( self, old, new ):
		for cat, vals in self.items.iteritems():
			if old in vals:
				vals[ vals.index( old ) ] = new
			
	def deleteItem( self, item ):
		for cat, vals in self.items.iteritems():
			if item in vals:
				vals.remove( item )

	def viewList( self, *categories ):
		if not categories:
			categories = [ 'TO DO' ]

		for key in categories:
			if key.upper() not in self.items:
				print "Error: {} is not a category in the list!\n".format( key )
				return

		print "----{}----".format( " & ".join( c.upper() for c in categories ) )

		for item in set.intersection( *[ set( item for item in self.items[ c.upper() ] ) for c in categories ] ):
			print "- {}".format( item )

		print ""

	def __del__( self ):
		with open( self.file_name, 'w' ) as outfile:
			json.dump( self.items, outfile )
