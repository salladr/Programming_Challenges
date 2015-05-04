#!/usr/bin/python

tens = { '0': '', '2': 'twenty', '3': 'thirty', '4': 'forty', '5': 'fifty', '6': 'sixty', '7': 'seventy', '8': 'eighty', '9': 'ninety',
		 'A': 'atta', 'B': 'bibbity', 'C': 'city', 'D': 'dickety', 'E': 'ebbity', 'F': 'fleventy' }

ones = { '0': 'zero', '1': 'one', '2': 'two', '3': 'three', '4': 'four', '5': 'five', '6': 'six', '7': 'seven', '8': 'eight', '9': 'nine',
		 'A': 'a', 'B': 'bee', 'C': 'cee', 'D': 'dee', 'E': 'e', 'F': 'eff' }

teens = { 'zero': 'ten', 'one': 'eleven', 'two': 'twelve', 'three': 'thirteen', 'four': 'fourteen', 'five': 'fifteen', 'six': 'sixteen',
		  'seven': 'seventeen', 'eight': 'eighteen', 'nine': 'nineteen', 'a': 'abteen', 'bee': 'bibteen', 'cee': 'cleventeen',
		  'dee': 'dibbleteen', 'e': 'eggteen', 'eff': 'fleventeen' }

result = []

while True:
	try:
		hex_input = raw_input()
		position = 0

		for digit in reversed( hex_input[2:].upper() ):
			if position == 0:
				result.append( ones[ digit ] )

			elif position == 1:
				if digit == '1':
					result.append( teens[ result.pop() ] )
				else:
					val = result.pop()
					if val != 'zero' :
						result.append( tens[ digit ] + '-' + val )
					else:
						result.append( tens[ digit ] );

			elif position == 2:
				result.append( 'bitey' );
				result.append( ones[ digit ] );
				position += 1

			position = ( position + 1 ) % 3

		result.append( hex_input )			
		result.reverse()
		for val in result:
			if val != 'zero' :
				print val,
		print ''
		result = []

	except EOFError:
		break;
	except KeyboardInterrupt:
		break;
