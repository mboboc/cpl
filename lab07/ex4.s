	.data
newline:
	.text
main:
	li $v0, 1 # system call code for print_str
	li $a0, 0
	li $a1, 20
	li $a2, 1
loop:
	syscall
	add $a0, $a2, $a0
	bge $a1, $a0, loop

exit:
	li $v0, 10 # exit
	syscall