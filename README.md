# regexp-spo
Regexp parser + engine (lab4)

# EBNF-notation

regex = term '|' regex | term  
term = {factor}  
factor = base ['*' | '+' | '?']  
base = char  
