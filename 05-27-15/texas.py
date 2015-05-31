#!/usr/bin/python
# -*- coding: utf-8 -*-
import random

hearts = set( [ "2♥", "3♥", "4♥", "5♥", "6♥", "7♥", "8♥", "9♥", "X♥", "J♥", "K♥", "Q♥", "A♥" ] )
spades = set( [ "2♠", "3♠", "4♠", "5♠", "6♠", "7♠", "8♠", "9♠", "X♠", "J♠", "K♠", "Q♠", "A♠" ] )
diamonds = set( [ "2♦", "3♦", "4♦", "5♦", "6♦", "7♦", "8♦", "9♦", "X♦", "J♦", "K♦", "Q♦", "A♦" ] )
clubs = set( [ "2♣", "3♣", "4♣", "5♣", "6♣", "7♣", "8♣", "9♣", "X♣", "J♣", "K♣", "Q♣", "A♣" ] )

deck = set.union( hearts, spades, diamonds, clubs )
num_players = input( "How many players (2-8) ? " )
hands = []

####################################################################################
# Deal the cards
####################################################################################
def deal( num, id ):
	for n in range( 0, num ):
		card = random.sample( deck, 1 )
		hands[ id ].append( card[0] )
		deck.remove( card[0] )
		print card[0] + " |",
	print ""

for player in range( 0, num_players ):
	hands.append( [] )
	if player == 0:
		print "Your hand:	|",
	else:
		print "CPU " + str( player ) + " hand:	|",
	deal( 2, player )

hands.append( [] )
print ""
burn = random.sample( deck, 1 )
deck.remove( burn[0] )
print "Flop:		|",
deal( 3, num_players )

burn = random.sample( deck, 1 )
deck.remove( burn[0] )
print "Turn:		|",
deal( 1, num_players )

burn = random.sample( deck, 1 )
deck.remove( burn[0] )
print "River:		|",
deal( 1, num_players )
print ""

####################################################################################
# Determine the winning hand
####################################################################################
for i in range( 0, num_players ):
	for card in hands[ num_players ]:
		hands[i].append( card )
hands.pop()

score = []

for hand in hands:
	in_hand = [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ]
	count   = [ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 ]
	start_heart = 0
	h_in_hand = []
	num_hearts = 0
	start_diamond = 0
	d_in_hand = []
	num_diamonds = 0
	start_club = 0
	c_in_hand = []
	num_clubs = 0
	start_spade = 0
	s_in_hand = []
	num_spades = 0
	for card in hand:
		if card[1:] == "♥":
			num_hearts += 1
			if card[0] == 'X':
				if 8 > start_heart: start_heart = 8
				h_in_hand.append( 8 )
			elif card[0] == 'J':
				if 9 > start_heart: start_heart = 9
				h_in_hand.append( 9 )
			elif card[0] == 'Q':
				if 10 > start_heart: start_heart = 10
				h_in_hand.append( 10 )
			elif card[0] == 'K':
				if 11 > start_heart: start_heart = 11
				h_in_hand.append( 11 )
			elif card[0] == 'A':
				if 12 > start_heart: start_heart = 12
				h_in_hand.append( 12 )
			else:
				if int( card[0] ) - 2 > start_heart: start_heart = int( card[0] ) - 2
				h_in_hand.append( int( card[0] ) - 2 )

		elif card[1:] == "♦":
			num_diamonds += 1
			if card[0] == 'X':
				if 8 > start_diamond: start_diamond = 8
				d_in_hand.append( 8 )
			elif card[0] == 'J':
				if 9 > start_diamond: start_diamond = 9
				d_in_hand.append( 9 )
			elif card[0] == 'Q':
				if 10 > start_diamond: start_diamond = 10
				d_in_hand.append( 10 )
			elif card[0] == 'K':
				if 11 > start_diamond: start_diamond = 11
				d_in_hand.append( 11 )
			elif card[0] == 'A':
				if 12 > start_diamond: start_diamond = 12
				d_in_hand.append( 12 )
			else:
				if int( card[0] ) - 2  > start_diamond: start_diamond = int( card[0] ) - 2
				d_in_hand.append( int( card[0] ) - 2 )

		elif card[1:] == "♣":
			num_clubs += 1
			if card[0] == 'X':
				if 8 > start_club: start_club = 8
				c_in_hand.append( 8 )
			elif card[0] == 'J':
				if 9 > start_club: start_club = 9
				c_in_hand.append( 9 )
			elif card[0] == 'Q':
				if 10 > start_club: start_club = 10
				c_in_hand.append( 10 )
			elif card[0] == 'K':
				if 11 > start_club: start_club = 11
				c_in_hand.append( 11 )
			elif card[0] == 'A':
				if 12 > start_club: start_club = 12
				c_in_hand.append( 12 )
			else:
				if int( card[0] ) - 2  > start_club: start_club = int( card[0] ) - 2
				c_in_hand.append( int( card[0] ) - 2 )

		elif card[1:] =="♠":
			num_spades += 1
			if card[0] == 'X':
				if 8 > start_spade: start_spade = 8
				s_in_hand.append( 8 )
			elif card[0] == 'J':
				if 9 > start_spade: start_spade = 9
				s_in_hand.append( 9 )
			elif card[0] == 'Q':
				if 10 > start_spade: start_spade = 10
				s_in_hand.append( 10 )
			elif card[0] == 'K':
				if 11 > start_spade: start_spade = 11
				s_in_hand.append( 11 )
			elif card[0] == 'A':
				if 12 > start_spade: start_spade = 12
				s_in_hand.append( 12 )
			else:
				if int( card[0] ) - 2  > start_spade: start_spade = int( card[0] ) - 2
				s_in_hand.append( int( card[0] ) - 2 )

		if card[0] == 'J':
			if in_hand[9] == 0: 
				in_hand[9] = card
			else:
				in_hand[9] = card + in_hand[9][1:]
			count[9] += 1
		elif card[0] == 'Q':
			if in_hand[10] == 0: 
				in_hand[10] = card
			else:
				in_hand[10] = card + in_hand[10][1:]
			count[10] += 1
		elif card[0] == 'K':
			if in_hand[11] == 0: 
				in_hand[11] = card
			else:
				in_hand[11] = card + in_hand[11][1:]
			count[11] += 1
		elif card[0] == 'A':
			if in_hand[12] == 0: 
				in_hand[12] = card
			else:
				in_hand[12] = card + in_hand[12][1:]
			count[12] += 1
		elif card[0] == 'X':
			if in_hand[8] == 0: 
				in_hand[8] = card
			else:
				in_hand[8] = card + in_hand[8][1:]
			count[8] += 1
		else:
			if in_hand[ int( card[0] ) - 2 ] == 0: 
				in_hand[ int( card[0] ) - 2 ] = card
			else:
				in_hand[ int( card[0] ) - 2 ] = card + in_hand[ int( card[0] ) - 2][1:]
			count[ int( card[0] ) - 2 ] += 1

	done = False
	# check for straight flush 104-116
	if num_hearts == 5:
		h_in_hand.sort()
		consec = [ x - h_in_hand[ i - 1 ] for i, x in enumerate( h_in_hand ) if i > 0 ]
		for c in consec:
			if c != 1:
				done = True
				break

		if done:
			done = False
		else:
			score.append( 104 + h_in_hand[4] )
	if num_diamonds == 5:
		d_in_hand.sort()
		consec = [ x - d_in_hand[ i - 1 ] for i, x in enumerate( d_in_hand ) if i > 0 ]
		for c in consec:
			if c != 1:
				done = True
				break

		if done:
			done = False
		else:
			score.append( 104 + d_in_hand[4] )
	if num_clubs == 5:
		c_in_hand.sort()
		consec = [ x - c_in_hand[ i - 1 ] for i, x in enumerate( c_in_hand ) if i > 0 ]
		for c in consec:
			if c != 1:
				done = True
				break

		if done:
			done = False
		else:
			score.append( 104 + c_in_hand[4] )
	if num_spades == 5:
		s_in_hand.sort()
		consec = [ x - s_in_hand[ i - 1 ] for i, x in enumerate( s_in_hand ) if i > 0 ]
		for c in consec:
			if c != 1:
				done = True
				break

		if done:
			done = False
		else:
			score.append( 104 + s_in_hand[4] )

	if done: continue

	# check for four of a kind 113-125
	for i in range(len( count ) - 1, -1, -1 ):
		if count[i] == 4:
			score.append( 113 + i )
			done = True
			break

	if done: continue

	# check for full house 89-112
	num_three = 0
	three_index = 0
	num_pairs = 0
	pair_index = 0
	for i in range( len( count ) - 1, -1, -1 ):
		if count[i] == 3:
			if num_three == 0: 
				num_three += 1
				three_index = i
			else:
				num_pairs += 1
				pair_index = i
		elif count[i] == 2:
			num_pairs += 1
			pair_index = i

		if num_three == 1 and num_pairs == 1:
			score.append( 89 + three_index + pair_index )
			done = True
			break
	
	if done: continue

	# check for flush 76-88
	if num_hearts == 5:
		score.append( 76 + start_heart )
		continue
	if num_diamonds == 5:
		score.append( 76 + start_diamond )
		continue
	if num_clubs == 5:
		score.append( 76 + start_club )
		continue
	if num_spades == 5:
		score.append( 76 + start_spade )
		continue

	# check for straight 63-75
	start_index = 0
	for i in range( len( count ) - 1, -1, -1 ):
		if count[i] == 1:
			num_cards = 0
			start_index = i
		while count[i] == 1 and i >= 0:
			num_cards += 1
			i -= 1
			if num_cards == 5:
				score.append( 63 + start_index )
				done = True
				break
		if done: break

	if done: continue

	# check for three of a kind 50-62
	for i in range( len( count ) - 1, -1, -1 ):
		if count[i] == 3:
			score.append( 50 + i )
			done = True
			break

	if done: continue

	# check for two pair 26-49
	pairs = 0
	high_pair = 0
	for i in range( len( count ) - 1, -1, -1 ):
		if count[i] == 2:
			pairs += 1
			if pairs == 2:
				score.append( 26 + high_pair + i )
				done = True
				break
			else:
				high_pair = i

	if done: continue

	# check for one pair 13-25
	for i in range( len( count ) - 1, -1, -1 ):
		if count[i] == 2:
			score.append( 13 + i )
			done = True
			break

	if done: continue

	# check for high card 0-12
	for i in range( len( count ) - 1, -1, -1 ):
		if count[i] == 1:
			score.append( i )
			break

winner = 0
max_score = 0
for i in range( 0, len( score ) ):
	if score[i] > max_score:
		winner = i
		max_score = score[i]

if winner == 0:
	print "You win!"
else:
	print "CPU " + str( winner ) + " wins!"

print "(scores:",
for s in score:
	print s,
print ")"
