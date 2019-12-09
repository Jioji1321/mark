import re


str = 'a b   c,d '
str1 = re.split(r'[\s\,]+', str)

print(str1)