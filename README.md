# regexp-spo
Regexp parser + engine (lab4)

regex = term '|' regex | term  
term = {factor}  
factor = base ['*' | '+' | {'?'}]  
base = char  
