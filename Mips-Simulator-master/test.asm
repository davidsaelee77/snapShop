

.data

first: .word 10

second: .word 20

third: .word 30

.text


main:	addi	$a0,	$zero,	1
	addiu	$a1,	$zero,	2
	andi	$a0,	$a0,	0
	ori	$a1,	$a1,	0	
	lw	$t0,	first
	sw	$t0,	0($t1)
	j	rtest	


loop:	addi	$a0,	$a0,	100
	jr	$zero


rtest:	add	$v0,	$t0,	$t0
	addu	$v0,	$v0,	$v0
	and	$v0,	$v0,	$zero
	or	$v1,	$v0,	$a1   
	beq	$v1,	$zero,	loop
