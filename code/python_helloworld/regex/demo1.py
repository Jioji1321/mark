import re

# resultBoolean = re.match('[0-9A-Za-z\_!@#\$\^\*][0-9A-Za-z\_!@#\$\^\*]{7,15}','!Q@W3e4r')

# print(resultBoolean)

test = '!Q@W3e4r'

if re.match(r'[0-9A-Za-z\_!@#\$\^\*][0-9A-Za-z\_!@#\$\^\*]{7,15}', test):
    print('pass')
else:
    print('reject')