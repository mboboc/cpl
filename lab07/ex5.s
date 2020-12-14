	.data
newline:
	.text
main:
	li $v0, 1 # system call code for print_str
	li $a0, 0
	li $a1, 20
	li $a2, 1
	li $a3, -4
	li $t0, 4
loop:
	add $a0, $a0, 1
	sw $a0, 0($sp)
	sub $sp, $sp, 4
	bge $a1, $a0, loop

print:
	add $sp, $sp, 4
	lw $a0, 0($sp)
	syscall
	beq $a1, 0, exit
	sub $a1, $a1, 1
	j print
exit:
	li $v0, 10 # exit
	syscall