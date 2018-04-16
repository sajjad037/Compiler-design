           entry
           addi r14,r0,topaddr
           % processing:  := 
           lw r1,(r0)
           sw (r0),r1
           % processing:  := 
           lw r1,(r0)
           sw (r0),r1
           % processing: t1 :=  + 
           lw r2,(r0)
           lw r3,(r0)
           add r1,r2,r3
           sw t1(r0),r1
           % processing:  := t1
           lw r1,t1(r0)
           sw (r0),r1
           % processing: t2 :=  + 
           lw r3,(r0)
           lw r2,(r0)
           add r1,r3,r2
           sw t2(r0),r1
           % processing:  := t2
           lw r1,t2(r0)
           sw (r0),r1
           % processing: t3 :=  * 
           lw r2,(r0)
           lw r3,(r0)
           mul r1,r2,r3
           sw t3(r0),r1
           % processing:  := t3
           lw r1,t3(r0)
           sw (r0),r1
           % processing: t4 :=  * 
           lw r3,(r0)
           lw r2,(r0)
           mul r1,r3,r2
           sw t4(r0),r1
           % processing:  := t4
           lw r1,t4(r0)
           sw (r0),r1
           % processing: put()
           lw r1,(r0)
           % put value on stack
           sw -8(r14),r1
           % link buffer to stack
           addi r1,r0, buf
           sw -12(r14),r1
           % convert int to string for output
           jl r15, intstr
           sw -8(r14),r13
           % output to console
           jl r15, putstr
           % processing: put()
           lw r1,(r0)
           % put value on stack
           sw -8(r14),r1
           % link buffer to stack
           addi r1,r0, buf
           sw -12(r14),r1
           % convert int to string for output
           jl r15, intstr
           sw -8(r14),r13
           % output to console
           jl r15, putstr
           % processing: put()
           lw r1,(r0)
           % put value on stack
           sw -8(r14),r1
           % link buffer to stack
           addi r1,r0, buf
           sw -12(r14),r1
           % convert int to string for output
           jl r15, intstr
           sw -8(r14),r13
           % output to console
           jl r15, putstr
           % processing: put()
           lw r1,(r0)
           % put value on stack
           sw -8(r14),r1
           % link buffer to stack
           addi r1,r0, buf
           sw -12(r14),r1
           % convert int to string for output
           jl r15, intstr
           sw -8(r14),r13
           % output to console
           jl r15, putstr
           % processing: put()
           lw r1,(r0)
           % put value on stack
           sw -8(r14),r1
           % link buffer to stack
           addi r1,r0, buf
           sw -12(r14),r1
           % convert int to string for output
           jl r15, intstr
           sw -8(r14),r13
           % output to console
           jl r15, putstr
           % processing: put()
           lw r1,(r0)
           % put value on stack
           sw -8(r14),r1
           % link buffer to stack
           addi r1,r0, buf
           sw -12(r14),r1
           % convert int to string for output
           jl r15, intstr
           sw -8(r14),r13
           % output to console
           jl r15, putstr
           % processing:  := 
           lw r1,(r0)
           sw (r0),r1
           % processing: put()
           lw r1,(r0)
           % put value on stack
           sw -8(r14),r1
           % link buffer to stack
           addi r1,r0, buf
           sw -12(r14),r1
           % convert int to string for output
           jl r15, intstr
           sw -8(r14),r13
           % output to console
           jl r15, putstr
           % processing:  := 
           lw r1,(r0)
           sw (r0),r1
           % processing: put()
           lw r1,(r0)
           % put value on stack
           sw -8(r14),r1
           % link buffer to stack
           addi r1,r0, buf
           sw -12(r14),r1
           % convert int to string for output
           jl r15, intstr
           sw -8(r14),r13
           % output to console
           jl r15, putstr
           % processing:  := 
           lw r1,(r0)
           sw (r0),r1
           % processing:  := 
           lw r1,(r0)
           sw (r0),r1
           % processing: t5 :=  + 
           lw r2,(r0)
           lw r3,(r0)
           add r1,r2,r3
           sw t5(r0),r1
           % processing:  := t5
           lw r1,t5(r0)
           sw (r0),r1
           % processing: t6 :=  * 
           lw r3,(r0)
           lw r2,(r0)
           mul r1,r3,r2
           sw t6(r0),r1
           % processing:  := t6
           lw r1,t6(r0)
           sw (r0),r1
           % processing:  := 
           lw r1,(r0)
           sw (r0),r1
           % processing:  := 
           lw r1,(r0)
           sw (r0),r1
           % processing: put()
           lw r1,(r0)
           % put value on stack
           sw -8(r14),r1
           % link buffer to stack
           addi r1,r0, buf
           sw -12(r14),r1
           % convert int to string for output
           jl r15, intstr
           sw -8(r14),r13
           % output to console
           jl r15, putstr
           % processing: put()
           lw r1,(r0)
           % put value on stack
           sw -8(r14),r1
           % link buffer to stack
           addi r1,r0, buf
           sw -12(r14),r1
           % convert int to string for output
           jl r15, intstr
           sw -8(r14),r13
           % output to console
           jl r15, putstr
           % processing: put()
           lw r1,(r0)
           % put value on stack
           sw -8(r14),r1
           % link buffer to stack
           addi r1,r0, buf
           sw -12(r14),r1
           % convert int to string for output
           jl r15, intstr
           sw -8(r14),r13
           % output to console
           jl r15, putstr
           % processing: put()
           lw r1,(r0)
           % put value on stack
           sw -8(r14),r1
           % link buffer to stack
           addi r1,r0, buf
           sw -12(r14),r1
           % convert int to string for output
           jl r15, intstr
           sw -8(r14),r13
           % output to console
           jl r15, putstr
           % processing: put()
           lw r1,(r0)
           % put value on stack
           sw -8(r14),r1
           % link buffer to stack
           addi r1,r0, buf
           sw -12(r14),r1
           % convert int to string for output
           jl r15, intstr
           sw -8(r14),r13
           % output to console
           jl r15, putstr
           % processing: put()
           lw r1,(r0)
           % put value on stack
           sw -8(r14),r1
           % link buffer to stack
           addi r1,r0, buf
           sw -12(r14),r1
           % convert int to string for output
           jl r15, intstr
           sw -8(r14),r13
           % output to console
           jl r15, putstr
           % processing: put()
           lw r1,(r0)
           % put value on stack
           sw -8(r14),r1
           % link buffer to stack
           addi r1,r0, buf
           sw -12(r14),r1
           % convert int to string for output
           jl r15, intstr
           sw -8(r14),r13
           % output to console
           jl r15, putstr
           % processing: put()
           lw r1,(r0)
           % put value on stack
           sw -8(r14),r1
           % link buffer to stack
           addi r1,r0, buf
           sw -12(r14),r1
           % convert int to string for output
           jl r15, intstr
           sw -8(r14),r13
           % output to console
           jl r15, putstr
           % processing: put()
           lw r1,(r0)
           % put value on stack
           sw -8(r14),r1
           % link buffer to stack
           addi r1,r0, buf
           sw -12(r14),r1
           % convert int to string for output
           jl r15, intstr
           sw -8(r14),r13
           % output to console
           jl r15, putstr
           % processing: put()
           lw r1,(r0)
           % put value on stack
           sw -8(r14),r1
           % link buffer to stack
           addi r1,r0, buf
           sw -12(r14),r1
           % convert int to string for output
           jl r15, intstr
           sw -8(r14),r13
           % output to console
           jl r15, putstr
           hlt
 
 i          res 4
j          res 4
a          res 4
s          res 4
m          res 4
d          res 4
sign       res 4
or_op      res 4
and_op     res 4
not_op_i   res 4
not_op_j   res 4
           % space for  + 
t1         res 4
           % space for  + 
t2         res 4
           % space for  * 
t3         res 4
           % space for  * 
t4         res 4
           % space for  + 
t5         res 4
           % space for  * 
t6         res 4
           % buffer space used for console output
buf        res 20
