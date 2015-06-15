#!/usr/bin/python

class ToDoList:
	def __init__( self ):
		self.items = []

	def addItem( self, item ):
		if item not in self.items:
			self.items.append( item )
		else:
			print "That item is already in the todo list!"

	def deleteItem( self, item ):
		try:
			self.items.remove( item )
		except ValueError:
			print "That item is not in the todo list!"

	def viewList( self ):
		for i, item in enumerate( self.items, start=1 ): print str(i) + ". " + item
