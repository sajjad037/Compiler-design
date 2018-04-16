          entry
          addi r14,r0,topaddr
          % processing: put()
          lw r1,0(r14)
          % put value on stack
          addi r14,r14,-44
          sw -8(r14),r1
          % link buffer to stack
          addi r1,r0, buf
          sw -12(r14),r1
          % convert int to string for output
          jl r15, intstr
          sw -8(r14),r13
          % output to console
          jl r15, putstr
          subi r14,r14,-44
          % processing: t1 :=  * 
          lw r1,0(r14)
          lw r3,0(r14)
          mul r4,r1,r3
          sw -32(r14),r4
          % processing: t2 :=  + t1
          lw r3,0(r14)
          lw r1,-32(r14)
          add r2,r3,r1
          sw -36(r14),r2
          % processing: t3 :=  * t2
          lw r1,0(r14)
          lw r3,-36(r14)
          mul r4,r1,r3
          sw -44(r14),r4
          % processing:  := t3
          lw r3,-44(r14)
          sw 0(r14),r3
          % processing: put()
          lw r1,0(r14)
          % put value on stack
          addi r14,r14,-44
          sw -8(r14),r1
          % link buffer to stack
          addi r1,r0, buf
          sw -12(r14),r1
          % convert int to string for output
          jl r15, intstr
          sw -8(r14),r13
          % output to console
          jl r15, putstr
          subi r14,r14,-44
          % processing:  := 
          lw r1,0(r14)
          sw 0(r14),r1
          % processing: put()
          lw r4,0(r14)
          % put value on stack
          addi r14,r14,-44
          sw -8(r14),r4
          % link buffer to stack
          addi r4,r0, buf
          sw -12(r14),r4
          % convert int to string for output
          jl r15, intstr
          sw -8(r14),r13
          % output to console
          jl r15, putstr
          subi r14,r14,-44
          hlt
 
           % buffer space used for console output
buf       res 20
