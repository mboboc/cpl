	.data
str1:
    .asciiz "1\n"
str2:
    .asciiz "Large value\n"
str3:
	.asciiz "2"
str4:
	.asciiz "Small value\n"
    .text
main:
	li $v0, 4 # system call code for print_str
	la $a0, str1
	syscall
	li $a1, 50
	li $a2, 64 
	bgt $a0, $a1, print
	la $a0, str4
	syscall
	j exit 
print:
	la $a0, str2
	syscall

exit:
	la $a0, str3
	syscall
	li $v0, 10 # exit
	syscall