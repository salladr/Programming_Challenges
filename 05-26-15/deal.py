#!/usr/bin/python
# -*- coding: utf-8 -*-
import random

hearts = set( [ "2♥", "3♥", "4♥", "5♥", "6♥", "7♥", "8♥", "9♥", "10♥", "J♥", "K♥", "Q♥", "A♥" ] )
spades = set( [ "2♠", "3♠", "4♠", "5♠", "6♠", "7♠", "8♠", "9♠", "10♠", "J♠", "K♠", "Q♠", "A♠" ] )
diamonds = set( [ "2♦", "3♦", "4♦", "5♦", "6♦", "7♦", "8♦", "9♦", "10♦", "J♦", "K♦", "Q♦", "A♦" ] )
clubs = set( [ "2♣", "3♣", "4♣", "5♣", "6♣", "7♣", "8♣", "9♣", "10♣", "J♣", "K♣", "Q♣", "A♣" ] )

deck = set.union( hearts, spades, diamonds, clubs )
num_players = input( "How many players (2-8) ? " )

def deal( num ):
	for n in range( 0, num ):
		card = random.sample( deck, 1 )
		deck.remove( card[0] )
		print card[0] + " |",
	print ""

for player in range( 0, num_players ):
	if player == 0:
		print "Your hand:	|",
	else:
		print "CPU " + str( player ) + " hand:	|",
	deal(2)

print ""
burn = random.sample( deck, 1 )
deck.remove( burn[0] )
print "Flop:		|",
deal(3)

burn = random.sample( deck, 1 )
deck.remove( burn[0] )
print "Turn:		|",
deal(1)

burn = random.sample( deck, 1 )
deck.remove( burn[0] )
print "River:		|",
deal(1)
