          entry
          addi r14,r0,topaddr
          % processing:  := 
          lw r2,0(r14)
          sw 0(r14),r2
          % processing:  := 
          lw r2,0(r14)
          sw 0(r14),r2
          % processing:  := 
          lw r2,0(r14)
          sw 0(r14),r2
          % processing: t1 :=  + 
          lw r2,0(r14)
          lw r1,0(r14)
          add r4,r2,r1
          sw -4(r14),r4
          % processing:  := t1
          lw r1,-4(r14)
          sw 0(r14),r1
          % processing: t2 :=  + 
          lw r1,0(r14)
          lw r4,0(r14)
          add r3,r1,r4
          sw -4(r14),r3
          % processing:  := t2
          lw r4,-4(r14)
          sw 0(r14),r4
          % processing: put()
          lw r1,0(r14)
          % put value on stack
          addi r14,r14,-4
          sw -8(r14),r1
          % link buffer to stack
          addi r1,r0, buf
          sw -12(r14),r1
          % convert int to string for output
          jl r15, intstr
          sw -8(r14),r13
          % output to console
          jl r15, putstr
          subi r14,r14,-4
          % processing: put()
          lw r4,0(r14)
          % put value on stack
          addi r14,r14,-4
          sw -8(r14),r4
          % link buffer to stack
          addi r4,r0, buf
          sw -12(r14),r4
          % convert int to string for output
          jl r15, intstr
          sw -8(r14),r13
          % output to console
          jl r15, putstr
          subi r14,r14,-4
          % processing: t3 :=  + 
          lw r4,0(r14)
          lw r3,0(r14)
          add r2,r4,r3
          sw -4(r14),r2
          % processing: t4 :=  * 
          lw r3,0(r14)
          lw r4,0(r14)
          mul r1,r3,r4
          sw -4(r14),r1
          % processing: t5 := t3 * t4
          lw r4,-4(r14)
          lw r3,-4(r14)
          mul r2,r4,r3
          sw -4(r14),r2
          % processing: t6 :=  + 
          lw r3,0(r14)
          lw r4,0(r14)
          add r1,r3,r4
          sw -4(r14),r1
          % processing: t7 :=  + 
          lw r4,0(r14)
          lw r3,0(r14)
          add r2,r4,r3
          sw -4(r14),r2
          % processing: t8 := t6 * t7
          lw r3,-4(r14)
          lw r4,-4(r14)
          mul r1,r3,r4
          sw -4(r14),r1
          % processing: t9 := t5 + t8
          lw r4,-4(r14)
          lw r3,-4(r14)
          add r2,r4,r3
          sw -4(r14),r2
          % processing:  := t9
          lw r3,-4(r14)
          sw 0(r14),r3
          % processing: put()
          lw r4,0(r14)
          % put value on stack
          addi r14,r14,-4
          sw -8(r14),r4
          % link buffer to stack
          addi r4,r0, buf
          sw -12(r14),r4
          % convert int to string for output
          jl r15, intstr
          sw -8(r14),r13
          % output to console
          jl r15, putstr
          subi r14,r14,-4
          % processing:  := 
          lw r4,0(r14)
          sw 0(r14),r4
          % processing: put()
          lw r2,0(r14)
          % put value on stack
          addi r14,r14,-4
          sw -8(r14),r2
          % link buffer to stack
          addi r2,r0, buf
          sw -12(r14),r2
          % convert int to string for output
          jl r15, intstr
          sw -8(r14),r13
          % output to console
          jl r15, putstr
          subi r14,r14,-4
          % processing: t10 :=  + 
          lw r2,0(r14)
          lw r3,0(r14)
          add r1,r2,r3
          sw -4(r14),r1
          % processing: t11 :=  * 
          lw r3,0(r14)
          lw r2,0(r14)
          mul r4,r3,r2
          sw -4(r14),r4
          % processing: t12 := t10 * t11
          lw r2,-4(r14)
          lw r3,-4(r14)
          mul r1,r2,r3
          sw -4(r14),r1
          % processing: t13 :=  + 
          lw r3,0(r14)
          lw r2,0(r14)
          add r4,r3,r2
          sw -4(r14),r4
          % processing: t14 :=  + 
          lw r2,0(r14)
          lw r3,0(r14)
          add r1,r2,r3
          sw -4(r14),r1
          % processing: t15 := t13 * t14
          lw r3,-4(r14)
          lw r2,-4(r14)
          mul r4,r3,r2
          sw -4(r14),r4
          % processing: t16 := t12 + t15
          lw r2,-4(r14)
          lw r3,-4(r14)
          add r1,r2,r3
          sw -4(r14),r1
          % processing:  := t16
          lw r3,-4(r14)
          sw 0(r14),r3
          % processing: put()
          lw r2,0(r14)
          % put value on stack
          addi r14,r14,-4
          sw -8(r14),r2
          % link buffer to stack
          addi r2,r0, buf
          sw -12(r14),r2
          % convert int to string for output
          jl r15, intstr
          sw -8(r14),r13
          % output to console
          jl r15, putstr
          subi r14,r14,-4
          hlt
 
           % buffer space used for console output
buf       res 20
