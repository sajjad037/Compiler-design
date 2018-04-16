           entry
           addi r14,r0,topaddr
           % processing:  := 
           lw r1,(r0)
           sw (r0),r1
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
           % processing: t3 :=  + 
           lw r2,(r0)
           lw r3,(r0)
           add r1,r2,r3
           sw t3(r0),r1
           % processing: t4 :=  * 
           lw r3,(r0)
           lw r2,(r0)
           mul r1,r3,r2
           sw t4(r0),r1
           % processing: t5 := t3 * t4
           lw r2,t3(r0)
           lw r3,t4(r0)
           mul r1,r2,r3
           sw t5(r0),r1
           % processing: t6 :=  + 
           lw r3,(r0)
           lw r2,(r0)
           add r1,r3,r2
           sw t6(r0),r1
           % processing: t7 :=  + 
           lw r2,(r0)
           lw r3,(r0)
           add r1,r2,r3
           sw t7(r0),r1
           % processing: t8 := t6 * t7
           lw r3,t6(r0)
           lw r2,t7(r0)
           mul r1,r3,r2
           sw t8(r0),r1
           % processing: t9 := t5 + t8
           lw r2,t5(r0)
           lw r3,t8(r0)
           add r1,r2,r3
           sw t9(r0),r1
           % processing:  := t9
           lw r1,t9(r0)
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
           % processing: t10 :=  + 
           lw r3,(r0)
           lw r2,(r0)
           add r1,r3,r2
           sw t10(r0),r1
           % processing: t11 :=  * 
           lw r2,(r0)
           lw r3,(r0)
           mul r1,r2,r3
           sw t11(r0),r1
           % processing: t12 := t10 * t11
           lw r3,t10(r0)
           lw r2,t11(r0)
           mul r1,r3,r2
           sw t12(r0),r1
           % processing: t13 :=  + 
           lw r2,(r0)
           lw r3,(r0)
           add r1,r2,r3
           sw t13(r0),r1
           % processing: t14 :=  + 
           lw r3,(r0)
           lw r2,(r0)
           add r1,r3,r2
           sw t14(r0),r1
           % processing: t15 := t13 * t14
           lw r2,t13(r0)
           lw r3,t14(r0)
           mul r1,r2,r3
           sw t15(r0),r1
           % processing: t16 := t12 + t15
           lw r3,t12(r0)
           lw r2,t15(r0)
           add r1,r3,r2
           sw t16(r0),r1
           % processing:  := t16
           lw r1,t16(r0)
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
           hlt
 
 f1         res 4
x          res 4
y          res 4
i5         res 4
j10        res 4
ind        res 4
           % space for  + 
t1         res 4
           % space for  + 
t2         res 4
           % space for  + 
t3         res 4
           % space for  * 
t4         res 4
           % space for t3 * t4
t5         res 4
           % space for  + 
t6         res 4
           % space for  + 
t7         res 4
           % space for t6 * t7
t8         res 4
           % space for t5 + t8
t9         res 4
           % space for  + 
t10        res 4
           % space for  * 
t11        res 4
           % space for t10 * t11
t12        res 4
           % space for  + 
t13        res 4
           % space for  + 
t14        res 4
           % space for t13 * t14
t15        res 4
           % space for t12 + t15
t16        res 4
           % buffer space used for console output
buf        res 20
