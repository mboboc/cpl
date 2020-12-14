	.data
fizzbuzz:
    .asciiz "Fizzbuzz\n"
fizz:
	.asciiz "Fizz\n"
buzz:
	.asciiz "Buzz\n"
newline:
	.asciiz "\n"
	.text
main:
	li $v0, 1 # system call code for print_str
	li $a3, 0 # i
	li $a1, 100
	li $a2, 1
	li $t0, 15
	li $t1, 3
	li $t2, 5
loop:
	add $a3, $a2, $a3
	div $a3, $t0
	mfhi $t3
	bne $t3, 0, c1
	li $v0, 4 # system call code for print_str
	la $a0, fizzbuzz
	syscall
	j c4

c1:
	div $a3, $t1
	mfhi $t3
	bne $t3, 0, c2
	li $v0, 4 # system call code for print_str
	la $a0, fizz
	syscall
	j c4

c2:
	div $a3, $t2
	mfhi $t3
	bne $t3, 0, c3
	li $v0, 4 # system call code for print_str
	la $a0, buzz
	syscall
	j c4

c3:
	li $v0, 1 # system call code for print_str
	move $a0, $a3
	syscall
	li $v0, 4 # system call code for print_str
	la $a0, newline
	syscall
c4:
	bge $a1, $a3, loop

exit:
	li $v0, 10 # exit
	syscall